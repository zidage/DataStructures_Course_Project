# route\_optimizer.py

### 简介

该文档描述了一个基于`osmnx`、`networkx`和A\*算法的路径优化模块。此模块用于查找和优化不同运输方式（步行或骑行）的路径，并根据策略（距离优先或时间优先）选择最佳路线。它还实现了基于Christofides算法的旅行商问题（TSP）解决方案。

### 安装和依赖

要运行该模块，需安装以下依赖项：

* osmnx
* networkx
* heapq

可以使用以下命令安装所需库：

```bash
pip install osmnx networkx
```

### 常量定义

```python
DISTANCE_FIRST = 0
TIME_FIRST = 1
WALK = 0
BIKE = 1
```

* `DISTANCE_FIRST`：距离优先策略
* `TIME_FIRST`：时间优先策略
* `WALK`：步行模式
* `BIKE`：骑行模式

### 函数说明

#### `heuristic(node, target, strategy)`

启发函数，用于A\*算法。

```python
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

```python
def astar(adjacency_list, node_list, start, end, strategy=DISTANCE_FIRST, transport=WALK):
    wts = {node: float('inf') for node in adjacency_list}
    wts[start] = 0
    heap = [(0, 0, start)]
    previous = {}

    if start == end:
        return 0, [start]

    weight_select = 0 if strategy == DISTANCE_FIRST else strategy + transport

    while heap:
        _, current_g, current_node = heapq.heappop(heap)

        if current_node == end:
            break

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

    return wts[end], path_node
```

* `adjacency_list`：邻接表，表示图的结构
* `node_list`：节点列表
* `start`：起点节点
* `end`：终点节点
* `strategy`：策略（DISTANCE\_FIRST或TIME\_FIRST）
* `transport`：运输方式（WALK或BIKE）
* 返回值：最小权重和路径节点列表

#### `optimize_route(map_basket, waypoints, strategy, transport)`

优化途径点路径。

```python
def optimize_route(map_basket, waypoints, strategy, transport):
    tsp_graph = []
    wpt = []

    adj_list = map_basket["adj_list"]
    nd_list = map_basket["nd_list"]

    for i in range(len(waypoints) - 1):
        wpt_a = waypoints[i]
        for j in range(i + 1, len(waypoints)):
            wpt_b = waypoints[j]
            orig_node = ox.nearest_nodes(map_basket["graph"], wpt_a[0], wpt_a[1])
            dest_node = ox.nearest_nodes(map_basket["graph"], wpt_b[0], wpt_b[1])
            wt, path = astar(adj_list, nd_list, orig_node, dest_node, strategy, transport)
            if wt == 0:
                return None
            tsp_graph.append((wpt_a[-1], wpt_b[-1], wt, path))
        wpt.append(int(wpt_a[-1]))
    wpt.append(int(waypoints[-1][-1]))
    
    G = nx.Graph()

    for u, v, w, _ in tsp_graph:
        G.add_edge(u, v, weight=w)
    
    try:
        optimized_wpt, _ = christofides_tsp(G)
        return optimized_wpt[:-1]
    except:
        return None
```

* `map_basket`：包含地图数据的字典
* `waypoints`：途径点列表（坐标）
* `strategy`：策略（DISTANCE\_FIRST或TIME\_FIRST）
* `transport`：运输方式（WALK或BIKE）
* 返回值：优化后的途径点列表

#### `christofides_tsp(G)`

使用Christofides算法解决TSP问题。

```python
def christofides_tsp(G):
    # Step 1: Compute the Minimum Spanning Tree (MST)
    mst = nx.minimum_spanning_tree(G, weight='weight')

    # Step 2: Find vertices with odd degree in the MST
    odd_degree_nodes = [v for v, degree in mst.degree() if degree % 2 == 1]

    # Step 3: Find the Minimum Weight Matching on odd degree nodes
    subgraph = G.subgraph(odd_degree_nodes)
    min_weight_matching = nx.algorithms.matching.min_weight_matching(subgraph, weight='weight')

    # Step 4: Combine MST and Minimum Weight Matching to form an Eulerian circuit
    multi_graph = nx.MultiGraph(mst)
    multi_graph.add_edges_from(min_weight_matching)

    # Step 5: Find an Eulerian circuit in the multi-graph
    eulerian_circuit = list(nx.eulerian_circuit(multi_graph))

    # Step 6: Convert Eulerian circuit to Hamiltonian circuit by skipping visited nodes
    visited = set()
    path = []
    for u, v in eulerian_circuit:
        if u not in visited:
            path.append(u)
            visited.add(u)
    path.append(path[0])  # Return to start

    # Calculate total distance of the path
    total_distance = sum(G[u][v]['weight'] for u, v in zip(path[:-1], path[1:]))

    return path, total_distance
```

* `G`：网络图
* 返回值：最优路径和总距离

### 使用示例

```python
python复制代码# 假设已加载map_basket和waypoints
strategy = DISTANCE_FIRST
transport = WALK
optimized_waypoints = optimize_route(map_basket, waypoints, strategy, transport)
print(optimized_waypoints)
```
