"""
地图图像生成模块
功能: 

作者: 字禹润

创建日期: 2024-03-30
最后修改日期: 2024-04-03
"""


import geopandas as gpd
import os
import matplotlib.pyplot as plt
import json
import networkx as nx
import osmnx as ox
from random import randint
import pickle
import route_finder


# 配色方案. 配色为RGB的8bit表示
# TODO: 根据生成地图所在位置选择不同配色方案
color_scheme = {"route": "#780000", "backgroud": "#fdf0d5", "building": "#669bbc",
                "road": "#003049", "address": "#c1121f", "area": "#d9d9d9"}


def view_generator(map_basket, university, save_path,
                   orig_place, dest_place,
                   scale_factor=-0.001, offset_x=0, offset_y=0,
                   dark_mode=False):
    """
    根据各类数据集合对各种数据进行可视化的函数

    Parameter:
    map_basket (dict): 特定地点的数据集合
    university (str): 文件命名所用字符串的组成部分

    Returns:
    None
    """

    # 先对存储有road network的graph数据进行可视化，具体参数详见osmnx及matplotlib文档
    if map_basket["graph"] is not None and map_basket["route"] is not None:
        fig, ax = ox.plot_graph_route(
            map_basket["graph"],
            map_basket["route"],
            route_color=color_scheme["route"],
            route_linewidth=2,
            route_alpha=1,
            orig_dest_size=6,
            ax=None,
            show=False,
            close=False,
            bgcolor=color_scheme["backgroud"],
            edge_color=color_scheme["road"],
            edge_linewidth=1,
            node_size=0,
        )

    elif map_basket["graph"] is not None:  # 如果当前模式不进行路径显示时使用的plot方案
        fig, ax = ox.plot_graph(
            map_basket["graph"],
            show=False,
            close=False,
            bgcolor=color_scheme["backgroud"],
            edge_color=color_scheme["road"],
            edge_linewidth=1,
            node_size=0,
        )

    # 显示路名
    graph = ox.graph_to_gdfs(map_basket["graph"], nodes=False).fillna("")
    shuffled_edge = graph.sample(
            frac=scale_factor*950+1 if scale_factor < 0 else 1)
    for _, edge in shuffled_edge.iterrows():
        # 由于每条边都有命名，所以将随机显示，避免文字过密
        if (edge["name"] != "nan"):
            text = edge["name"]
            c = edge["geometry"].centroid
            ax.annotate(text, (c.x, c.y), c="black",
                        fontname='Microsoft YaHei', fontsize=3)

    # 显示建筑
    if map_basket["building"] is not None:
        map_basket["building"].plot(ax=ax, facecolor=color_scheme["building"])

    # 显示设施
    if map_basket["amenity"] is not None:
        # 显示设施名，并用红点标注
        if map_basket["route"] is None:
            shuffled_amn = map_basket["amenity"].sample(
                frac=scale_factor*900+1 if (scale_factor < 0 and len(map_basket["amenity"].index) > 100) else 1)
            for idx, row in shuffled_amn.iterrows():
                centroid = row.geometry.centroid
                text = row['name']
                ax.annotate(text, (centroid.x, centroid.y), fontsize=4,
                            fontname='Microsoft YaHei', color='black')
                ax.scatter(centroid.x, centroid.y,
                        color=color_scheme["address"], s=10)
        else:
            ax.annotate(orig_place[2], (orig_place[0], orig_place[1]),
                        fontsize=4, fontname='Microsoft YaHei', color='black')
            ax.annotate(dest_place[2], (dest_place[0], dest_place[1]),
                        fontsize=4, fontname='Microsoft YaHei', color='black')
            ax.scatter(orig_place[0], orig_place[1],
                        color=color_scheme["address"], s=5)
            ax.scatter(dest_place[0], dest_place[1],
                        color=color_scheme["address"], s=5)

    # 显示地点占据区域
    if map_basket["area"] is not None:
        map_basket["area"].plot(
            ax=ax, facecolor=color_scheme["area"], linewidth=0, edgecolor='black', alpha=0.5)
        # scale_factor参数，单位为"°"，用于控制缩放范围
        # offset_x和offset_y为上下左右平移参数
        bbox = map_basket["area"].total_bounds  # 区域占据的bbox大小，用于限定显示范围
        ax.set_xlim(bbox[0]+offset_x+scale_factor,
                    bbox[2]+offset_x-scale_factor)
        ax.set_ylim(bbox[1]+offset_y+scale_factor,
                    bbox[3]+offset_y-scale_factor)

    # 缩略图存储文件名
    # 存储文件
    plt.savefig(f'{save_path}/map_view_{university}_sc_{scale_factor}_x_{offset_x}_y_{offset_y}.png',
                bbox_inches='tight', dpi=300)
    plt.close()
    print(f"{university} view generated.")


# 从amn_list中得到某一地点的经纬度以及名字
def get_place(amn_list, place_id):
    place_coor = []
    for place in amn_list:
        if (place["id"] == int(place_id)):
            place_coor.append(float(place["longitutde"]))
            place_coor.append(float(place["latitude"]))
            place_coor.append(place["name"])
    return place_coor


# main函数
root_dir = f'{os.environ["MAP_DATA"]}/map_exports_test'
output_dir = f'{os.environ["MAP_DATA"]}/map_view_test'

while (True):
    # 用户输入
    # 用户输入将由3-5部分组成, 其中参数0, 1, 2分别为模式、地点规范名称、缩放因子
    # 参数3, 4则为-b模式下起点及终点的id
    user_input = input().split()
    mode = user_input[0]
    university = user_input[1]

    # query的大学名称未找到则程序将暂停
    if not os.path.exists(f"{root_dir}/{university}"):
        print("Folder do not exist!")
    else:
        with open(f"{root_dir}/{university}/{university}_sr.pickle", "rb") as f:
            map_basket = pickle.load(f)
            if (mode == '-n'):  # -n模式, 该模式将不显示路径
                view_generator(map_basket, university, output_dir,
                               None, None, scale_factor=float(user_input[2]))
            elif (mode == '-b'):  # -b模式，该模式将显示路径, 并需要client程序输入起点及终点
                with open(f"{root_dir}/{university}/{university}_map.json", "r", encoding='utf-8') as js_file:
                    js = json.load(js_file)
                    amn_list = js["amenity"]["amenity_list"]
                    orig_place = get_place(amn_list, user_input[2])
                    dest_place = get_place(amn_list, user_input[3])
                    map_basket["route"] = route_finder.route_find(
                        map_basket, orig_place, dest_place)
                    view_generator(map_basket, university, output_dir, orig_place,
                                   dest_place, scale_factor=float(user_input[4]))
