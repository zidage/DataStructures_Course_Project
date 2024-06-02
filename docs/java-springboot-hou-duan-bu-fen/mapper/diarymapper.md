# DiaryMapper

`DiaryMapper` 接口定义了操作日记（Diary）数据的MyBatis映射器。它包含了向数据库添加日记、查询日记列表、更新日记热度和评分等操作。

### 接口注解

* `@Mapper`：标记该接口为MyBatis的映射器接口。

### 方法定义

#### add

* **注解**：`@Insert`
* **SQL**：插入新的日记记录到数据库。
* **参数**：`Diary diary` - 要添加的日记对象。
* **返回值**：无。

#### list

* **参数**：`Integer userId`, `Integer planId`, `String state` - 分别代表用户ID、计划ID和日记状态。
* **返回值**：`List<Diary>` - 根据给定条件返回的日记列表。

#### listCommunity

* **参数**：`Long placeId` - 场所ID。
* **返回值**：`List<Diary>` - 根据给定的场所ID返回的社区日记列表。

#### getByDiaryId

* **参数**：`Integer id` - 日记ID。
* **返回值**：`Diary` - 根据给定ID返回的日记对象。

#### incrementPopularityByDiaryId

* **注解**：`@Update`
* **SQL**：更新指定日记ID的热度（popularity），每次调用热度加1。
* **参数**：`Integer diaryId` - 日记ID。
* **返回值**：无。

#### updateRating

* **注解**：`@Update`
* **SQL**：更新指定日记ID的评分（rating）和评分次数（ratingCount）。
* **参数**：
  * `Integer diaryId` - 日记ID。
  * `Double rating` - 新的评分。
  * `Integer ratingCount` - 新的评分次数。
* **返回值**：无。

### 注意事项

* `list`、`listCommunity`方法可能需要通过XML文件或使用注解的方式提供具体的SQL实现，这里未展示具体实现。
* 在实际应用中，确保传递给方法的参数符合数据库表结构和业务逻辑的要求。
* 为保证数据的一致性和完整性，对数据库的操作可能需要进行事务管理。
