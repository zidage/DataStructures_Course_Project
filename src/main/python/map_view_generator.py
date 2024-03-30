"""
地图图像生成模块
功能: 

作者: 字禹润

创建日期: 2024-03-30
最后修改日期: 2024-03-30
"""


from fileinput import filename
import geopandas as gpd
import os
from matplotlib.font_manager import json_dump
import matplotlib.pyplot as plt
import json
import networkx as nx
import osmnx as ox
from random import randint
from sys import argv
import pickle


def view_generator(map_basket, university, save_path, offset=0.001, dark_mode=False):
    """
    根据各类数据集合对各种数据进行可视化的函数

    Parameter:
    map_basket (dict): 特定地点的数据集合
    university (str): 文件命名所用字符串的组成部分

    Returns:
    void
    """

    if map_basket["graph"] is not None: # 先对存储有road network的graph数据进行可视化，具体参数详见osmnx及matplotlib文档
        fig, ax = ox.plot_graph(
            map_basket["graph"],
            show=False,
            close=False,
            bgcolor="#141414",
            edge_color="lightgrey",
            edge_linewidth=1,
            node_size=0,
        )


        # 显示路名
        for _, edge in ox.graph_to_gdfs(map_basket["graph"], nodes=False).fillna("").iterrows():
            random_number = randint(1, 100) # 由于每条边都有命名，所以将随机显示，避免文字过密
            if (random_number % 3 == 0):
                text = edge["name"]
                c = edge["geometry"].centroid
                ax.annotate(text, (c.x, c.y), c="white", fontname='Microsoft YaHei', fontsize=3)

        # 显示地点占据区域
        if map_basket["area"] is not None:
            map_basket["area"].plot(ax=ax, facecolor='dimgrey', linewidth=0.6, edgecolor='black', alpha=0.5)
            # offset参数，单位为"°"，用于控制缩放范围
            bbox = map_basket["area"].total_bounds # 区域占据的bbox大小，用于限定显示范围
            ax.set_xlim(bbox[0]+offset, bbox[2]-offset)
            ax.set_ylim(bbox[1]+offset, bbox[3]-offset)

        # 显示建筑
        if map_basket["building"] is not None:
            map_basket["building"].plot(ax=ax, facecolor='lightgrey')

        #显示设施
        if map_basket["amenity"] is not None:
            # filtered_gdf = amenity_filter(map_basket["amenity"], map_basket["area"])
            # 显示设施名，并用红点标注
            for idx, row in map_basket["amenity"].iterrows():
                centroid = row.geometry.centroid
                text = row['name']
                ax.annotate(text, (centroid.x, centroid.y), fontsize=4, fontname='Microsoft YaHei', color='white')
                ax.scatter(centroid.x, centroid.y, color='red', s=10)
        

        
        # 缩略图存储文件名
        # 存储文件
        plt.savefig(f'{save_path}/map_view_{university}_{offset}.png', bbox_inches='tight', dpi=300)
        plt.close()
        print(f"{university} view generated.")



# main函数
root_dir = f'{os.environ["MAP_DATA"]}/map_exports_test'
output_dir = f'{os.environ["MAP_DATA"]}/map_view_test'

while (True):
    user_input = input().split()
    university = user_input[0]



    if not os.path.exists(f"{root_dir}/{university}"):
        print("Folder do not exist!")
    else:
        with open(f"{root_dir}/{university}/{university}_sr.pickle", "rb") as f:
            map_basket = pickle.load(f)
            view_generator(map_basket, university, output_dir, offset=float(user_input[1]))