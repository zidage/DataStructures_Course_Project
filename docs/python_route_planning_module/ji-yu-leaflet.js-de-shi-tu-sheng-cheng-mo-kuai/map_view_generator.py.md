---
description: 作者：字禹润
---

# map\_view\_generator.py

## Overview

该模块将通过接收标准输入流中的字符串对请求进行处理，生成对应地图/路径的基于Leaflet.js的HTML视图。该模块的输出结果_**需有国际互联网访问支持**_。

## Usage

从该模块开始的模块将真正服务于用户端，故本文档对这类模块仅有usage说明。

统一的输入格式为：

```
-n place_name
-r place_name [way_points] ... 
```

* place\_name：该地点的规范化名称，与map\_exports中文件夹名一致。
* way\_points：地点内部设施的osmid，具体可从map\_exports中各地点文件夹下[`*_map.json`](../data\_fetcher/map\_data/data\_parser.md#map.json)中的[`amenity_list`](../data\_fetcher/map\_data/data\_parser.md#map.json)中查到。

在`-n`模式中，该程序仅会给出当前地点名为中心的视图，而在`-r`模式中，还可接收一系列路径点，从左至右的计算出相邻每对路径点之间的最短路径并输出一个带有路径的视图。

目前，该程序使用Java中的`ProcessBuilder`进行运行，具体请见Java部分的`MapViewGenerator.java`中的相关内容

以下是一个示例：

Input:

```
-r South_China_Agricultural_University 1091015028 205503549 614119144 9087904 613936879 957193796
```

Output:

```
html saved!
```

具体实现样式详见：

[https://zidage.github.io/HTML/map\_test/map\_test.html](https://zidage.github.io/HTML/map\_test/map\_test.html)

## Quirks

当前版本对每条路进行顺序区分仅能通过代码中的`route_color`变量进行，道路颜色将循环出现。

```python
route_color = ["#f94144", "#f3722c", "#f8961e", "#f9c74f",
               "#90be6d", "#43aa8b", "#577590"]
```

