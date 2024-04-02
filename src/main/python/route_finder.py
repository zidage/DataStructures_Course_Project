import osmnx as ox


def route_find(map_basket, orig, dest):
    orig_node = ox.nearest_nodes(map_basket["graph"], orig[0], orig[1])
    dest_node = ox.nearest_nodes(map_basket["graph"], dest[0], dest[1])

    return ox.shortest_path(map_basket["graph"], orig_node, dest_node, weight="length")


