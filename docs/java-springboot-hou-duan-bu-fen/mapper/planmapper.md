# PlanMapper

`PlanMapper` 接口定义了操作计划（Plan）数据的MyBatis映射器。它包括了向数据库添加计划、关联计划与场馆、删除计划、更新计划信息、列出计划列表等操作。

### 接口注解

* `@Mapper`：标记该接口为MyBatis的映射器接口。

### 方法定义

#### insertPlan

* **注解**：`@Insert`, `@Options`
* **SQL**：向数据库的`plan`表插入一条新记录，并且使用自动生成的键（通常是自增ID）。
* **参数**：`Plan plan` - 要添加的计划对象。
* **返回值**：无。操作成功后，`plan`对象的`id`属性将被设置为数据库生成的ID。

#### insertPlanVenues

* **注解**：`@Insert`
* **SQL**：使用MyBatis的动态SQL功能，向`plan_venue`表批量插入计划与场馆的关联数据。
* **参数**：
  * `Integer planId` - 计划ID。
  * `List<Long> venueIds` - 要关联的场馆ID列表。
* **返回值**：无。

#### deletePlanVenuesByPlanId

* **注解**：`@Delete`
* **SQL**：删除指定计划ID的所有场馆关联数据。
* **参数**：`Integer planId` - 计划ID。
* **返回值**：无。

#### deletePlan

* **注解**：`@Delete`
* **SQL**：删除指定ID的计划。
* **参数**：`Integer id` - 计划ID。
* **返回值**：无。

#### updatePlan

* **注解**：`@Update`
* **SQL**：更新计划的标题、交通方式和更新时间。
* **参数**：`Plan plan` - 要更新的计划对象。
* **返回值**：无。

#### listPlan

* **参数**：`Integer userId`, `Integer planId`, `Long placeId`, `String planTitle` - 分别代表用户ID、计划ID、地点ID和计划标题的查询条件。
* **返回值**：`List<Plan>` - 根据给定条件返回的计划列表。

#### insertPlanMapViewAndTime

* **注解**：`@Update`
* **SQL**：更新计划的地图视图路径和所需时间。
* **参数**：
  * `Integer id` - 计划ID。
  * `String savePath` - 地图视图保存路径。
  * `int requiredTime` - 计划所需时间。
* **返回值**：无。

### 注意事项

* `insertPlan`方法通过`@Options`注解的`useGeneratedKeys = true`属性来指定使用数据库自动生成的键值，并通过`keyProperty`属性指定这个键值设置回哪个属性。
* `insertPlanVenues`方法展示了如何使用MyBatis的`<script>`标签和`<foreach>`标签来实现批量插入操作。
* 在实际应用中，可能需要根据业务需求调整这些方法的定义，例如增加更多的查询条件或更新字段。
