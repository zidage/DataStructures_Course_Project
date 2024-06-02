# Diary

`VenueMapper` 接口定义了操作场馆（Venue）数据的MyBatis映射器。它包括了向数据库添加场馆、根据场所ID查询场馆、列出符合条件的场馆列表、根据场馆ID查询场馆详情、增加场馆人气等操作。

### 接口注解

* `@Mapper`：标记该接口为MyBatis的映射器接口。

### 方法定义

#### insertVenue

* **注解**：`@Insert`
* **SQL**：向数据库的`venue`表插入一条新记录。
* **参数**：`Venue venue` - 要添加的场馆对象。
* **返回值**：无。操作成功后，新场馆记录将被插入到数据库中。

#### findVenuesByPlaceId

* **注解**：`@Select`
* **SQL**：根据场所ID查询相关的所有场馆记录。
* **参数**：`Long placeId` - 场所ID。
* **返回值**：`List<Venue>` - 查询到的场馆对象列表。

#### listByPlaceId

* **参数**：`Long placeId`, `String venueName`, `String type` - 分别代表场所ID、场馆名称和场馆类型的查询条件。
* **返回值**：`List<Venue>` - 根据给定条件返回的场馆列表。此方法的实现需要在Mapper XML文件中提供，这里未直接使用注解定义SQL。

#### findVenueByVenueId

* **注解**：`@Select`
* **SQL**：根据场馆ID查询对应的场馆记录。
* **参数**：`Long venueId` - 场馆ID。
* **返回值**：`Venue` - 查询到的场馆对象。如果没有找到对应的记录，返回`null`。

#### incrementPopularityByVenueId

* **注解**：`@Update`
* **SQL**：更新指定场馆ID的人气（popularity），每次调用人气加1。
* **参数**：`Long venueId` - 场馆ID。
* **返回值**：无。

### 注意事项

* `listByPlaceId`方法可能需要通过XML文件或使用注解的方式提供具体的SQL实现，这里未展示具体实现。
* 在实际应用中，确保传递给方法的参数符合数据库表结构和业务逻辑的要求。

### 示例

```java
Diary diary = new Diary();
diary.setTitle("我的游学日记");
diary.setContent("今天是游学的第一天...");
// 设置其他属性
```
