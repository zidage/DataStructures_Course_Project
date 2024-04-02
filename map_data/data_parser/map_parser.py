"""
地图数据处理模块
功能: 删除数据文件夹中数据不完整的文件夹, 并从特定格式文件中还原networkx和geopandas等库中规定的数据.
然后利用matplotlib中的方法对地图进行可视化还原, 这个模块仅作实验性使用, 日后可能其功能会被拆分.

作者: 字禹润

创建日期: 2024-03-21
最后修改日期: 2024-03-24
"""


from fileinput import filename
import geopandas as gpd
import os
import shutil
from matplotlib.font_manager import json_dump
import matplotlib.pyplot as plt
import json
import networkx as nx
from numpy import save
import osmnx as ox
from shapely.geometry import Polygon, MultiPolygon, Point
from random import randint
import pickle


def list_subdirectories(root_dir):
    """
    该函数可以返回文件夹中所有子目录。

    Parameter:
    root_dir (str): 根目录地址

    Returns:
    list: 根目录下所有子目录
    """
    subdirectories = []
    # 遍历root_dir下的所有文件和文件夹
    for item in os.listdir(root_dir):
        item_path = os.path.join(root_dir, item)
        # 如果是文件夹，则将其添加到subdirectories列表中
        if os.path.isdir(item_path):
            subdirectories.append(item_path)
            # 递归地调用list_subdirectories函数，继续遍历子文件夹
    return subdirectories


def delete_folders_with_few_files(root_dir, min_files=2):
    """
    该函数可以删除给定根目录下文件个数少于等于min_files个数的子文件夹

    Parameter:
    root_dir (str): 根目录地址
    min_files (int): 文件夹删除阈值

    Returns:
    void
    """
    for dirpath, dirnames, filenames in os.walk(root_dir, topdown=False):
        if len(filenames) <= min_files and dirpath != root_dir:  # 排除根文件夹
            for file in filenames:
                os.remove(os.path.join(dirpath, file))  # 删除文件
            os.rmdir(dirpath)  # 删除空文件夹
            print(f"Deleted folder: {dirpath}")


def get_files_in_folder(folder_path):
    """
    该函数可以返回给定目录下所有文件的路径列表

    Parameter:
    folder_path (str): 文件夹如今

    Returns:
    list: 目录下的所有文件的路径组成的列表
    """
    files = []
    for file_name in os.listdir(folder_path):
        file_path = os.path.join(folder_path, file_name)
        if os.path.isfile(file_path):
            files.append(file_path)
    return files


def info_parser(file):
    """
    *_info.json文件的解析函数, 目前只回返回"name"这个key的对应value

    Parameter:
    file (str): 文件路径

    Returns:
    str: 地点中文名
    """
    with open(file, 'r', encoding='gb2312') as f:
        js = json.load(f)
        return js["university"]["name"], js["university"]["rating"]


def regular_parser(file):
    """
    常规地理信息文件的解析函数, 返回一个GeoDataFrame.

    Parameter:
    file (str): 文件路径

    Returns:
    GeoDataFrame: 读取特定地理信息文件后形成的GeoDataFrame
    """
    gdf = gpd.read_file(file)
    return gdf


def graph_parser(file):
    """
    graph类型文件的解析函数, 返回一个graph类型的对象.

    Parameter:
    file (str): 文件路径

    Returns:
    Graph: networkx库中定义的存储图的数据对象, 可能是Graph, DiGraph, MultiGraph, MultiDiGraph中的一种
    """
    return ox.load_graphml(file)


def area_parser(file):
    """
    Deprecated. 与regular_parser功能相同
    """
    gdf = gpd.read_file(file)
    return gdf


def amenity_parser(file):
    """
    amenity类型文件的解析函数, 会对一个地点范围内的设施进行筛选并返回一个GeoDataFrame. 

    Parameter:
    file (str): 文件路径

    Returns:
    GeoDataFrame: 地点范围内设施的data set.
    """
    try:
        gdf = gpd.read_file(file)
        gdf = gdf[gdf['amenity'] != 'university'].drop_duplicates(subset=['name']) # 将含'university'的数据行删去，并去除名字重复的数据
        gdf = gdf[~gdf['name'].astype(str).str.isdigit()] # 将仅为数字的数据行删掉（防止垃圾数据）
        gdf.replace({'nan': '可能感兴趣的地点'}, inplace=True)
        # print(gdf.head())
        return gdf
    except:
        print("Amenity file parsing error")
        return None


def amenity_filter(amenity_gdf, area_gdf):
    """
    amenity类型文件的进一步筛选函数, 目前已废弃. 
    """
    try:
        # 定义函数处理每个几何对象
        def filter_geometry(row):
            geom = row['geometry']
            if isinstance(geom, Point) or isinstance(geom, Polygon):
                return any(geom.within(polygon) for polygon in area_gdf['geometry'])
            elif isinstance(geom, MultiPolygon):
                for poly in geom.geoms:
                    if any(poly.within(polygon) for polygon in area_gdf['geometry']):
                        return True
                return False

        # 对每个 'amenity_gdf' 中的几何值进行空间查询，并保留在 'area_gdf' 中的数据
        filtered_gdf = amenity_gdf[amenity_gdf.apply(filter_geometry, axis=1)]
        return filtered_gdf
    except Exception as e:
        print("filter error: ", e)
        return amenity_gdf


def export_data_object(map_basket, amenity_basket, university):
    file_name = university.split('\\')[-1]
    save_path = f"{os.environ['MAP_DATA']}/map_exports_test/{file_name}"
    os.makedirs(save_path)
    # gdf = amenity_filter(map_basket["amenity"], map_basket["area"])
    # print(map_basket["amenity"].head(100))
    amenity_basket["amenity_list"] = []
    try:
        for idx, row in map_basket["amenity"].iterrows():
            amenity_basket["amenity_list"].append({"id": hash(row["name"]), "name": row["name"], "type": row["amenity"], 
                                                  "latitude": row["geometry"].centroid.y, "longitutde": row["geometry"].centroid.x})
    except:
        print(f"No amenity in {file_name}")
    export_data = {
        "id": map_basket["id"],
        "name": map_basket["name"],
        "rating": map_basket["rating"],
        "popularity": map_basket["popularity"],
        "data_path": f"map_data/university_map/{file_name}",
        "amenity": amenity_basket
    }
    with open(f"{save_path}/{file_name}_map.json", 'w', encoding='utf-8') as file:
        json.dump(export_data, file, indent=4, ensure_ascii=False)
    with open(f"{save_path}/{file_name}_sr.pickle", 'wb') as file:
        pickle.dump(map_basket, file)


# main函数
root_dir = f'{os.environ["MAP_DATA"]}/university_map_test'
delete_folders_with_few_files(root_dir)
university_directories = list_subdirectories(root_dir)

for university in university_directories:
    files_path = get_files_in_folder(university)
    map_basket = {"id": None, "name": None, "rating": None, "popularity": None, "graph": None, "area": None, 
                  "building": None, "amenity": None, "route": None}
    amenity_basket = {"affiliation": None, "amenity_list": None}
    for file in files_path:   
        parsed_line = file.split('_')
        file_type = parsed_line[-1]
        if file_type == 'info.json':
            map_basket["name"], map_basket["rating"] = info_parser(file)
            map_basket["id"] = hash(map_basket["name"])
            map_basket["popularity"] = int(map_basket["rating"]) * randint(10, 25)
            amenity_basket["affiliation"] = map_basket["id"]
        elif file_type == 'graph.graphml':
            map_basket["graph"] = graph_parser(file)
        elif file_type == 'area.gpkg':
            map_basket["area"] = regular_parser(file)
        elif file_type == 'buildings.gpkg':
            map_basket["building"] = regular_parser(file)
        elif file_type == 'amenity.gpkg':
            map_basket["amenity"] = amenity_parser(file)

    export_data_object(map_basket, amenity_basket, university)
    print(university + 'map image and export data generated!')