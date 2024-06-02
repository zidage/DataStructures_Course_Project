# WebConfig.java

### 概述

WebConfig 类是一个配置类，用于定义和配置Spring MVC的拦截器。它通过实现 WebMvcConfigurer 接口，允许我们自定义Spring MVC的行为。本配置主要用于注册自定义的拦截器 LoginInterceptor，以便对请求进行预处理，比如检查用户是否已登录。

### 配置细节

#### 注解说明

* `@Configuration`: 表明该类是一个配置类，Spring容器会自动检测到这个注解，并读取类中的配置信息。
* `@Autowired`: 自动注入Spring容器中的LoginInterceptor 实例到当前配置类中。

#### 实现接口

`WebMvcConfigurer`: 通过实现此接口，WebConfig 类能够自定义Spring MVC的配置。

#### 方法说明

* `addInterceptors(InterceptorRegistry registry)`: 此方法用于向Spring MVC注册自定义拦截器。
* `registry.addInterceptor(loginInterceptor)`: 将自定义的 LoginInterceptor 拦截器添加到Spring MVC的拦截器链中。
* `.excludePathPatterns("/user/login", "/user/regist")`: 指定拦截器不应拦截的路径模式，这里配置为不拦截登录和注册接口。

### 使用场景

此配置类主要用于需要对用户身份进行验证的Web应用。通过自定义的拦截器，开发者可以在用户访问特定资源前检查用户的登录状态，从而提高应用的安全性。
