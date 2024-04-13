---
description: 作者：字禹润
---

# data\_parser

## Overview

该文件夹下有一个Python程序名为`map_parser.py`，其为之前使用`university_location_fetch_osm.py`导出的数据的解析程序。

该文件夹下有以下方法：

* <pre class="language-python"><code class="lang-python">def list_subdirectories(root_dir)
  <strong>def delete_folders_with_few_files(root_dir, min_files=2)
  </strong><strong>def get_files_in_folder(folder_path)
  </strong>def info_parser(file)
  def regular_parser(file)
  def graph_parser(file)
  def area_parser(file)
  def amenity_parser(file)
  def export_data_object(map_basket, amenity_basket, university)
  </code></pre>

以及一个启动入口函数

这个程序也仅为一个脚本程序，其中值得注意的事项为下：

## `map_basket`

map\_basket为该个模块的核心数据变量，该变量为一个dict类型的变量且存储着一个地点所需的生成地图的一切数据，这是map\_basket数据类型生成的代码：

```python
map_basket = {"id": None, "name": None, "rating": None, "popularity": None, "graph": None, "area": None,
                  "building": None, "amenity": None, "route": None}
amenity_basket = {"affiliation": None, "amenity_list": None}
for file in files_path:
    parsed_line = file.split('_')  # 把下划线分隔的文件名拆成一个数组
    file_type = parsed_line[-1]  # 得到文件类型
    if file_type == 'info.json':
        map_basket["name"], map_basket["rating"] = info_parser(file)
        map_basket["id"] = hash(map_basket["name"])  # 计算该地点的哈希值
        map_basket["popularity"] = int(
            map_basket["rating"]) * randint(10, 25)  # 根据地点评分随机生成一个欢迎度
        amenity_basket["affiliation"] = map_basket["id"]
    elif file_type == 'graph.graphml':
        map_basket["graph"] = graph_parser(file)  # 将图解析并存储
    elif file_type == 'area.gpkg':
        map_basket["area"] = regular_parser(file)  # 将占据区域解析并存储
    elif file_type == 'buildings.gpkg':
        map_basket["building"] = regular_parser(file)  # 将建筑解析并存储
    elif file_type == 'amenity.gpkg':
        map_basket["amenity"] = amenity_parser(file)  # 将设施解析并存储
```

其中，`area，building，amenity`项对应的数据均为`GeoDataFrame`，`route`项为当前会话生成的路径列表，其并不在当前程序中有任何的对应操作。另外，`amenity_basket`将单独存储地点内部的所有建筑和设施（由`building，amenity`项中的数据得到）

在数据解析完成后，每个地点的数据将被存储到`map_data/map_exports`中，存储的数据有以下两种：

## `*_map.json`

该种文件中，将存储以下几种的数据：

* `id`：该地点的唯一标识符，为地点名称的哈希值
* `name`：地点的格式化英文名
* `rating`：评分
* `popularity`：地点的热度，是一个由评分生成的随机值（因为真的没有那么多人对去那么多大学旅游感兴趣）
* `data_path`：当前文件的所在目录
*   `amenity`设施数据集合

    * `affiliation`：当前集合的从属，为地点的`id`值
    * `amenity_list`：为从属于当前地点的全部设施以及场所的集合，该列表中的元素计划与Java部分的`Venue`类进行对应

    ### `amenity_list`

    该属性中拥有数据：
* id：为该场所（设施）的唯一标识符，用于地址查找与[路径生成](../../ji-yu-leaflet.js-de-shi-tu-sheng-cheng-mo-kuai/map\_view\_generator.py.md)
* name：场所名称
* type：OSM中POI的类型，可用于类型区分
* latitude：该地点的纬度，其与经度一起在最短路径生成中起到定位离该设施最近的路网节点的作用
* longitude：该地点的经度，作用如上



## `*_sr.pickle`

这个文件为`map_basket`变量的序列化（serialized）二进制文件，文件类型为`.pickle`，需使用`rb`模式读取

