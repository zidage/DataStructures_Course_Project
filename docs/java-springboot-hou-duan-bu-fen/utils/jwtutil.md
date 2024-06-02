# JwtUtil

## JwtUtil 类文档

### 包路径

`org.twentyEight.utils`

### 引入

* `com.auth0.jwt.JWT`
* `com.auth0.jwt.algorithms.Algorithm`
* `java.util.Date`
* `java.util.Map`

### 类描述

`JwtUtil` 类提供了JWT（JSON Web Token）的生成和解析功能，使用HMAC256算法对token进行签名。

#### 常量

**`private static final String KEY`**

* **描述**：用于签名的密钥。
* **值**：`"BALLS UNDER RED FLAG"`。

#### 方法

**`public static String genToken(Map<String, Object> claims)`**

* **描述**：根据提供的业务数据生成JWT token。
* **参数**：
  * `claims`：一个 `Map<String, Object>` 类型，包含了要放入token中的业务数据。
* **返回值**：生成的JWT token字符串。
* **实现细节**：
  * 使用 `JWT.create()` 方法创建一个新的JWT构建器。
  * 通过 `.withClaim("claims", claims)` 添加业务数据。
  * 设置token的过期时间为当前时间之后的12小时。
  * 使用HMAC256算法和预定义的密钥对token进行签名。

**`public static Map<String, Object> parseToken(String token)`**

* **描述**：验证并解析token，返回包含在token中的业务数据。
* **参数**：
  * `token`：待验证和解析的JWT token字符串。
* **返回值**：从token中解析出的业务数据，以 `Map<String, Object>` 形式返回。
* **实现细节**：
  * 使用 `JWT.require(Algorithm.HMAC256(KEY))` 配置验证器，指定HMAC256算法和密钥。
  * 使用 `.build().verify(token)` 验证并解析token。
  * 通过 `.getClaim("claims").asMap()` 获取token中的业务数据并返回。

### 使用示例

#### 生成Token

<pre class="language-java"><code class="lang-java">Map&#x3C;String, Object> claims = new HashMap&#x3C;>();
claims.put("userId", 123);
claims.put("username", "john_doe");
​
String token = JwtUtil.genToken(claims);
System.out.println("Generated Token: " +token);
解析Token
String token = "your.jwt.token.here";
Map&#x3C;String, Object> parsedClaims = JwtUtil.parseToken(token);
System.out.println("Parsed Claims: " + parsedClaims);
<strong>结论
</strong>JwtUtil 类通过封装 com.auth0.jwt 库的功能，简化了JWT的生成和解析过程，使得在Java应用中实现基于JWT的认证机制变得更加容易。
​
</code></pre>
