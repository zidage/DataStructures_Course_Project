# Venue

## Venue 类文档

`Venue` 类定义了场馆实体的结构，包含了场馆的基本信息，如场馆ID、名称、类型、地理位置（纬度和经度）、所属地点ID、人气值以及OpenStreetMap的ID（osmid）。

### 类注解

* `@Data`：Lombok库的注解，用于自动生成getter和setter方法，以及`toString`、`equals`和`hashCode`方法。

### 属性说明

* `id`：场馆的唯一标识符，类型为`Long`。
* `name`：场馆的名称，类型为`String`。
* `type`：场馆的类型，类型为`String`。例如，餐馆、博物馆、公园等。
* `latitude`：场馆的纬度，类型为`Double`。用于标识场馆的地理位置。
* `longitude`：场馆的经度，类型为`Double`。用于标识场馆的地理位置。
* `placeId`：场馆所属地点的ID，类型为`Long`。作为外键，关联到特定的地点。
* `popularity`：场馆的人气值，类型为`Integer`。用于表示场馆的受欢迎程度或访问量。
* `osmid`：场馆在OpenStreetMap上的唯一标识符，类型为`Long`。用于关联到OpenStreetMap上的具体位置信息。

### 使用场景

`Venue` 类主要用于存储和展示场馆的详细信息。例如，在旅游指南应用中，`Venue` 可以用来展示不同场馆的名称、类型、位置、人气等信息。

### 示例

```java
Venue venue = new Venue();
venue.setId(1L);
venue.setName("国家博物馆");
venue.setType("博物馆");
venue.setLatitude(39.9042);
venue.setLongitude(116.4074);
venue.setPlaceId(10L);
venue.setPopularity(1000);
venue.setOsmid(123456789L);
此类通过提供场馆的详细信息，帮助用户更好地了解和访问不同的场馆。

```
