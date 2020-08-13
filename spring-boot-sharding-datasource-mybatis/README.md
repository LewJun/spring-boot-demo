# spring-boot-sharding-datasource-mybatis

> mybatis整合[ShardingSphere](https://shardingsphere.apache.org/index_zh.html)

[TOC]

## 引入依赖

```xml
    <dependencies>
        <!--实现对mybatis的自动化-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql-connector-java.version}</version>
        </dependency>

        <!-- 实现对 Sharding-JDBC 的自动化配置 -->
        <dependency>
            <groupId>org.apache.shardingsphere</groupId>
            <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
        </dependency>

    </dependencies>
```

## 水平分库分表

将ab01表，拆分到2个库，每个库5张表，一共10张表

```
- test0
    - ab01_0
    - ab01_2
    - ab01_4
    - ab01_6
    - ab01_8

- test1
    - ab01_1
    - ab01_3
    - ab01_5
    - ab01_7
    - ab01_9

id以5结尾的数据都在ab01_5中

                id  aab001  AAB002     AAB003     
------------------  ------  ---------  -----------
500630389065252865       1  aab002 1   aab003 1   
500630405653725185      71  aab002 71  aab003 71  
500630407310475265      79  aab002 79  aab003 79  
500630409722200065      89  aab002 89  aab003 89  
500630410057744385      91  aab002 91  aab003 91  
500630411504779265      97  aab002 97  aab003 97  

------------------  ------  ---------  -----------

id以9结尾的数据都在ab01_9中

                id  aab001  AAB002     AAB003     
------------------  ------  ---------  -----------
500630389425963009       3  aab002 3   aab003 3   
500630389782478849       5  aab002 5   aab003 5   
500630391145627649       9  aab002 9   aab003 9   
500630392110317569      13  aab002 13  aab003 13  
500630396933767169      35  aab002 35  aab003 35  
500630397898457089      39  aab002 39  aab003 39  
500630398506631169      41  aab002 41  aab003 41  
500630401337786369      53  aab002 53  aab003 53  
500630405867634689      73  aab002 73  aab003 73  
500630410628169729      93  aab002 93  aab003 93  

```

拆分规则：对id字段进行计算

* 分库 var index = id % 2(个库); => test${index}

* 分表 var index = id % 10(张表); => ab01_${index}

根据以上规则，得到生成数据库表的ddl语句，在数据库中执行。
[schema-h2.sql](src/main/resources/db/schema-h2.sql)

水平拆分的数据库（表）的逻辑和数据结构相同

## ShardingSphere 配置水平分库分表
根据以上规则，在application.yml中配置水平分库分表

```yaml
spring:
  # ShardingSphere 配置项
  shardingsphere:
    datasource:
      # 所有数据源的名字
      names: test0, test1
      test0:
        type: com.zaxxer.hikari.HikariDataSource # 使用 Hikari  数据库连接池
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/test0
        username: root
        password:

      test1:
        type: com.zaxxer.hikari.HikariDataSource # 使用 Hikari  数据库连接池
        driver-class-name: com.mysql.jdbc.Driver
        jdbc-url: jdbc:mysql://127.0.0.1:3306/test1
        username: root
        password:
    # 分片规则
    sharding:
      tables:
        # ab01 表配置
        ab01:
          # 真实数据节点
          actualDataNodes: test0.ab01_$->{[0,2,4,6,8]}, test1.ab01_$->{[1,3,5,7,9]}
          key-generator: # 主键生成策略
            column: id
            type: SNOWFLAKE # 雪花算法 所以id是long类型
          database-strategy:
            inline:
              algorithm-expression: test$->{id % 2}
              sharding-column: id
          table-strategy:
            inline:
              algorithm-expression: ab01_$->{id % 10}
              sharding-column: id

    # 拓展属性配置
    props:
      sql:
        show: true # 打印 SQL
```

必须配置数据库连接池，否则会报错：
```log
Error creating bean with name 'org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration': Initialization of bean failed; nested exception is java.lang.NullPointerException
```

## 垂直分库表

不用水平分库表的某些表，可以放到原库中。例如test.ac01
```yaml

        # ################## 垂直分库的表 ######################
        ac01:
          actualDataNodes: test.ac01
          keyGenerator:
            column: id
            type: SNOWFLAKE
          tableStrategy:
            inline:
              shardingColumn: id
              algorithmExpression: ac01
        # ################## 垂直分库的表 ######################
```

垂直分库的表，不能和水平分库的表联合查询。##TODO## 该怎么办？

## 公共表配置

在项目中一般会有一些表的内容是固定的，或者说是很少修改的表，但是又经常跟这些表关联查询，例如一些状态信息。一般在我们项目中这种表会存放在我们各个数据库，所以称为公共表

```yaml
        # ############  公共表配置  开始   ####################
        # 在broadcast-tables中，配置了t_dict为广播表/公共表，所以这里是对t_dict进行配置
        ad01:
          # 配置公共表的主键生成策略
          key-generator:
            column: id
            type: SNOWFLAKE
        # todo 如果公共表比较多，那还挺麻烦的
      # 配置广播表/公共表（在项目中一般会有一些表的内容是固定的，或者说是很少修改的表，但是又经常跟这些表关联查询，例如一些状态信息。一般在我们项目中这种表会存放在我们各个数据库，所以称为公共表）
      broadcast-tables:
        - ad01 # 同时存在于 test、test0、test1中
    # ############  公共表配置  开始   ####################
```

由于这是公共表，在增删改数据时候，会在每个库中都会做相同的操作。

执行查询
```
//  错误的写法 @Select("select ab01.*, ad01.aad001 from ab01 join ad01 on ab01.aab001=ad01.aad001") // 运行时会提示找不到ab01，因为最后真实的语句是select ab01.*, ad01.aad001 from ab01_0 join ad01 on ab01.aab001=ad01.aad001
// **所以，如果要做分库分表，别名处该写as的地方一定要写as**
    @Select("select ab01.*, ad01.aad001 from ab01 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001")
    List<Ab01Ad01> queryAb01Ad01();
```

```log
 ShardingSphere-SQL                       : Logic SQL: select ab01.*, ad01.aad001 from ab01 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : SQLStatement: SelectStatementContext(super=CommonSQLStatementContext(sqlStatement=org.apache.shardingsphere.sql.parser.sql.statement.dml.SelectStatement@68b7bdcb, tablesContext=org.apache.shardingsphere.sql.parser.binder.segment.table.TablesContext@d84418a), tablesContext=org.apache.shardingsphere.sql.parser.binder.segment.table.TablesContext@d84418a, projectionsContext=ProjectionsContext(startIndex=7, stopIndex=25, distinctRow=false, projections=[ShorthandProjection(owner=Optional[ab01], actualColumns=[ColumnProjection(owner=ab01, name=id, alias=Optional.empty), ColumnProjection(owner=ab01, name=aab001, alias=Optional.empty), ColumnProjection(owner=ab01, name=aab002, alias=Optional.empty), ColumnProjection(owner=ab01, name=aab003, alias=Optional.empty)]), ColumnProjection(owner=ad01, name=aad001, alias=Optional.empty)]), groupByContext=org.apache.shardingsphere.sql.parser.binder.segment.select.groupby.GroupByContext@45e11627, orderByContext=org.apache.shardingsphere.sql.parser.binder.segment.select.orderby.OrderByContext@ec5f944, paginationContext=org.apache.shardingsphere.sql.parser.binder.segment.select.pagination.PaginationContext@5b4954b2, containsSubquery=false)
 ShardingSphere-SQL                       : Actual SQL: test0 ::: select ab01.*, ad01.aad001 from ab01_0 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test0 ::: select ab01.*, ad01.aad001 from ab01_2 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test0 ::: select ab01.*, ad01.aad001 from ab01_4 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test0 ::: select ab01.*, ad01.aad001 from ab01_6 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test0 ::: select ab01.*, ad01.aad001 from ab01_8 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test1 ::: select ab01.*, ad01.aad001 from ab01_1 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test1 ::: select ab01.*, ad01.aad001 from ab01_3 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test1 ::: select ab01.*, ad01.aad001 from ab01_5 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test1 ::: select ab01.*, ad01.aad001 from ab01_7 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 ShardingSphere-SQL                       : Actual SQL: test1 ::: select ab01.*, ad01.aad001 from ab01_9 as ab01 join ad01 as ad01 on ab01.aab001=ad01.aad001
 com.example.lewjun.Ab01MapperTest        : 【queryAb01Ad01: [Ab01Ad01(id=500663677825843200, aab002=aab002 20, aab003=aab003 20, aab001=20, aad001=20), Ab01Ad01(id=500663678874419200, aab002=aab002 30, aab003=aab003 30, aab001=30, aad001=30), Ab01Ad01(id=500663680795410432, aab002=aab002 50, aab003=aab003 50, aab001=50, aad001=50), Ab01Ad01(id=500663681697185792, aab002=aab002 60, aab003=aab003 60, aab001=60, aad001=60), Ab01Ad01(id=500663676865347584, aab002=aab002 10, aab003=aab003 10, aab001=10, aad001=10), Ab01Ad01(id=500663679868469248, aab002=aab002 40, aab003=aab003 40, aab001=40, aad001=40)]】
```

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

