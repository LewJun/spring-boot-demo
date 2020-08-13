# spring-boot-sharding-datasource-mybatis

> mybatis整合[ShardingSphere](https://shardingsphere.apache.org/index_zh.html)，实现多数据源

代码来源于[spring-boot-sharding-datasource-mybatis](../spring-boot-sharding-datasource-mybatis)

之前在垂直分库表节，问了一句`垂直分库的表，不能和水平分库的表联合查询。##TODO## 该怎么办？`，其实垂直分库，就是多数据源，不能做联合查询。

多数据源数据最终只能拿过来做统一分析。

## 配置

```yaml
spring:
  # ShardingSphere 配置项
  shardingsphere:
    datasource:
      # 所有数据源的名字
      names: test, test1
      test:
        type: com.zaxxer.hikari.HikariDataSource # 使用 Hikari 数据库连接池
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/test
        username: root
        password:

      test1:
        type: com.zaxxer.hikari.HikariDataSource # 使用 Hikari 数据库连接池
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/test1
        username: root
        password:
    # 分片规则
    sharding:
      tables:
        # ################## 垂直分库的表 ######################
        ac01:
          actualDataNodes: test.ac01

        ab01:
          actualDataNodes: test.ab01
        # 如上，ac01和ab01都位于同一个库test中，所以是可以做联合查询的

        ad01:
          actualDataNodes: test1.ad01
        # ################## 垂直分库的表 ######################
    # 拓展属性配置
    props:
      sql:
        show: true # 打印 SQL
```

>如上，配置了两个数据源`test`和`test1`，
`ac01`和`ab01`表被路由映射到了`test`数据源，用的同一个数据源`test`所以是可以做联合查询的，在Ab01MapperTest.java中有示例；
`ad01`表被路由映射到了`test1`数据源中。

## 完整单元测试

* [Ab01MapperTest.java](src/test/java/com/example/lewjun/Ab01MapperTest.java)

* [Ac01MapperTest.java](src/test/java/com/example/lewjun/Ac01MapperTest.java)

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

