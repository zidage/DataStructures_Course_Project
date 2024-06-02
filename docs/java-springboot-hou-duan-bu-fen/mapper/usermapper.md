# UserMapper

`UserMapper` 接口定义了操作用户（User）数据的MyBatis映射器。它包括了根据用户名查询用户、添加新用户、更新用户信息、更新用户头像和密码等操作。

### 接口注解

* `@Mapper`：标记该接口为MyBatis的映射器接口。

### 方法定义

#### findByUserName

* **注解**：`@Select`
* **SQL**：根据用户名查询用户记录。
* **参数**：`String username` - 用户名。
* **返回值**：`User` - 查询到的用户对象。如果没有找到对应的用户，返回`null`。

#### add

* **注解**：`@Insert`
* **SQL**：向数据库的`user`表插入一条新记录。
* **参数**：
  * `String username` - 用户名。
  * `String password` - 用户密码。
* **返回值**：无。操作成功后，新用户记录将被插入到数据库中。

#### update

* **注解**：`@Update`
* **SQL**：更新用户的昵称和电子邮件。
* **参数**：`User user` - 包含更新信息的用户对象。
* **返回值**：无。操作成功后，指定用户的信息将被更新。

#### updateAvatar

* **注解**：`@Update`
* **SQL**：更新用户的头像URL。
* **参数**：
  * `String avatarUrl` - 新的头像URL。
  * `Integer id` - 用户ID。
* **返回值**：无。操作成功后，用户的头像将被更新。

#### updatePwd

* **注解**：`@Update`
* **SQL**：更新用户的密码。
* **参数**：
  * `String md5String` - 经过MD5加密后的新密码。
  * `Integer id` - 用户ID。
* **返回值**：无。操作成功后，用户的密码将被更新。

### 注意事项

* 在实际应用中，`add`、`updatePwd` 方法中的密码应该在应用层进行加密处理，以确保数据库中存储的是加密后的密码。
* `updateAvatar` 和 `updatePwd` 方法通过用户ID来识别需要更新的用户记录，确保了更新操作的准确性。
