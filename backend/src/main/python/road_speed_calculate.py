from datetime import datetime


current_time = datetime.now()

current_hour = current_time.hour

offset = 0

if 7 <= current_hour <= 9:
    offset = -5

street_types_dict = {
    'motorway': 70,  # 高速公路，限速70mph
    'trunk': 60,  # 主干道，限速60mph
    'primary': 50,  # 主要道路，限速50mph
    'secondary': 40,  # 次要道路，限速40mph
    'tertiary': 35,  # 三级道路，限速35mph
    'residential': 25,  # 居民区道路，限速25mph
    'service': 20,  # 服务道路，限速20mph
    'unclassified': 30,  # 未分类道路，限速30mph
    'road': 40,  # 一般道路，限速40mph
    'living_street': 20,  # 生活街道，限速20mph
    'pedestrian': 10,  # 步行街，限速10mph
    'track': 15,  # 小路，限速15mph
    'path': 10,  # 小径，限速10mph
    'footway': 5,  # 人行道，限速5mph
    'cycleway': 10,  # 自行车道，限速10mph
    'bridleway': 10,  # 马道，限速10mph
    'steps': 5,  # 台阶，限速5mph
    'corridor': 20,  # 走廊，限速20mph
    'proposed': 30,  # 拟议道路，限速30mph
    'construction': 30,  # 施工中道路，限速30mph
    'bus_guideway': 40,  # 公交车专用道，限速40mph
    'escape': 25,  # 逃生通道，限速25mph
    'raceway': 80,  # 赛车道，限速80mph
    'services': 20,  # 服务区道路，限速20mph
    'rest_area': 20,  # 休息区道路，限速20mph
    'abandoned': 20,  # 废弃道路，限速20mph
    'planned': 30,  # 计划中道路，限速30mph
}

# 对字典中的值进行加上偏移量
for key, value in street_types_dict.items():
    street_types_dict[key] = value + offset