# MapUpdateController

`MapUpdateController` 是一个Spring Boot控制器，专门负责处理地图数据更新的HTTP请求。它通过调用 `MapUpdateService`来实现从磁盘导入JSON格式的地图数据。

### 类注解

`@RestController`：标记该类为一个控制器，其中的方法返回值自动序列化为JSON。 `@RequestMapping("/MapUpdate")`：指定该控制器处理的所有请求的基础路径。

### 依赖注入

`@Autowired`：自动注入 `MapUpdateService` 的实例。

### API接口

#### 导入地图数据

* **路径**：/import
* **方法**：POST
* **描述**：从指定路径导入地图数据。路径是通过环境变量 `MAP_DATA` 获取，然后拼接 `/map_exports_test` 作为完整路径。
* **返回**：操作结果，如果导入成功，返回成功信息。

### 注意事项

该接口依赖于环境变量 `MAP_ DATA`，请确保在部署应用前配置此环境变量，并确保指定的路径下有待导入的JSON文件。 `Result` 类型用于统一API响应格式，确保前端可以统一处理成功和错误响应。 本文档描述的API接口仅为示例，实际使用时应根据具体业务需求进行调整。
