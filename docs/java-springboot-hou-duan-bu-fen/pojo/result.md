# Result

## Result 类文档

`Result` 类是一个泛型类，用于封装API响应的结果。它包含了操作结果的状态码、消息和数据。

### 类注解

* `@NoArgsConstructor`：Lombok库的注解，用于生成无参构造函数。
* `@AllArgsConstructor`：Lombok库的注解，用于生成包含所有字段的构造函数。
* `@Data`：Lombok库的注解，用于自动生成getter和setter方法，以及`toString`、`equals`和`hashCode`方法。

### 属性说明

* `code`：操作结果的状态码，类型为`Integer`。通常，0表示操作成功，非0表示操作失败。
* `message`：操作结果的描述信息，类型为`String`。用于提供更详细的操作结果说明。
* `data`：操作结果的数据，类型为泛型`T`。用于存储具体的响应数据。

### 静态方法

#### success(E data)

* **参数**：`E data` - 操作成功时需要返回的数据。
* **返回值**：`Result<E>` - 操作成功的结果对象，状态码为0，消息为"操作成功"，数据为传入的数据。

#### success()

* **返回值**：`Result` - 不包含具体数据的操作成功结果对象，状态码为0，消息为"操作成功"。

#### error(String message)

* **参数**：`String message` - 操作失败时的错误消息。
* **返回值**：`Result` - 操作失败的结果对象，状态码为1，消息为传入的错误消息。

### 使用场景

`Result` 类主要用于服务端向客户端返回API调用的结果。通过状态码、消息和数据，客户端可以快速了解API调用的结果并进行相应的处理。

### 示例

```java
// 操作成功，返回具体数据
Result<String> successResult = Result.success("操作成功的数据");
​
// 操作成功，不返回数据
Result successResultNoData = Result.success();
​
// 操作失败
Result errorResult = Result.error("操作失败的原因");
​
此类通过提供统一的响应结构，帮助前后端进行高效的通信。
​
```
