# UserServiceImpl

## UserServiceImpl 类文档

### 包路径

`org.twentyEight.service.impl`

### 引入

* `org.springframework.beans.factory.annotation.Autowired`
* `org.springframework.stereotype.Service`
* `org.twentyEight.mapper.UserMapper`
* `org.twentyEight.pojo.User`
* `org.twentyEight.service.UserService`
* `org.twentyEight.utils.Md5Util`
* `org.twentyEight.utils.ThreadLocalUtil`
* `java.time.LocalDateTime`
* `java.util.Map`

### 类描述

`UserServiceImpl` 实现了 `UserService` 接口，提供用户管理功能，包括根据用户名查询用户、注册用户、更新用户信息、更新用户头像和更新用户密码等。

#### 方法

**`public User findByUserName(String username)`**

* **描述**：根据用户名查询用户。
* **参数**：
  * `username`：用户的用户名。
* **返回值**：`User` 用户对象。

**`public void register(String username, String password)`**

* **描述**：注册新用户，密码将被MD5加密后存储。
* **参数**：
  * `username`：新用户的用户名。
  * `password`：新用户的密码。
* **实现细节**：首先使用 `Md5Util` 工具类对密码进行MD5加密，然后调用 `UserMapper` 的 `add` 方法添加用户。

**`public void update(User user)`**

* **描述**：更新用户信息。
* **参数**：
  * `user`：包含更新信息的 `User` 对象。
* **实现细节**：设置用户的 `updateTime` 为当前时间，然后更新用户信息。

**`public void updateAvatar(String avatarUrl)`**

* **描述**：更新用户的头像URL。
* **参数**：
  * `avatarUrl`：新的头像URL。
* **实现细节**：从 `ThreadLocal` 中获取当前用户的ID，然后更新头像URL。

**`public void updatePwd(String newPwd)`**

* **描述**：更新用户的密码。
* **参数**：
  * `newPwd`：新密码。
* **实现细节**：从 `ThreadLocal` 中获取当前用户的ID，使用 `Md5Util` 工具类对新密码进行MD5加密，然后更新密码。

### 结论

`UserServiceImpl` 类通过提供一系列用户管理相关的方法，支持用户信息的查询、注册、更新等功能。特别注意的是，用户密码在存储前会进行MD5加密，以增强安全性。
