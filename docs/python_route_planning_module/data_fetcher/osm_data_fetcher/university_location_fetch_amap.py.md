---
description: 作者：字禹润
---

# university\_location\_fetch\_amap.py

## Overview

该程序将从

```python
map_data/provinces.json
```

中获取省份信息，该省份信息来源：[https://github.com/modood/Administrative-divisions-of-China](https://github.com/modood/Administrative-divisions-of-China)

随后，利用高德Web基础服务API，查询该省份下所有高校的名称以及对应高德经纬度信息。目前该子程序由于涉及坐标转换等诸多难点，已被废弃。
