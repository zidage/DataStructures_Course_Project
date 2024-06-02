# LoginInterceptor

`LoginInterceptor` 类实现了 Spring MVC 的 `HandlerInterceptor` 接口，用于在请求处理之前和之后执行操作，主要用于用户登录状态的验证。

### 类注解

* `@Component`：将此拦截器类标记为组件，使其被Spring容器自动检测并添加到应用上下文中。

### 实现的接口方法

#### preHandle

* **参数**：
  * `HttpServletRequest request`：当前请求对象。
  * `HttpServletResponse response`：当前响应对象。
  * `Object handler`：被拦截的处理器（例如，一个Controller的方法）。
* **返回值**：`boolean` - 如果返回`true`，则继续向下执行（到下一个拦截器或处理请求）；如果返回`false`，则中断执行。
* **功能描述**：
  * 从请求头中获取`Authorization`字段，即JWT令牌。
  * 尝试解析JWT令牌。如果解析成功，将令牌中的claims信息存储到`ThreadLocal`中，然后放行（返回`true`）。
  * 如果解析令牌失败（例如，令牌无效或过期），则设置HTTP响应状态码为401（未授权），并中断请求处理流程（返回`false`）。

#### afterCompletion

* **参数**：
  * `HttpServletRequest request`：当前请求对象。
  * `HttpServletResponse response`：当前响应对象。
  * `Object handler`：被拦截的处理器。
  * `Exception ex`：处理请求过程中抛出的异常。
* **返回值**：无返回值。
* **功能描述**：
  * 请求处理完成后（包括渲染视图之后），清空存储在`ThreadLocal`中的数据，以防止内存泄露。

### 使用场景

* 该拦截器主要用于API保护，确保只有提供了有效JWT令牌的请求才能访问受保护的资源。
* 可以在Spring MVC配置中注册此拦截器，并指定需要拦截的URL路径模式。

### 注意事项

* 确保所有受保护的API在调用前都会经过此拦截器的检查。
* 在使用`ThreadLocal`时，务必在请求处理完毕后清理存储的数据，避免内存泄露。
* JWT令牌的有效性和安全性对于API保护至关重要，应确保令牌的生成和解析机制是安全的。
