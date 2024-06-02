# PlaceMapper

`PlaceMapper` 接口定义了操作地点（Place）数据的MyBatis映射器。它包括了向数据库插入地点、通过ID查询地点、列出符合条件的地点列表等操作。

### 接口注解

* `@Mapper`：标记该接口为MyBatis的映射器接口，MyBatis通过这个注解自动寻找对应的Mapper XML文件或解析注解定义的SQL。

### 方法定义

#### insertPlace

* **注解**：`@Insert`
* **SQL**：向数据库的`place`表插入一条新记录。
* **参数**：`Place place` - 要插入的地点对象，包含地点的各项属性。
* **返回值**：无返回值。操作成功后，`place`对象的ID将被自动设置为数据库生成的ID。

#### findPlaceById

* **注解**：`@Select`
* **SQL**：根据地点ID查询对应的地点记录。
* **参数**：`Long id` - 要查询的地点ID。
* **返回值**：`Place` - 查询到的地点对象。如果没有找到对应的记录，返回`null`。

#### list

* **参数**：`String placeName`, `String address` - 分别代表地点名称和地址的查询条件。
* **返回值**：`List<Place>` - 根据给定条件返回的地点列表。此方法的实现需要在Mapper XML文件中提供，这里未直接使用注解定义SQL。

### 注意事项

* `insertPlace`方法中的SQL语句包含了全部的字段，确保插入操作时能够提供完整的地点信息。
* `findPlaceById`方法提供了基础的ID查询功能，是许多业务逻辑的基础。
* `list`方法的具体实现依赖于MyBatis Mapper XML配置，需要在XML文件中定义对应的`<select>`元素和SQL语句。这里假设该方法用于根据地点名称和地址进行模糊查询。
* 在实际应用中，可能需要根据业务需求调整或扩展这些方法，例如增加分页处理、查询条件等。
