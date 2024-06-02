# NearestVenueUtil

## NearestVenueUtil 类文档

### 包路径

`org.twentyEight.utils`

### 引入

* `org.twentyEight.pojo.Venue`
* `java.util.ArrayList`
* `java.util.Comparator`
* `java.util.List`
* `java.util.Scanner`
* `java.util.stream.Collectors`

### 类描述

`NearestVenueUtil` 类提供了一个静态方法 `findNearestVenues`，用于从给定的场所列表中找出位于用户指定搜索半径内的最近的场所列表，并按距离用户的远近排序。

#### 方法

**`public static List<Venue> findNearestVenues(List<Venue> venues, double userLat, double userLong, double searchRadius)`**

* **描述**：根据用户的位置和指定的搜索半径，从一组场所中筛选出位于该半径内的最近的场所，并按距离排序。
* **参数**：
  * `venues`：场所列表，`List<Venue>` 类型。
  * `userLat`：用户的纬度坐标。
  * `userLong`：用户的经度坐标。
  * `searchRadius`：搜索半径（单位：米）。
* **返回值**：筛选并排序后的场所列表，`List<Venue>` 类型。

### DistanceCalculator 类

#### 类描述

`DistanceCalculator` 类提供了一个静态方法 `calculateDistance`，用于计算两个地理坐标点之间的距离。

#### 方法

**`public static double calculateDistance(double startLat, double startLong, double endLat, double endLong)`**

* **描述**：计算两点之间的地理距离。
* **参数**：
  * `startLat`：起点纬度。
  * `startLong`：起点经度。
  * `endLat`：终点纬度。
  * `endLong`：终点经度。
* **返回值**：两点之间的距离（单位：米）。
* **实现细节**：使用Haversine公式计算地球上两点之间的距离。

### 使用示例

```java
List<Venue> venues = new ArrayList<>();
// 假设venues已经被初始化并填充了场所数据
double userLat = 34.052235; // 用户纬度
double userLong = -118.243683; // 用户经度
double searchRadius = 5000; // 搜索半径为5公里
​
List<Venue> nearestVenues = NearestVenueUtil.findNearestVenues(venues, userLat, userLong, searchRadius);
// nearestVenues 将包含在5公里内的所有场所，按距离排序
​
```
