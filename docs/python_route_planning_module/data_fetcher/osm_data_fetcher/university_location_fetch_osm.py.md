---
description: 作者：字禹润
---

# university\_location\_fetch\_osm.py

## Overview

该子程序目前主要的地图数据获取方式。程序将通过谷歌API获取地点的格式化文本信息并存储为`.json`文件。然后用[`OSMnx`](https://osmnx.readthedocs.io/en/stable/)查询目标地点的相关GIS数据，并保存为`.gpkg`文件。

下面的内容将对程序代码中一些重点部分进行解释

## 常量变量

### `google_api_url`

类型：`string`

该变量为`Google Place API`的请求`URL`，请求格式为：

```python
https://maps.googleapis.com/maps/api/place/textsearch/json?query={query}&language=zh-CN&key={API_KEY}
```

其中，`query`为地点名称，规定了获取信息的语言为`zh-CN`，并规定了使用的`API_KEY`

该变量内`URL`的详细使用方法详见：[https://developers.google.com/maps/documentation/places/web-service/search-text](https://developers.google.com/maps/documentation/places/web-service/search-text)

### `input_file_path`

类型：`list`

该变量存储了一系列文件地址，这些文件位于`map_data/category`中，为名录文件，例如：欲获取地点的地点名录

### `query_formal_name`

类型：`string`

该变量为`query`变量中空格替换为下划线后的版本

```python
query_formal_name = query.replace(' ', '_')
```

### 存储目录

```python
gpkg_features_path = 'map_data/university_map/{query}/{query}_{feature}.gpkg'
gm_features_path = 'map_data/university_map/{query}/{query}_{feature}.graphml'
info_file_path = 'map_data/university_map/{query}/{query}_info.json'
```

其中，`gpkg_features_path`为`.gpkg`文件的统一存储地址，`gm_features_path`为`.graphml`的统一存储地址，`info_file_path`为基本信息`json`文件的统一存储地址

## 谷歌数据获取部分

在这个部分中，将使用`Google Place API`获取地点的如下信息：

* 中文名
* 格式化地址 (formatted address)
* 评分

然后这些数据将被存储到`info_file_path`中为`*_info.json`文件，该文件示例如下：

```json
{
    "university": {
        "name": "北京邮电大学",
        "address": "中国北京市海淀区北太平庄西土城路10号 邮政编码: 100876",
        "rating": 4.6
    }
}
```

## 路网数据获取部分

将使用OSMnx中的`graph_from_address`获取一个NetworkX中的`MultiDiGraph`对象，作为查询地点的路网数据集，该数据集将用于路径规划功能。

```python
graph = ox.graph_from_address(query, dist=2000)
```

其中，`dist`参数规定了数据的获取半径，此处为2000m

根据OSMnx官方教程中的说法，该数据文件适合被存储为`.graphml`文件

```python
ox.save_graphml(graph, filepath=gm_features_path.format(
                    query=query_formal_name, feature='graph'))  # Graph类型数据须存储作.graphml格式，避免坐标系混乱
```

## 区域获取

该部分会获取查询地点的所占区域，该功能目前展示没有作用

## 建筑物获取

将使用OSMnx中的`features_from_place`方法获取该请求地点内的所有建筑物，返回一个`GeoDataFrame`。为避免重新读取时造成`GeoDataFrame`构造器识别格式冲突，需修改当前`GeoDataFrame`

```python
buildings = buildings.apply(lambda c: c.astype(
                    str) if c.name != "geometry" else c, axis=0)
```

## 设施获取

同建筑获取，这里也需要修改当前`GeoDataFrame`

```python
amenity = amenity.apply(lambda c: c.astype(str) if c.name != "geometry" else c, axis=0)
```
