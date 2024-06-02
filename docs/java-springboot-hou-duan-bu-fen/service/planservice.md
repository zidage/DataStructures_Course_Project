# PlanService

## PlanService 接口文档

`PlanService` 接口定义了与游学计划相关的业务逻辑操作。它包括创建计划、获取地点信息、删除计划、更新计划、列出地点和场馆、以及列出我的计划等功能。

### 方法说明

#### createPlanWithVenues(Plan plan, Long placeId, List\<Long> venueIds)

* **参数**：
  * `Plan plan` - 游学计划的基本信息。
  * `Long placeId` - 游学计划关联的地点ID。
  * `List<Long> venueIds` - 游学计划包含的场馆ID列表。
* **返回值**：无。
* **描述**：创建一个新的游学计划，包括计划的基本信息以及关联的地点和场馆。

#### getPlaceById(Long placeId)

* **参数**：`Long placeId` - 地点ID。
* **返回值**：`Place` - 根据ID查询得到的地点对象。
* **描述**：根据地点ID获取地点的详细信息。

#### deletePlan(Integer planId)

* **参数**：`Integer planId` - 计划ID。
* **返回值**：无。
* **描述**：删除指定ID的游学计划。

#### updatePlanWithVenues(Plan plan, Long placeId, List\<Long> venueIds)

* **参数**：
  * `Plan plan` - 需要更新的游学计划信息。
  * `Long placeId` - 游学计划关联的地点ID。
  * `List<Long> venueIds` - 游学计划包含的场馆ID列表。
* **返回值**：无。
* **描述**：更新游学计划的信息，包括基本信息、关联地点和场馆。

#### listPlace(Integer pageNum, Integer pageSize, String name, String address)

* **参数**：
  * `Integer pageNum` - 请求的页码。
  * `Integer pageSize` - 每页的大小。
  * `String name` - 地点名称，用于搜索。
  * `String address` - 地点地址，用于搜索。
* **返回值**：`PageBean<Place>` - 包含分页信息和当前页的地点列表。
* **描述**：分页列出地点，支持按名称和地址搜索。

#### listVenuesByPlaceId(Long placeId, Integer pageNum, Integer pageSize, String venueName, String type)

* **参数**：
  * `Long placeId` - 地点ID。
  * `Integer pageNum` - 请求的页码。
  * `Integer pageSize` - 每页的大小。
  * `String venueName` - 场馆名称，用于搜索。
  * `String type` - 场馆类型，用于搜索。
* **返回值**：`PageBean<Venue>` - 包含分页信息和当前页的场馆列表。
* **描述**：根据地点ID分页列出场馆，支持按名称和类型搜索。

#### listMyPlan(Integer pageNum, Integer pageSize, Long placeId, Integer planId, String planTitle)

* **参数**：
  * `Integer pageNum` - 请求的页码。
  * `Integer pageSize` - 每页的大小。
  * `Long placeId` - 地点ID，用于搜索。
  * `Integer planId` - 计划ID，用于搜索。
  * `String planTitle` - 计划标题，用于搜索。
* **返回值**：`PageBean<Plan>` - 包含分页信息和当前页的游学计划列表。
* **描述**：分页列出我的游学计划，支持按地点ID、计划ID和标题搜索。

#### listNearestVenuesByPlaceVenueId(Long placeId, Long venueId, String venueName, String type, Integer radius)

* **参数**：
  * `Long placeId` - 地点ID。
  * `Long venueId` - 场馆ID。
  * `String venueName` - 场馆名称，用于搜索。
  * `String type` - 场馆类型，用于搜索。
  * `Integer radius` - 搜索半径。
* **返回值**：`List<Venue>` - 符合条件的最近场馆列表。
* **描述**：根据地点ID、场馆ID、场馆名称、类型和搜索半径，列出最近的场馆。

### 使用场景

* 创建、更新和删除游学计划。
* 查询地点和场馆的信息。
* 分页列出地点、场馆和游学计划。
* 根据条件搜索地点、场馆和游学计划。

此接口为游学计划管理提供了一套完整的业务逻辑操作方法，方便在业务层进行调用和管理。
