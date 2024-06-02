# route\_finder.py

### 简介

该文档描述了一个基于`osmnx`和A\*算法的路径优化模块。此模块用于查找和优化不同运输方式（步行或骑行）的路径，并根据策略（距离优先或时间优先）选择最佳路线。

### 安装和依赖

要运行该模块，需安装以下依赖项：

* osmnx
* route\_optimizer
* datetime
* heapq

可以使用以下命令安装`osmnx`：

```
pip install osmnx
```

### 常量定义

```
DISTANCE_FIRST = 0
TIME_FIRST = 1
WALK = 0
BIKE = 1
```

* `DISTANCE_FIRST`：距离优先策略
* `TIME_FIRST`：时间优先策略
* `WALK`：步行模式
* `BIKE`：骑行模式

### 环境变量

```
root_dir = f'{environ["MAP_DATA"]}/map_exports_test'
```

* `MAP_DATA`：地图数据的环境变量路径

### 函数说明

#### `get_offset(transport)`

根据当前时间和运输方式获取偏移量。

```
def get_offset(transport):
    current_time = datetime.now()
    current_hour = current_time.hour
    offset = 0
​
    if 7 <= current_hour <= 9 or 16 <= current_hour <= 19:
        offset = -3 if transport == 'bike' else -1
    return offset 
```

* `transport`：运输方式（'bike'或其他）
* 返回值：偏移量

#### `route_find(map_basket, orig, dest)`

查找从起点到终点的最短路径。

```
python复制代码def route_find(map_basket, orig, dest):
    orig_node = ox.nearest_nodes(map_basket["graph"], orig[0], orig[1])
    dest_node = ox.nearest_nodes(map_basket["graph"], dest[0], dest[1])
​
    return ox.shortest_path(map_basket["graph"], orig_node, dest_node, weight="length")
```

* `map_basket`：包含地图数据的字典
* `orig`：起点坐标（经纬度）
* `dest`：终点坐标（经纬度）
* 返回值：最短路径节点列表

#### `heuristic(node, target, strategy)`

启发函数，用于A\*算法。

```
def heuristic(node, target, strategy):
    x1, y1 = node
    x2, y2 = target
    return 0 if strategy == TIME_FIRST else abs(x1 - x2) + abs(y1 - y2)
```

* `node`：当前节点坐标
* `target`：目标节点坐标
* `strategy`：策略（DISTANCE\_FIRST或TIME\_FIRST）
* 返回值：启发值

#### `astar(adjacency_list, node_list, start, end, strategy=DISTANCE_FIRST, transport=WALK)`

使用A\*算法查找最优路径。

```
def astar(adjacency_list, node_list, start, end, strategy=DISTANCE_FIRST, transport=WALK):
    wts = {node: float('inf') for node in adjacency_list}
    wts[start] = 0
    heap = [(0, 0, start)]
    previous = {}
​
    if start == end:
        return 0, [start]
​
    weight_select = 0 if strategy == DISTANCE_FIRST else strategy + transport
​
    while heap:
        _, current_g, current_node = heapq.heappop(heap)
​
        if current_node == end:
            break
​
        for neighbor, weight in adjacency_list[current_node].items():
            try:
                wt = current_g + weight[weight_select]
                if wt < wts[neighbor]:
                    wts[neighbor] = wt
                    f = wt + heuristic(node_list[neighbor], node_list[end], strategy)
                    heapq.heappush(heap, (f, wt, neighbor))
                    previous[neighbor] = current_node
            except:
                continue
    
    if end not in previous:
        return float('inf'), []
    
    path_node = []
    node = end
    while node != start:
        path_node.insert(0, node)
        node = previous[node]
    path_node.insert(0, start)
​
    return wts[end], path_node
```

* `adjacency_list`：邻接表，表示图的结构
* `node_list`：节点列表
* `start`：起点节点
* `end`：终点节点
* `strategy`：策略（DISTANCE\_FIRST或TIME\_FIRST）
* `transport`：运输方式（WALK或BIKE）
* 返回值：最小权重和路径节点列表

#### `route_find_test(place, map_basket, waypoints, strategy=DISTANCE_FIRST, transport=WALK)`

测试路径查找功能。

```
def route_find_test(place, map_basket, waypoints, strategy=DISTANCE_FIRST, transport=WALK):
    optimize_route(map_basket, waypoints, strategy, transport)
    
    route = []
    for i in range(len(waypoints) - 1):
        orig_node = ox.nearest_nodes(map_basket["graph"], waypoints[i][0], waypoints[i][1])
        dest_node = ox.nearest_nodes(map_basket["graph"], waypoints[i + 1][0], waypoints[i + 1][1])
        wt, path = astar(map_basket["adj_list"], map_basket["nd_list"], orig_node, dest_node, strategy, transport)
        route.append((path, wt))
    
    return route
```

* `place`：地点名称
* `map_basket`：包含地图数据的字典
* `waypoints`：途径点列表（坐标）
* `strategy`：策略（DISTANCE\_FIRST或TIME\_FIRST）
* `transport`：运输方式（WALK或BIKE）
* 返回值：路径列表和权重

### 使用示例

```
# 假设已加载map_basket和waypoints
place = "some_place"
strategy = DISTANCE_FIRST
transport = WALK
route = route_find_test(place, map_basket, waypoints, strategy, transport)
print(route)
```
