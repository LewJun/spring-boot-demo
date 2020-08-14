# spring-boot-sharding-read-write-separation-mybatis

> mybatis整合[ShardingSphere](https://shardingsphere.apache.org/index_zh.html)，实现读写分离

代码来源于[spring-boot-sharding-datasource-mybatis](../spring-boot-sharding-datasource-mybatis)

只能实现把数据写入主表，在从表中读取数据。而要实现主从复制，需要对mysql进行主从同步设置。

## 读写分离配置

关键配置

```yaml
    # ################# 主从配置 ##################
    masterslave:
      name: ms
      # 主数据源配置
      master-data-source-name: test
      # 从数据源配置
      slave-data-source-names: test0, test1
    # ################# 主从配置 ##################
```

[完整配置](src/main/resources/application.yml)

```yaml
spring:
  # ShardingSphere 配置项
  shardingsphere:
    datasource:
      # 所有数据源的名字
      names: test, test0, test1
      test:
        type: com.zaxxer.hikari.HikariDataSource # 使用 Hikari 数据库连接池
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/test
        username: root
        password:

      test0:
        type: com.zaxxer.hikari.HikariDataSource # 使用 Hikari 数据库连接池
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/test0
        username: root
        password:

      test1:
        type: com.zaxxer.hikari.HikariDataSource # 使用 Hikari 数据库连接池
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/test1
        username: root
        password:


    # ################# 主从配置 ##################
    masterslave:
      name: ms
      # 主数据源配置
      master-data-source-name: test
      # 从数据源配置
      slave-data-source-names: test0, test1
    # ################# 主从配置 ##################

    # 拓展属性配置
    props:
      sql:
        show: true # 打印 SQL
```

## 完整单元测试

* [Ad01MapperTest.java](src/test/java/com/example/lewjun/Ad01MapperTest.java)

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

