import osmnx as ox
from os import environ
import pickle
from datetime import datetime
import heapq


root_dir = f'{environ["MAP_DATA"]}/map_exports_test'


def get_offset(transport):
    current_time = datetime.now()
    current_hour = current_time.hour
    offset = 0

    if 7 <= current_hour <= 9 or 16 <= current_hour <= 19:
        offset = -3 if transport == 'bike' else -1
    return offset 


def route_find(map_basket, orig, dest):
    orig_node = ox.nearest_nodes(map_basket["graph"], orig[0], orig[1])
    dest_node = ox.nearest_nodes(map_basket["graph"], dest[0], dest[1])

    return ox.shortest_path(map_basket["graph"], orig_node, dest_node, weight="length")


def heuristic(node, target):
    x1, y1 = node
    x2, y2 = target
    return abs(x1 - x2) + abs(y1 - y2)


def astar(adjacency_list, node_list, start, end):
    wts = {node: float('inf') for node in adjacency_list}
    wts[start] = 0
    heap = [(0, 0, start)]
    previous = {}

    while heap:
        _, current_g, current_node = heapq.heappop(heap)

        if current_node == end:
            break

        for neighbor, weight in adjacency_list[current_node].items():
            wt = current_g + weight
            if wt < wts[neighbor]:
                wts[neighbor] = wt
                f = wt + heuristic(node_list[neighbor], node_list[end])
                heapq.heappush(heap, (f, wt, neighbor))
                previous[neighbor] = current_node
    
    if end not in previous:
        return float('inf'), []
    
    path_node = []
    path_edges = []
    node = end
    while node != start:
        path_node.insert(0, node)
        path_edges.insert(0, previous[node])
        node = previous[node]
    path_node.insert(0, start)

    return wts[end], path_node, path_edges


def route_find_test(place, map_basket, orig, dest):
    orig_node = ox.nearest_nodes(map_basket["graph"], orig[0], orig[1])
    dest_node = ox.nearest_nodes(map_basket["graph"], dest[0], dest[1])
    adjacency_list, node_list = get_graph(place)
    wt, path, path_edges = astar(adjacency_list, node_list, orig_node, dest_node)
    
    return wt, path
    

def get_graph(place):
    with open(f"{root_dir}/{place}/{place}_sr.pickle", "rb") as f:
        transport = 'bike'
        offset = 0
        
        map_basket = pickle.load(f)
        nodes = ox.graph_to_gdfs(map_basket["graph"], edges=False).fillna("")
        edges = ox.graph_to_gdfs(map_basket["graph"], nodes=False).fillna("")
        # print(nodes.iloc[2].name)
        time_use = []
        for idx, rows in edges.iterrows():
            time_use.append(rows["length"] / 
                            (10 if transport == 'bike' else 2) - get_offset(transport) / 1000 / 3600)
        edges["time_use"] = time_use

        # print(edges["osmid"].head())

        adjacency_list = {}
        node_list = {}

        graph = ox.graph_from_gdfs(nodes, edges)


        for u, v, key, data in graph.edges(keys=True, data=True):
            if u is None or v is None:
                continue

            if u not in adjacency_list:
                adjacency_list[u] = {}
            
            if v not in adjacency_list[u]:
                adjacency_list[u][v] = data["time_use"]
                # print(adjacency_list[u][v])
        
        for id, data in graph.nodes(data=True):
            node_list[id] = (data['x'], data['y'])

        # print(node_list)

        return adjacency_list, node_list

