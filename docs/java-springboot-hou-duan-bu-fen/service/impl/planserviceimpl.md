# PlanServiceImpl

## PlanServiceImpl 类文档

### 包路径

`org.twentyEight.service.impl`

### 引入

* `com.github.pagehelper.Page`
* `com.github.pagehelper.PageHelper`
* `org.springframework.beans.factory.annotation.Autowired`
* `org.springframework.stereotype.Service`
* `org.twentyEight.mapper.PlaceMapper`
* `org.twentyEight.mapper.PlanMapper`
* `org.twentyEight.mapper.VenueMapper`
* `org.twentyEight.pojo.PageBean`
* `org.twentyEight.pojo.Place`
* `org.twentyEight.pojo.Plan`
* `org.twentyEight.pojo.Venue`
* `org.twentyEight.service.PlanService`
* `org.twentyEight.utils.MapViewGenerationUtil`
* `org.twentyEight.utils.NearestVenueUtil`
* `org.twentyEight.utils.ThreadLocalUtil`
* `java.time.LocalDateTime`
* `java.util.List`
* `java.util.Map`

### 类描述

`PlanServiceImpl` 实现了 `PlanService` 接口，提供了与旅行计划相关的业务逻辑处理，包括创建计划、删除计划、更新计划、列出地点、列出场所以及列出我的计划等功能。

#### 方法

**`public void createPlanWithVenues(Plan plan, Long placeId, List<Long> venueIds)`**

* **描述**：创建一个新的旅行计划，并关联指定的场所。
* **参数**：
  * `plan`：要创建的计划对象。
  * `placeId`：地点的ID。
  * `venueIds`：与计划关联的场所ID列表。

**`public Place getPlaceById(Long placeId)`**

* **描述**：根据地点ID获取地点详情。
* **参数**：
  * `placeId`：地点的ID。

**`public void deletePlan(Integer planId)`**

* **描述**：删除指定ID的计划。
* **参数**：
  * `planId`：计划的ID。

**`public void updatePlanWithVenues(Plan plan, Long placeId, List<Long> venueIds)`**

* **描述**：更新旅行计划及其关联的场所。
* **参数**：
  * `plan`：要更新的计划对象。
  * `placeId`：地点的ID。
  * `venueIds`：与计划关联的场所ID列表。

**`public PageBean<Place> listPlace(Integer pageNum, Integer pageSize, String name, String address)`**

* **描述**：分页列出符合条件的地点。
* **参数**：
  * `pageNum`：页码。
  * `pageSize`：每页大小。
  * `name`：地点名称。
  * `address`：地点地址。

**`public PageBean<Venue> listVenuesByPlaceId(Long placeId, Integer pageNum, Integer pageSize, String venueName, String type)`**

* **描述**：根据地点ID分页列出场所。
* **参数**：
  * `placeId`：地点的ID。
  * `pageNum`：页码。
  * `pageSize`：每页大小。
  * `venueName`：场所名称。
  * `type`：场所类型。

**`public PageBean<Plan> listMyPlan(Integer pageNum, Integer pageSize, Long placeId, Integer planId, String planTitle)`**

* **描述**：分页列出我的旅行计划。
* **参数**：
  * `pageNum`：页码。
  * `pageSize`：每页大小。
  * `placeId`：地点的ID。
  * `planId`：计划的ID。
  * `planTitle`：计划的标题。

**`public List<Venue> listNearestVenuesByPlaceVenueId(Long placeId, Long venueId, String venueName, String type, Integer radius)`**

* **描述**：列出指定场所附近的场所列表。
* **参数**：
  * `placeId`：地点的ID。
  * `venueId`：场所的ID。
  * `venueName`：场所名称。
  * `type`：场所类型。
  * `radius`：搜索半径。

#### 私有方法

**`private void updatePlanMapView(Plan plan, Long placeId, List<Long> venueIds)`**

* **描述**：更新计划的地图视图。
* **参数**：
  * `plan`：计划对象。
  * `placeId`：地点的ID。
  * `venueIds`：与计划关联的场所ID列表。

### 结论

`PlanServiceImpl` 类为应用提供了一系列管理旅行计划的方法，包括计划的创建、更新、删除以及与计划相关的地点和场所的列表展示功能。此外，还支持通过地图视图生成工具更新计划的地图视图，增强了用户体验。
