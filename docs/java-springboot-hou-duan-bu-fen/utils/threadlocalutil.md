# ThreadLocalUtil

## ThreadLocalUtil 类文档

### 包路径

`org.twentyEight.utils`

### 引入

* `java.util.HashMap`
* `java.util.Map`

### 类描述

`ThreadLocalUtil` 类是一个工具类，提供了对 `ThreadLocal` 的简化使用方法。它允许在当前线程中存储、获取和清除数据，从而实现线程间数据隔离。

#### 静态变量

**`private static final ThreadLocal THREAD_LOCAL`**

* **描述**：定义了一个 `ThreadLocal` 对象，用于存储每个线程的私有数据。

#### 方法

**`public static <T> T get()`**

* **描述**：从当前线程的 `ThreadLocal` 中获取存储的值。
* **返回值**：返回当前线程中存储的值，类型为泛型 `T`。

**`public static void set(Object value)`**

* **描述**：向当前线程的 `ThreadLocal` 中存储一个值。
* **参数**：
  * `value`：要存储在当前线程的 `ThreadLocal` 中的值。

**`public static void remove()`**

* **描述**：清除当前线程的 `ThreadLocal` 中存储的值。
* **实现细节**：调用 `ThreadLocal.remove()` 方法来防止潜在的内存泄漏问题。

### 使用示例

```java
// 在当前线程中存储用户信息
Map<String, Object> userInfo = new HashMap<>();
userInfo.put("userId", 12345);
userInfo.put("userName", "John Doe");
ThreadLocalUtil.set(userInfo);
​
// 在当前线程的其他部分获取用户信息
Map<String, Object> currentUserInfo = ThreadLocalUtil.get();
System.out.println("Current User Info: " + currentUserInfo);
​
// 在操作完成后清除存储的用户信息
ThreadLocalUtil.remove()
​
```
