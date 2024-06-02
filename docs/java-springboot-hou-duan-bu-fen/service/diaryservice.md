# DiaryService

`DiaryService` 接口定义了与游学日记相关的业务逻辑操作。它包括新增日记、分页查询日记列表、获取特定日记详情、增加日记的人气值以及更新日记的评分等功能。

### 方法说明

#### add(Diary diary)

* **参数**：`Diary diary` - 要新增的日记对象。
* **返回值**：无。
* **描述**：新增一个日记到数据库中。

#### list(Integer pageNum, Integer pageSize, Integer planId, String state)

* **参数**：
  * `Integer pageNum` - 请求的页码。
  * `Integer pageSize` - 每页的大小。
  * `Integer planId` - 日记所属的游学计划ID，可为`null`。
  * `String state` - 日记的状态，可为`null`。
* **返回值**：`PageBean<Diary>` - 包含分页信息和当前页的日记列表。
* **描述**：根据给定的条件分页查询日记列表。

#### listCommunity(Integer pageNum, Integer pageSize, Long placeId)

* **参数**：
  * `Integer pageNum` - 请求的页码。
  * `Integer pageSize` - 每页的大小。
  * `Long placeId` - 地点ID，用于筛选与特定地点相关的日记。
* **返回值**：`PageBean<Diary>` - 包含分页信息和当前页的社区日记列表。
* **描述**：分页查询与特定地点相关的社区日记列表。

#### getByDiaryId(Integer diaryId)

* **参数**：`Integer diaryId` - 日记的ID。
* **返回值**：`Diary` - 根据ID查询得到的日记对象，如果不存在则返回`null`。
* **描述**：根据日记ID获取日记的详细信息。

#### incrementPopularityByDiaryId(Integer diaryId)

* **参数**：`Integer diaryId` - 日记的ID。
* **返回值**：无。
* **描述**：根据日记ID增加日记的人气值。

#### updateRating(Diary diary)

* **参数**：`Diary diary` - 需要更新评分的日记对象。
* **返回值**：无。
* **描述**：更新日记的评分和评分次数。

### 使用场景

* **新增日记**：用户创建新的游学日记时调用 `add` 方法。
* **浏览日记**：用户浏览日记列表或社区日记列表时，分别调用 `list` 和 `listCommunity` 方法进行分页查询。
* **查看日记详情**：用户查看特定日记的详细信息时，调用 `getByDiaryId` 方法。
* **互动操作**：用户对日记进行评分或增加日记人气时，分别调用 `updateRating` 和 `incrementPopularityByDiaryId` 方法。

此接口为游学日记功能提供了数据操作的抽象定义，方便在业务逻辑层进行调用和管理。
