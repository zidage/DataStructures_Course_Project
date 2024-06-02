# MapUpdateServiceImpl

## MapUpdateServiceImpl 类文档

### 包路径

`org.twentyEight.service.impl`

### 引入

* `com.fasterxml.jackson.databind.JsonNode`
* `com.fasterxml.jackson.databind.ObjectMapper`
* `org.springframework.beans.factory.annotation.Autowired`
* `org.springframework.stereotype.Service`
* `org.twentyEight.mapper.PlaceMapper`
* `org.twentyEight.mapper.VenueMapper`
* `org.twentyEight.pojo.Place`
* `org.twentyEight.pojo.Venue`
* `org.twentyEight.service.MapUpdateService`
* `org.twentyEight.utils.HashUtil`
* `java.io.File`
* `java.io.IOException`
* `java.nio.file.Files`
* `java.util.ArrayList`
* `java.util.Iterator`
* `java.util.List`
* `java.util.zip.CRC32`

### 类描述

`MapUpdateServiceImpl` 类实现了 `MapUpdateService` 接口，负责从磁盘导入JSON文件，并解析这些文件以更新地点（Place）和场所（Venue）信息到数据库。

#### 方法

**`public void importJsonFilesFromDisk(String directoryPath)`**

* **描述**：从指定目录导入JSON文件，并解析文件内容更新到数据库。
* **参数**：
  * `directoryPath`：一个 `String` 类型，表示包含JSON文件的目录路径。
* **实现细节**：
  1. 方法首先检查给定的目录路径是否为一个目录。
  2. 遍历目录下的所有子目录，寻找以 `.json` 结尾的文件。
  3. 对找到的每个JSON文件，使用 `ObjectMapper` 解析文件内容。
  4. 从JSON对象中读取地点（Place）和场所（Venue）的信息，并构造对应的对象。
  5. 使用 `PlaceMapper` 和 `VenueMapper` 将地点和场所的信息插入到数据库中。

**`private Long computeUniqueId(String name)`**

* **描述**：计算一个基于字符串的唯一ID。
* **参数**：
  * `name`：一个 `String` 类型，通常是地点的地址。
* **返回值**：`Long` 类型，表示计算出的唯一ID。
* **实现细节**：
  * 使用 `CRC32` 算法对字符串进行哈希计算，以生成一个唯一的长整型ID。

### 结论

`MapUpdateServiceImpl` 类通过解析磁盘上的JSON文件，自动化地更新数据库中的地点和场所信息，简化了数据更新和维护的过程。此类展示了如何结合文件处理、JSON解析及数据库操作技术来实现具体的业务需求。
