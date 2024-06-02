# MapUpdateService

## MapUpdateService 接口文档

`MapUpdateService` 接口定义了地图数据更新的业务逻辑操作。它包含了从磁盘导入JSON格式的地图数据文件的方法。

### 方法说明

#### importJsonFilesFromDisk(String directoryPath)

* **参数**：`String directoryPath` - 指向包含JSON文件的目录的路径。
* **返回值**：无。
* **描述**：此方法用于从指定的目录路径读取JSON格式的地图数据文件，并将这些数据导入到应用的数据存储中。

### 使用场景

* **地图数据更新**：当需要更新应用中使用的地图数据时，可以调用此方法。例如，地图应用或地理信息系统（GIS）可能需要定期从外部数据源更新地图数据，以保证显示的地图信息是最新的。

### 示例

假设有一个目录`/data/map_exports`，该目录下存储了多个JSON格式的地图数据文件。要导入这些数据文件，可以这样调用`importJsonFilesFromDisk`方法：

```
mapUpdateService.importJsonFilesFromDisk("/data/map_exports");
此方法将遍历/data/map_exports目录，读取所有JSON文件，并将文件中的地图数据导入到应用的数据存储中。
​
```
