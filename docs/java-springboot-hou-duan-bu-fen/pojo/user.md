# User

## User 类文档

`User` 类定义了用户实体的结构，包含了用户的基本信息，如用户ID、用户名、密码、昵称、电子邮件地址、用户图像地址以及创建和更新时间。

### 类注解

* `@Data`：Lombok库的注解，用于自动生成getter和setter方法，以及`toString`、`equals`和`hashCode`方法。
* `@JsonIgnore`：Jackson库的注解，用于在对象序列化为JSON时忽略`password`属性，增加数据的安全性。

### 字段验证注解

* `@NotNull`：用于验证`id`字段，确保`id`不为`null`。
* `@NotEmpty`：用于验证`nickname`和`email`字段，确保这些字段不为空。
* `@Pattern(regexp = "^\\S{1,16}$")`：用于验证`nickname`字段，确保昵称为1到16个非空白字符。
* `@Email`：用于验证`email`字段，确保电子邮件地址的格式正确。

### 属性说明

* `id`：用户的唯一标识符，类型为`Integer`。
* `username`：用户的用户名，类型为`String`。
* `password`：用户的密码，类型为`String`。在JSON序列化时会被忽略。
* `nickname`：用户的昵称，类型为`String`。必须为1到16个非空白字符。
* `email`：用户的电子邮件地址，类型为`String`。必须符合电子邮件的格式。
* `userPic`：用户的图像地址，类型为`String`。
* `createTime`：用户创建的时间，类型为`LocalDateTime`。
* `updateTime`：用户最后更新的时间，类型为`LocalDateTime`。

### 使用场景

`User` 类主要用于管理和展示用户的基本信息。例如，在用户注册、登录、个人信息展示和编辑等功能中使用。

### 示例

```java
User user = new User();
user.setId(1);
user.setUsername("john_doe");
user.setPassword("password123");
user.setNickname("John");
user.setEmail("john.doe@example.com");
user.setUserPic("http://example.com/userpic.jpg");
user.setCreateTime(LocalDateTime.now());
user.setUpdateTime(LocalDateTime.now());
​
```
