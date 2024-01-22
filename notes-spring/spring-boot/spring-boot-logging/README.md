Spring Boot 使用 Commons Logging 对内部日志进行日志记录。但是我们也可以通过配置使用其他日志实现，SpringBoot默认为 java.util.logging,log4j,logback提供了默认控制台输出和可选的文件输出配置。

# 添加日志依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
</dependency>
```

一般不添加该依赖，因为`spring-boot-starter`中已经包含了`spring-boot-starter-logging`

# 日志级别
日志级别从高到底可以分为:
```yml
TRACE < DEBUG < INFO < WARN < ERROR < FATAL
```
如果设置WARN，则低于WARN的日志都不会输出。

# 配置输出级别

SpringBoot默认的配置会向控制台输出 `ERROR`,`WARN`,`INFO`等级的日志。你可以在配置文件中使用配置声明打印的日志级别：
```yml
logging:
  level:
    root: debug
    me.feathers.demo: debug
```

也可以在启动jar时，指定参数，启用调试，这样也会打印debug日志：
```
java -jar xxx.jar --debug
```

同样，针对`trace`级别的日志，也有这两种方式。
```
java -jar xxx.jar --trace
```

# 默认日志格式
Spring Boot默认的格式化输出如下：
```log
2014-03-05 10:57:51.112  INFO 45469 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/7.0.52
2014-03-05 10:57:51.253  INFO 45469 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2014-03-05 10:57:51.253  INFO 45469 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1358 ms
2014-03-05 10:57:51.698  INFO 45469 --- [ost-startStop-1] o.s.b.c.e.ServletRegistrationBean        : Mapping servlet: 'dispatcherServlet' to [/]
2014-03-05 10:57:51.702  INFO 45469 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
```

上面的日志分别对应：

```
日期(yyyy-MM-dd HH:mm:ss.SSS) 日志级别(INFO,DEBUG,ERROR...) 进程ID ---(分隔符) 线程名称(比如[main]代表主线程) 日志对象的名称(m.f.d.LoggerTest) 日志信息
```

# 控制台日志输出
SpringBoot默认只会在控制台输出。
## 控制台日志的颜色配置
如果你的终端支持ANSI，日志会按照默认的颜色打印日志，以增强日志的可读性。你可以通过配置：

```properties
spring=
output=
ansi=
enabled=always  #always总是启用ANSI打印彩色日志， never永远不启用ANSI，detect检测是否有ANSI着色功能有的话就启用
```
来更改这个选项。

> 注意:
> 默认设置为detect，没有颜色输出，需要改为always。

**win下控制台不支持颜色输出，开启后会打印乱码，可以使用cmder替换cmd以及powerShell**，IDEA新版本控制台默认支持ANSI。

下面是每个日志等级默认对应的颜色：

|Level|Color|
|-----|-----|
|FATAL|Red|
|ERROR|Red|
|WARN|Yellow|
|INFO|Green|
|DEBUG|Green|
|TRACE|Green|

# 文件日志输出

默认情况下，Spring Boot 只会将日志记录在控制台，而不会记录在文件。如果要将日志打印在文件中，可以指定：

```
logging.file=my.log #设置文件，可以是绝对路径或者相对路径
```

```
logging.path=/var/log #只配置路径，则会在路径下生成spring.log日志文件
```
两个属性，注意**二者不可以同时使用**。

# 自定义日志配置

由于日志服务一般都在ApplicationContext创建前就初始化了，它并不是必须通过Spring的配置文件控制。因此通过系统属性和传统的Spring Boot外部配置文件依然可以很好的支持日志控制和管理。

根据不同的日志系统，你可以按如下规则组织配置文件名，并放到resources目录下即可被正确加载：
- `Logback`：logback-spring.xml, logback-spring.groovy, logback.xml, logback.groovy
- `Log4j`：log4j-spring.properties, log4j-spring.xml, log4j.properties, log4j.xml
- `Log4j2`：log4j2-spring.xml, log4j2.xml
- `JDK (Java Util Logging)`：logging.properties

如果向更换日志配置的位置或者名称，可以通过
```properties
logging.config=classpath:logging-config.xml
```
来配置。

> 更改日志名称通常用作不同运行时的不同配置。

# Logback扩展

SpringBoot针对Logback提供了一系列的扩展，这样可以做更多的高级操作。你可以在`logback-spring.xml`使用这些扩展。

> 注意，不可以在`logback.xml`中使用扩展，因为该文件加载过早，你可以使用`spring-logback.xml`或者配置`logging.config`属性。
