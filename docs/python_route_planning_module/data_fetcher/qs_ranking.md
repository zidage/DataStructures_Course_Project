---
description: 作者：字禹润
---

# qs\_ranking

### `qs_data.csv`

这是一个`.csv`文件，存储了2023年QS世界大学排名前1400名的学校，包括了他们的所在国家以及各种评测数据。数据来源：[https://github.com/Chemical118/QSCrawlProject](https://github.com/Chemical118/QSCrawlProject)

### `qs_process.py`

该文件将筛选世界大学前30名以及中国大学前100名（实际不足100名，该数据集前1400名中中国大学目前仅占70席）的数据

```python
top_30_WRD = df[~df['location code'].str.startswith('CN')]['institution'].head(30)
top_100_CN = df[df['location code'].str.startswith('CN')]['institution']
```

并将其名称集合存为`.json`文件

```python
top_30_WRD.to_json("map_data/catagory/world_university.json", orient="records", force_ascii=False, indent=4)
top_100_CN.to_json("map_data/catagory/china_university.json", orient="records", force_ascii=False, indent=4)
```
