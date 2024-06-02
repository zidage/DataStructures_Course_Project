# DiaryController

`DiaryController` 是一个Spring Boot控制器，负责处理与日记相关的HTTP请求。它依赖于 DiaryService 来执行业务逻辑操作。

### 类注解

* `@RestController`：标记该类为一个控制器，其中的方法返回值自动序列化为JSON。
* `@RequestMapping("/diary")`：指定该控制器处理的所有请求的基础路径。

### 依赖注入

`@Autowired`：自动注入 DiaryService 的实例。

### API接口

#### 添加日记

* **路径**：/newDiary
* **方法**：POST
* **参数**：Diary diary（通过请求体传入）
* **描述**：添加一个新的日记条目。
* **返回**：操作结果，如果成功，返回成功信息。

#### 获取日记详情

* **路径**：/{diaryId}
* **方法**：GET
* **参数**：diaryId（路径变量）
* **描述**：根据日记ID获取日记详情，并增加日记的流行度。
* **返回**：操作结果，如果日记存在，返回日记详情；如果不存在，返回错误信息。

#### 评分日记

* **路径**：/{diaryId}/rating
* **方法**：PUT
* **参数**：
  * diaryId（路径变量）
  * rating（请求参数）
* **描述**：为指定ID的日记打分。
* **返回**：操作结果，如果日记存在并更新评分成功，返回成功信息；如果日记不存在，返回错误信息。

#### 我的日记列表

* **路径**：/myDiaries
* **方法**：GET
* **参数**：
  * pageNum（请求参数）
  * pageSize（请求参数）
  * planId（请求参数，可选）
  * state（请求参数，可选）
* **描述**：获取用户自己的日记列表，支持分页和按计划ID、状态过滤。
* **返回**：操作结果，返回分页后的日记列表。

#### 社区日记列表

* **路径**：/community
* **方法**：GET
* **参数**：
  * pageNum（请求参数）
  * pageSize（请求参数）
  * title（请求参数，可选）
  * placeId（请求参数，可选）
* **描述**：获取社区的日记列表，支持分页和按标题、地点ID过滤。
* **返回**：操作结果，返回分页后的日记列表。

### 私有方法

#### 增加日记流行度

* **方法签名**：`private void incrementPopularityByDiaryId(Integer diaryId)`
* **描述**：根据日记ID增加日记的流行度。此方法不对外暴露。
