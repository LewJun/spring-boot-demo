# spring-boot-quartz-cluster


> quartz 定时任务集群
之前[spring-boot-quartz](../spring-boot-quartz) 是内存级别的，不支持多JVM下的高可用。


[TOC]

## Quartz 两种存储器的对比

| 类型 | 优点 | 缺点 |
| ---- | ---- | ---- |
|RAMJobStore|不要外部数据库，配置容易，运行速度快|因为调度程序信息是存储在被分配给 JVM 的内存里面，所以，当应用程序停止运行时，所有调度信息将被丢失。另外因为存储到JVM内存里面，所以可以存储多少个 Job 和 Trigger 将会受到限制|
|JDBC 作业存储|支持集群，因为所有的任务信息都会保存到数据库中，可以控制事物，还有就是如果应用服务器关闭或者重启，任务信息都不会丢失，并且可以恢复因服务器关闭或者重启而导致执行失败的任务|运行速度的快慢取决与连接数据库的快慢|


## @DisallowConcurrentExecution

在job SayHelloJob上添加Quartz 的 @DisallowConcurrentExecution 注解，保证相同 JobDetail 在多个 JVM 进程中，有且仅有一个节点在执行。


## application.yml配置quartz

```yaml
spring:
  datasource:
    test:
      url: jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8
      driver-class-name: com.mysql.jdbc.Driver
      username: root
      password: Root123456
    quartz:
      url: jdbc:mysql://127.0.0.1:3306/quartz?useSSL=false&useUnicode=true&characterEncoding=UTF-8
      driver-class-name: com.mysql.jdbc.Driver
      username: root
      password: Root123456

  # Quartz 的配置，对应 QuartzProperties 配置类
  quartz:
    scheduler-name: clusteredScheduler # Scheduler 名字。默认为 schedulerName
    job-store-type: jdbc # Job 存储器类型。默认为 memory 表示内存，可选 jdbc 使用数据库。
    auto-startup: true # Quartz 是否自动启动
    startup-delay: 0 # 延迟 N 秒启动
    wait-for-jobs-to-complete-on-shutdown: true # 应用关闭时，是否等待定时任务执行完成。默认为 false ，建议设置为 true
    overwrite-existing-jobs: false # 是否覆盖已有 Job 的配置
    properties: # 添加 Quartz Scheduler 附加属性，更多可以看 http://www.quartz-scheduler.org/documentation/2.4.0-SNAPSHOT/configuration.html 文档
      org:
        quartz:
          # JobStore 相关配置
          jobStore:
            # 数据源名称
            dataSource: quartzDataSource # 使用的数据源
            class: org.quartz.impl.jdbcjobstore.JobStoreTX # JobStore 实现类
            driverDelegateClass: org.quartz.impl.jdbcjobstore.StdJDBCDelegate
            tablePrefix: QRTZ_ # Quartz 表前缀
            isClustered: true # 是集群模式
            clusterCheckinInterval: 1000
            useProperties: false

          scheduler:
            instanceName: Scheduler-Instance
            instanceId: AUTO

          # 线程池相关配置
          threadPool:
            threadCount: 25 # 线程池大小。默认为 10 。
            threadPriority: 5 # 线程优先级
            class: org.quartz.simpl.SimpleThreadPool # 线程池类型
    jdbc: # 使用 JDBC 的 JobStore 的时候，JDBC 的配置
      initialize-schema: never # 是否自动使用 SQL 初始化 Quartz 表结构。这里设置成 never ，我们手动创建表结构。
    cron:
      sayHelloJob: 0/5 * * * * ?
```

## DataSourceConfiguration

创建 DataSourceConfiguration 类，配置数据源

```java
@Configuration
public class DataSourceConfiguration {

    private static HikariDataSource createHikariDataSource(final DataSourceProperties properties) {
        // 创建 HikariDataSource 对象
        final HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        // 设置线程池名
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

    /**
     * 创建 user 数据源的配置对象
     */
    @Primary
    @Bean(name = "userDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.test") // 读取 spring.datasource.test 配置到 DataSourceProperties 对象
    public DataSourceProperties testDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 user 数据源
     */
    @Primary
    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test.hikari")
    // 读取 spring.datasource.user 配置到 HikariDataSource 对象
    public DataSource testDataSource() {
        // 获得 DataSourceProperties 对象
        final DataSourceProperties properties = this.testDataSourceProperties();
        // 创建 HikariDataSource 对象
        return createHikariDataSource(properties);
    }

    /**
     * 创建 quartz 数据源的配置对象
     */
    @Bean(name = "quartzDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.quartz")
    // 读取 spring.datasource.quartz 配置到 DataSourceProperties 对象
    public DataSourceProperties quartzDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 quartz 数据源
     */
    @Bean(name = "quartzDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.quartz.hikari")
    @QuartzDataSource
    public DataSource quartzDataSource() {
        // 获得 DataSourceProperties 对象
        final DataSourceProperties properties = this.quartzDataSourceProperties();
        // 创建 HikariDataSource 对象
        return createHikariDataSource(properties);
    }

}
```

## 导入sql
在 [Quartz Download](http://www.quartz-scheduler.org/downloads/) 地址下载对应版本的发布包。解压后，在`src/org/quartz/impl/jdbcjobstore/`目录，执行对应数据库的脚本初始化。

[doc/tables_mysql_innodb.sql](doc/tables_mysql_innodb.sql)

## 启动项目，模拟集群

创建App.java和App2.java

```java
public class App {
    public static void main(final String[] args) {
        System.setProperty("server.port", "0");
        SpringApplication.run(App.class, args);
    }
}
```

```java

@SpringBootApplication
public class App2 {
    public static void main(final String[] args) {
        System.setProperty("server.port", "0");
        SpringApplication.run(App2.class, args);
    }
}
```

首先启动App，会看到定时任务开始执行，
然后再启动App2，定时任务并没有执行，当关闭App后，该App2应用的定时任务才开始执行。


## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

