# UserService

## UserService 接口文档

### 包路径

`org.twentyEight.service`

### 引入

* `org.twentyEight.pojo.User`

### 接口描述

`UserService` 接口定义了应用中用户服务应提供的核心功能。它包括用户管理的操作，如根据用户名查询用户、注册新用户、更新用户信息、更新用户头像以及更新用户密码。

#### 方法

**`User findByUserName(String username)`**

* **描述**：根据提供的用户名查询并返回一个 `User` 对象。
* **参数**：
  * `username`：一个 `String` 类型，表示要查询的用户的用户名。
* **返回值**：对应指定用户名的 `User` 对象。

**`void register(String username, String password)`**

* **描述**：使用提供的用户名和密码注册新用户。
* **参数**：
  * `username`：一个 `String` 类型，表示新用户的用户名。
  * `password`：一个 `String` 类型，表示新用户的密码。

**`void update(User user)`**

* **描述**：更新现有用户的信息。
* **参数**：
  * `user`：一个 `User` 对象，包含了更新后的用户信息。

**`void updateAvatar(String avatarUrl)`**

* **描述**：更新用户的头像。
* **参数**：
  * `avatarUrl`：一个 `String` 类型，表示新头像图片的 URL。

**`void updatePwd(String newPwd)`**

* **描述**：更新用户的密码。
* **参数**：
  * `newPwd`：一个 `String` 类型，表示用户的新密码。

### 结论

`UserService` 接口在应用中扮演了用户管理的关键角色，提供了查询、注册和更新用户信息的基本方法
