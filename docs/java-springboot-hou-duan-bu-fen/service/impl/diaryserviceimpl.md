# DiaryServiceImpl

## DiaryServiceImpl 类文档

### 包路径

`org.twentyEight.service.impl`

### 引入

* `com.github.pagehelper.Page`
* `com.github.pagehelper.PageHelper`
* `org.springframework.beans.factory.annotation.Autowired`
* `org.springframework.stereotype.Service`
* `org.twentyEight.mapper.DiaryMapper`
* `org.twentyEight.pojo.Diary`
* `org.twentyEight.pojo.PageBean`
* `org.twentyEight.service.DiaryService`
* `org.twentyEight.utils.ThreadLocalUtil`
* `java.time.LocalDateTime`
* `java.util.List`
* `java.util.Map`

### 类描述

`DiaryServiceImpl` 类实现了 `DiaryService` 接口，提供了日记管理的具体实现方法，包括添加日记、列出日记列表、获取特定日记、增加日记的人气值以及更新日记评分等功能。

#### 方法

**`public void add(Diary diary)`**

* **描述**：添加一个新的日记记录到数据库。
* **参数**：
  * `diary`：一个 `Diary` 对象，包含了日记的各项信息。
* **实现细节**：方法首先为 `diary` 对象设置创建和更新时间，然后从 `ThreadLocal` 中获取当前用户的ID作为创建者ID，并调用 `DiaryMapper` 的 `add` 方法将日记添加到数据库。

**`public PageBean<Diary> list(Integer pageNum, Integer pageSize, Integer placeId, String state)`**

* **描述**：根据地点ID和状态列出日记列表，支持分页。
* **参数**：
  * `pageNum`：页码。
  * `pageSize`：每页显示的记录数。
  * `placeId`：地点ID。
  * `state`：日记的状态。
* **返回值**：`PageBean<Diary>`，包含了分页信息和当前页的日记列表。

**`public PageBean<Diary> listCommunity(Integer pageNum, Integer pageSize, Long placeId)`**

* **描述**：列出社区日记列表，支持分页。
* **参数**：
  * `pageNum`：页码。
  * `pageSize`：每页显示的记录数。
  * `placeId`：社区地点ID。
* **返回值**：`PageBean<Diary>`，包含了分页信息和当前页的日记列表。

**`public Diary getByDiaryId(Integer diaryId)`**

* **描述**：根据日记ID获取日记详情。
* **参数**：
  * `diaryId`：日记ID。
* **返回值**：`Diary`，对应ID的日记详情。

**`public void incrementPopularityByDiaryId(Integer diaryId)`**

* **描述**：根据日记ID增加日记的人气值。
* **参数**：
  * `diaryId`：日记ID。

**`public void updateRating(Diary diary)`**

* **描述**：更新日记的评分。
* **参数**：
  * `diary`：一个 `Diary` 对象，包含了日记ID、新的评分和评分次数。

### 结论

`DiaryServiceImpl` 类通过实现 `DiaryService` 接口，为日记管理提供了一套完整的业务逻辑处理方法，包括但不限于添加日记、查询日记列表、更新日记信息等功能。
