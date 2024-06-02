# MapViewGenerationUtil

## MapViewGenerationUtil 类文档

### 包路径

`org.twentyEight.utils`

### 引入

* `java.io.*`
* `java.nio.charset.Charset`

### 类描述

`MapViewGenerationUtil` 类用于生成地图视图。它通过调用一个外部Python脚本（[`map_view_generator.py`](../../python\_route\_planning\_module/ji-yu-leaflet.js-de-shi-tu-sheng-cheng-mo-kuai/map\_view\_generator.py.md)），传递查询参数来生成地图视图，并获取脚本的输出。

#### 成员变量

* `private String script_path`：Python脚本的路径。
* `private ProcessBuilder pb`：用于启动Python脚本的进程构建器。
* `private Process process`：表示Python脚本进程。
* `private OutputStreamWriter writer`：用于向Python脚本进程发送输入的流。
* `private BufferedReader reader`：用于从Python脚本进程接收输出的流。

#### 构造方法

**`public MapViewGenerationUtil() throws IOException`**

* **描述**：构造方法初始化类的实例，启动地图视图生成脚本的进程，并准备输入输出流。
* **实现细节**：
  * 从环境变量`DS_PY_UTIL`获取Python脚本的路径。
  * 使用`ProcessBuilder`启动Python脚本进程。
  * 初始化`writer`和`reader`以与Python脚本进程通信。

#### 方法

**`public void creatQuery(String query) throws IOException`**

* **描述**：向Python脚本发送查询指令。
* **参数**：
  * `query`：要发送给Python脚本的查询指令。
* **实现细节**：
  * 通过`writer`向Python脚本写入查询指令，并立即刷新确保指令发送。

**`public String getStdOut() throws IOException`**

* **描述**：从Python脚本获取标准输出。
* **返回值**：Python脚本的一行标准输出。

**`public String endProcess() throws IOException, InterruptedException`**

* **描述**：关闭向Python脚本的写入流，并等待进程结束，获取最终的输出结果。
* **返回值**：Python脚本完成执行后的最终输出结果。

#### 使用示例

```java
public static void main(String[] args) throws IOException, InterruptedException {
    MapViewGenerationUtil mapViewGenerationUtil = new MapViewGenerationUtil();
    mapViewGenerationUtil.creatQuery("-r 12345 0 0 Harbin_Institute_of_Technology_Weihai 1226934978 425467438 657768326 657768305 6001784001 17344858");
    mapViewGenerationUtil.endProcess();
}

结论
MapViewGenerationUtil 类提供了一个简单的方式来与外部Python脚本交互，用于生成地图视图。它封装了进程创建、指令发送、输出获取等操作，使得从Java应用中调用Python脚本变得更加容易。

```
