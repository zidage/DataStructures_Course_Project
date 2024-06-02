# Plan

## Plan 类文档

`Plan` 类表示一个游学计划的实体，包含了计划的基本信息，如计划ID、创建者、标题、相关地点、交通方式、旅行策略、所需时间、地图视图以及创建和更新时间。

### 类注解

* `@Data`：Lombok库的注解，用于自动生成getter和setter方法，以及`toString`、`equals`和`hashCode`方法。

### 属性说明

* `id`：计划的唯一标识符，类型为`Integer`。
* `createUser`：创建计划的用户ID，类型为`Integer`。
* `title`：计划的标题，类型为`String`。
* `placeId`：计划相关的地点ID，类型为`Long`。
* `transport`：计划使用的交通方式，类型为`String`。
* `strategy`：旅行策略或建议，类型为`String`。
* `requiredTime`：完成计划所需的时间（单位未指定，可能为小时或天），类型为`Integer`。
* `mapView`：计划的地图视图或路线图，类型为`String`。可能是一个URL链接或地图服务的标识符。
* `createTime`：计划创建的时间，类型为`LocalDateTime`。
* `updateTime`：计划最后更新的时间，类型为`LocalDateTime`。

### 使用场景

`Plan` 类主要用于存储和展示游学计划的详细信息。例如，在一个旅行规划应用中，用户可以创建、查看、编辑和分享他们的游学计划。

### 示例

```java
Plan plan = new Plan();
plan.setTitle("我的游学计划");
plan.setTransport("公共交通");
plan.setStrategy("参观历史遗迹，体验当地美食");
plan.setRequiredTime(7);
// 设置其他属性
此类通过提供详细的计划信息，帮助用户更好地规划和管理他们的旅行。
​
```
