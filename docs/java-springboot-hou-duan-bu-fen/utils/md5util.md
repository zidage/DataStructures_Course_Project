# Md5Util

## Md5Util 类文档

### 包路径

`org.twentyEight.utils`

### 引入

* `java.security.MessageDigest`
* `java.security.NoSuchAlgorithmException`

### 类描述

`Md5Util` 类提供了MD5加密的实用方法，用于生成字符串的MD5校验值，以及验证字符串的MD5校验码是否与已知的MD5码相匹配。这个类使用Java的`MessageDigest`类来实现MD5算法。

#### 静态变量

**`protected static char hexDigits[]`**

* **描述**：用于将字节转换成16进制表示的字符的数组。这个字符数组是apache校验下载文件正确性的默认组合。

**`protected static MessageDigest messagedigest`**

* **描述**：`MessageDigest`的实例，用于执行MD5加密。
* **初始化**：在静态代码块中，尝试获取MD5算法的`MessageDigest`实例。

#### 方法

**`public static String getMD5String(String s)`**

* **描述**：生成输入字符串的MD5校验值。
* **参数**：
  * `s`：输入的字符串。
* **返回值**：输入字符串的MD5校验值，以16进制字符串形式表示。

**`public static boolean checkPassword(String password, String md5PwdStr)`**

* **描述**：判断输入的字符串的MD5校验码是否与已知的MD5码相匹配。
* **参数**：
  * `password`：要校验的字符串。
  * `md5PwdStr`：已知的MD5校验码。
* **返回值**：如果输入字符串的MD5码与已知MD5码相匹配，返回`true`；否则返回`false`。

**`public static String getMD5String(byte[] bytes)`**

* **描述**：生成输入字节数组的MD5校验值。
* **参数**：
  * `bytes`：输入的字节数组。
* **返回值**：输入字节数组的MD5校验值，以16进制字符串形式表示。

#### 私有方法

**`private static String bufferToHex(byte bytes[])`**

* **描述**：将字节数组转换为16进制字符串。
* **参数**：
  * `bytes`：要转换的字节数组。
* **返回值**：转换后的16进制字符串。

**`private static String bufferToHex(byte bytes[], int m, int n)`**

* **描述**：将字节数组的指定部分转换为16进制字符串。
* **参数**：
  * `bytes`：要转换的字节数组。
  * `m`：起始索引。
  * `n`：长度。
* **返回值**：转换后的16进制字符串。

**`private static void appendHexPair(byte bt, StringBuffer stringbuffer)`**

* **描述**：将单个字节转换为其16进制字符表示，并追加到`StringBuffer`。
* **参数**：
  * `bt`：要转换的字节。
  * `stringbuffer`：字符缓冲区，用于追加转换后的字符。

### 使用示例

```java
String myPassword = "password123";
String md5Hash = Md5Util.getMD5String(myPassword);
System.out.println("MD5 Hash: " + md5Hash);
​
String knownMd5Hash = "202cb962ac59075b964b07152d234b70"; // MD5 for "123"
boolean isMatch = Md5Util.checkPassword("123", knownMd5Hash);
System.out.println("Does it match? " + isMatch);
结论
Md5Util 类为MD5加密提供了一个简单而强大的工具，可以用于生成MD5校验值和验证MD5校验码。通过封装MessageDigest的实现细节，它使得MD5加密的使用变得非常直接和便捷。
​
```
