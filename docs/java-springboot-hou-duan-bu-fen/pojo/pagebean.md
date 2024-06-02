# PageBean

## PageBean 类文档

`PageBean` 类是一个泛型类，用于封装分页查询的结果。这个类通过泛型 `<T>` 支持多种类型的数据，使其能够广泛用于不同实体的分页显示。

### 类注解

* `@Data`：Lombok库的注解，用于自动生成getter和setter方法，以及`toString`、`equals`和`hashCode`方法。
* `@NoArgsConstructor`：Lombok库的注解，用于生成一个无参构造函数。
* `@AllArgsConstructor`：Lombok库的注解，用于生成一个包含所有字段的构造函数。

### 属性说明

* `total`：表示查询结果的总记录数，类型为`Long`。这个属性用于前端分页组件计算总页数。
* `items`：表示当前页的数据列表，类型为`List<T>`。泛型 `<T>` 允许这个类被用于不同类型数据的分页，如用户列表、商品列表等。

### 示例

假设有一个用户实体类 `User`，并且需要对用户列表进行分页处理，可以这样使用 `PageBean`：

```java
PageBean<User> userPageBean = new PageBean<>();
userPageBean.setTotal(100L); // 假设总共有100条用户记录
userPageBean.setItems(Arrays.asList(new User(), new User())); // 当前页的用户数
​
```
