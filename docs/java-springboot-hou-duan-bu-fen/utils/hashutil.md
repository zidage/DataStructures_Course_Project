# HashUtil

## HashUtil 类文档

### 包路径

`org.twentyEight.utils`

### 引入

* `java.nio.charset.StandardCharsets`

### 类描述

`HashUtil` 类提供了基于 FNV-1a 算法的哈希函数实现，用于生成字符串的64位哈希值。

#### 方法

**`public static long fnv1a64(String input)`**

* **描述**：使用 FNV-1a 哈希算法计算输入字符串的64位哈希值。
* **参数**：
  * `input`：输入的字符串。
* **返回值**：计算得到的64位哈希值。
* **实现细节**：
  * 初始化哈希基值为 `0xcbf29ce484222325L`。
  * 使用 FNV 素数 `0x100000001b3L`。
  * 对输入字符串的每个字节进行以下操作：
    1. 字节值与当前哈希值进行异或（XOR）操作。
    2. 哈希值乘以 FNV 素数。
  * 返回最终计算得到的哈希值。

### 使用示例

```java
String input = "example";
long hashValue = HashUtil.fnv1a64(input);
System.out.println("The FNV-1a 64-bit hash of '" + input + "' is: " + hashValue);

​
```
