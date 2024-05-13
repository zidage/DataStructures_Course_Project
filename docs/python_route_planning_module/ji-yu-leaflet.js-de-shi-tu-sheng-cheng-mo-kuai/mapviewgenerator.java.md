---
description: 作者：字禹润
---

# MapViewGenerator.java

## Overview

该Java类可以运行一个Python进程，在这里便是map\_view\_generator.py，将Java程序中的字符串传入Python程序中，实现地图生成的功能。该类具有的主要的成员以及方法有：

```java
private String script_path;
private ProcessBuilder pb;
private Process process;
private OutputStreamWriter writer;
private BufferedReader reader;
public MapViewGenerator()
public void creatQuery(String query)
public void getStdOut()
public void endProcess()
```

另外，该文件还提供了一段示例代码。本文档将基于这段示例代码进行讲解。

## Usage

以下是一段示例代码：

```java
public static void main(String[] args) throws IOException, InterruptedException {
        MapViewGenerator mapViewGenerator = new MapViewGenerator();
        mapViewGenerator.creatQuery(
                "-r Harbin_Institute_of_Technology_Weihai 1226934978 425467438 657768326 657768305 6001784001 17344858");
        System.out.println(mapViewGenerator.reader.readLine());
        System.out.println(mapViewGenerator.reader.readLine());
        mapViewGenerator.endProcess();
}
               
```

首先，我们通过使用`MapViewGenerator`的构造器新建了一个“地图生成器”，然后我们就可以使用`creatQuery`方法生成了一个地图，这个字符串的格式要求可以在[这里](map\_view\_generator.py.md#usage)找到。

这里，我们创建的请求字符串翻译成的自然语言如下：

<table><thead><tr><th width="425">命令</th><th>意义</th></tr></thead><tbody><tr><td>-r</td><td>进行路径生成</td></tr><tr><td><pre data-full-width="true"><code>Harbin_Institute_of_Technology_Weihai
</code></pre></td><td>地点名称，这里为<code>哈尔滨工业大学（威海）</code></td></tr><tr><td><code>122693497</code></td><td>第一个场所，其<a href="../data_fetcher/map_data/map_exports.md"><code>osmid</code></a>为<code>122693497</code></td></tr><tr><td><code>425467438</code></td><td>第二个场所，其<a href="../data_fetcher/map_data/map_exports.md"><code>osmid</code></a>为<code>425467438</code></td></tr><tr><td>...</td><td>...</td></tr></tbody></table>

也就是说，我们请求生成了**在`哈尔滨工业大学（威海）`的地图并在上面创建一条从`122693497`途径`425467438`途径...途径`6001784001`最终到`17344858`的路径。**（目前该查询方法仅支持按照行走寻找距离最短的路径）

然后，在`$MAP_DATA$/map_view_html`下就会生成对应大学规范化英文名的一个html文件（目前的形式，日后将废弃该种保存形式）
