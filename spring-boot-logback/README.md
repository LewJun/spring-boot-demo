# spring-boot-logback

> 一个spring boot的logback配置及使用示例

[TOC]

## [logback.xml](src/main/resources/logback.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!--日志目录-->
    <property name="log_dir" value="./spring-boot-logback/logs"/>
    <property name="maxHistory" value="10"/>
    <property name="maxFileSize" value="2MB"/>
    <property name="charset" value="UTF-8"/>

    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>DEBUG</level>
        </filter>
        <encoder>
            <pattern>%date [%thread] %-5level [%logger{50}] %file:%line - %msg%n</pattern>
            <charset>${charset}</charset>
        </encoder>
    </appender>

    <appender name="TRACE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!--只接受TRACE级别的日志-->
            <level>TRACE</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <!--滚动策略，按照时间滚动 TimeBasedRollingPolicy-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--文件路径,定义了日志的切分方式——把每一天的日志归档到一个文件中,以防止日志填满整个磁盘空间-->
            <FileNamePattern>${log_dir}/trace.%d{yyyy-MM-dd}.part_%i.log</FileNamePattern>
            <!--只保留最近90天的日志-->
            <maxHistory>${maxHistory}</maxHistory>
            <!--用来指定日志文件的上限大小，那么到了这个值，就会删除旧的日志-->
            <!--<totalSizeCap>1GB</totalSizeCap>-->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <!-- maxFileSize:这是活动文件的大小，默认值是10MB,本篇设置为1KB，只是为了演示 -->
                <maxFileSize>${maxFileSize}</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <!--<triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">-->
        <!--<maxFileSize>1KB</maxFileSize>-->
        <!--</triggeringPolicy>-->
        <encoder>
            <pattern>%date [%thread] %-5level [%logger{50}] %file:%line - %msg%n</pattern>
            <charset>${charset}</charset> <!-- 此处设置字符集 -->
        </encoder>
    </appender>

    <!--WARNING 这里省略了除trace外的其它appender-->


    <!-- root将级别为“INFO”及大于“INFO”的日志信息交给已经配置好的名为“Console”的appender处理，
    “Console”appender将信息打印到Console，INFO及以上的日志将会被打印到日志文件中。
     -->
    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR"/>
        <appender-ref ref="INFO"/>
        <appender-ref ref="WARN"/>
        <appender-ref ref="DEBUG"/>
        <appender-ref ref="TRACE"/>
    </root>
</configuration>

```


## [App.java](src/main/java/com/example/lewjun/App.java)

```java
/**
 * spring boot 启动类
 */
@SpringBootApplication
@Slf4j
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);

        log.trace("trace: {}", "trace");
        log.debug("debug: {}", "debug");
        log.info("info: {}", "info");
        log.warn("warn: {}", "warn");
        log.error("error: {}", "error");

        try {
            final int i = 1 / 0;
        } catch (final Exception ex) {
            log.error("出现异常", ex);
        }
    }
}

```

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo
```

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

