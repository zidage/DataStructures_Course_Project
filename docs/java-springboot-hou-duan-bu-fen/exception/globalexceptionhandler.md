# GlobalExceptionHandler

`GlobalExceptionHandler` 类是一个全局异常处理器，使用 Spring MVC 的 `@RestControllerAdvice` 注解进行标记，用于捕获和处理控制器抛出的异常。

### 类注解

* `@RestControllerAdvice`：该注解将类定义为一个异常处理器，用于处理所有控制器（Controller）中抛出的异常。

### 方法注解

* `@ExceptionHandler(Exception.class)`：指定该方法用于处理所有类型的 `Exception` 异常。这意味着，当应用中发生任何未被捕获的异常时，此方法将被调用。

### 方法描述

#### handleException

* **参数**：`Exception e` - 捕获到的异常对象。
* **功能描述**：此方法打印异常堆栈信息（调用 `e.printStackTrace()`），并尝试返回一个包含错误信息的 `Result` 对象。如果异常对象 `e` 包含非空的消息（`e.getMessage()`），则使用该消息作为错误信息；否则，默认错误信息为“操作失败”。
* **返回值**：`Result` - 根据异常信息构建的 `Result` 对象，使用 `Result.error()` 方法生成，携带了错误信息。

### 注意事项

* 使用 `StringUtils.hasLength()` 方法来检查异常消息是否非空和非仅空白字符，确保返回给前端的错误信息是有意义的。
* 该全局异常处理器能够捕获所有控制器中未被处理的异常，是一种集中管理异常处理逻辑的好方法，可以减少代码冗余，并且能够对异常情况给出统一的响应格式。
* 在实际应用中，可能需要根据不同类型的异常定制更具体的异常处理方法，以提供更详细的错误信息或进行特定的异常处理逻辑。
