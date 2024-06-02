# Place

## Place 类文档

`Place` 类定义了地点实体的结构，用于表示一个具体的地点信息，如景点、餐馆等。它包含地点的基本信息，例如名称、人气值、评分、地址、描述以及相关图片。

### 类注解

* `@Data`：Lombok库的注解，用于自动生成getter和setter方法，以及`toString`、`equals`和`hashCode`方法。

### 属性说明

* `id`：地点的唯一标识符，类型为`Long`。
* `name`：地点的名称，类型为`String`。
* `popularity`：地点的人气值，类型为`Integer`。通常用于表示地点的受欢迎程度。
* `rating`：地点的评分，类型为`Double`。反映了用户对地点的整体评价。
* `formattedName`：地点的格式化名称，类型为`String`。可能包含官方名称或别名。
* `address`：地点的地址，类型为`String`。
* `description`：地点的描述信息，类型为`String`。提供了关于地点的详细介绍。
* `images`：地点相关的图片，类型为`String`。使用`@TableField`注解指定了`typeHandler`为`JacksonTypeHandler`，允许将JSON字符串与Java对象进行互转，方便存储和读取图片信息。

### 使用场景

`Place` 类主要用于存储和展示地点相关的信息。例如，在旅游指南应用中，`Place` 可以用来展示不同景点的详细信息，包括名称、地址、描述、评分和图片等。

### 示例

```java
Place place = new Place();
place.setName("故宫博物院");
place.setAddress("北京市东城区景山前街4号");
place.setDescription("故宫，又称紫禁城，位于北京中轴线的中心...");
place.setRating(4.9);
place.setPopularity(9999);
// 设置其他属性
​
```
