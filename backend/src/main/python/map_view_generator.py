"""
地图图像生成模块
功能: 

作者: 字禹润

创建日期: 2024-03-30
最后修改日期: 2024-04-03
"""


import geopandas as gpd
import os
import json
import networkx as nx
import osmnx as ox
import pickle
from math import isnan
import route_finder
from shapely.geometry import Point


route_color = ["#f94144", "#f3722c", "#f8961e", "#f9c74f",
               "#90be6d", "#43aa8b", "#577590"]


# 从amn_list中得到某一地点的经纬度以及名字
def get_place(amn_list, place_id):
    place_coor = []
    
    for place in amn_list:
        if (place["id"] == int(place_id)):
            place_coor.append(float(place["longitutde"]))
            place_coor.append(float(place["latitude"]))
            place_coor.append(place["name"])
        
    return place_coor


def new_view_generator(place, map_basket, wpt_gdf=None):
    mk = {"radius": 6}
    # print(map_basket["adj_list"])
    # nodes, edges = ox.graph_to_gdfs(map_basket["graph"])

    if (len(map_basket["route"]) == 0):
        m = map_basket["amenity"].explore(tooltip="name")
        m.save(f"{os.environ['MAP_DATA']}\\map_view_html_test\\{place}.html")
        print("html saved!")
        return
    
    trip_time = 0
    path = []
    path_name = []

    m = None

    for index, route in enumerate(map_basket["route"], start=0):
        prev_name = None
        route_edges = ox.routing.route_to_gdf(
            map_basket["graph"], route[0], weight="length")
        try:
            for idx, name in enumerate(route_edges["name"], start=0):
                if prev_name != name or idx == 0:
                    prev_name = name
                    path_name.append('未知道路' if isnan(name if type(name) != str else 1) else name)
            path.append(path_name)
            path_name = []
        except:
            path.append(['未知道路'])
            path_name = []
        if m is not None:
            m = route_edges.explore(m=m, style_kwds={
                                    "weight": 6, "opacity": 0.8}, color=route_color[index % len(route_color)])
        else:
            m = route_edges.explore(style_kwds={
                                    "weight": 6, "opacity": 0.8}, color=route_color[index % len(route_color)])
        trip_time += route[1]
    m = wpt_gdf.explore(m=m, tooltip="name", marker_kwds=mk)

    
    
    for p in wpt_gdf['name']:
        print(p + " -> ", end="")
    print("trip_length: ", str(trip_time) + "m")
    # print("Throuth road" + str(path))
    #if waypoints is not None:
        #for index, w in enumerate(waypoints, start=0):
            #map_basket["amenity"].loc[map_basket["amenity"]["name"] == w[2]].explore(m=m, tooltip="name", marker_kwds=mk,
                                                                                  #color=route_color[index % len(route_color)])

    m.save(f"{os.environ['MAP_DATA']}\\map_view_html_test\\{place}.html")
    print(f"{place} html saved!")


# main函数
root_dir = f'{os.environ["MAP_DATA"]}/map_exports_test'
output_dir = f'{os.environ["MAP_DATA"]}/map_view_test'

while (True):
    # 用户输入
    # 用户输入将由3-5部分组成, 其中参数0, 1, 2分别为模式、地点规范名称、缩放因子
    # 参数3, 4则为-b模式下起点及终点的id
    user_input = input().split()
    mode = user_input[0]
    place = user_input[1]

    # query的大学名称未找到则程序将暂停
    if not os.path.exists(f"{root_dir}/{place}"):
        print("Folder do not exist!")
    else:
        with open(f"{root_dir}/{place}/{place}_sr.pickle", "rb") as f:
            map_basket = pickle.load(f)
            map_basket["route"] = []
            if (mode == '-n'):  # -n模式, 该模式将不显示路径
                new_view_generator(place, map_basket)
                # view_generator(map_basket, university, output_dir,
                # None, None, scale_factor=float(user_input[2]))
            elif (mode == '-r'):  # -b模式，该模式将显示路径, 并需要client程序输入起点及终点
                with open(f"{root_dir}/{place}/{place}_map.json", "r", encoding='utf-8') as js_file:
                    js = json.load(js_file)
                    amn_list = js["amenity"]["amenity_list"]
                    orig_venue, dest_venue = None, None
                    waypoints = {"name": [], "geometry": []}
                    wpt = []
                    for i in range(2, len(user_input) - 1):
                        orig_venue = get_place(amn_list, user_input[i])
                        dest_venue = get_place(amn_list, user_input[i + 1])
                        waypoints["name"].append(orig_venue[2])
                        waypoints["geometry"].append(Point(orig_venue[0], orig_venue[1]))
                        wpt.append(orig_venue)
                    waypoints["name"].append(dest_venue[2])
                    waypoints["geometry"].append(Point(dest_venue[0], dest_venue[1]))
                    wpt.append(dest_venue)
                    wpt_gdf = gpd.GeoDataFrame(waypoints)
                    map_basket["route"] = route_finder.route_find_test(place, map_basket, wpt)
                    new_view_generator(place, map_basket, wpt_gdf)
