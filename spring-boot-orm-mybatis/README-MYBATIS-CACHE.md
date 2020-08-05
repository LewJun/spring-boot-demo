## 开启mybatis 缓存

没有开启缓存之前，每次查询都会执行sql语句

* cache-enable=true

```yaml
mybatis:
  # 扫描xml的位置
  mapper-locations: classpath:/mappers/**/*Mapper.xml
  configuration:
    map-underscore-to-camel-case: true
    # mybatis开启缓存
    cache-enabled: true

logging:
  level:
    root: debug
```

* 在mapper上添加`@CacheNamespace`

```java
@CacheNamespace
@Repository
public interface Ab01Mapper {
    
}
```

* 测试代码

```java
    @Test
    public void testQueryByAab002Cache() {
        log.info("【1:{}】", ab01Mapper.queryByAab002("SALES"));
        log.info("【2:{}】", ab01Mapper.queryByAab002("SALES"));

        ab01Mapper.delete(20);// 这里有一个删除操作，测试删除数据后是否还会查询缓存

        log.info("【3:{}】", ab01Mapper.queryByAab002("SALES"));
    }
```

* 日志分析

```log

2020-08-05 22:35:10.867 DEBUG 3640 --- [           main] c.e.l.mapper.Ab01Mapper.queryByAab002    : ==>  Preparing: select * from ab01 where aab002=?
2020-08-05 22:35:10.898 DEBUG 3640 --- [           main] c.e.l.mapper.Ab01Mapper.queryByAab002    : ==> Parameters: SALES(String)
2020-08-05 22:35:10.926 DEBUG 3640 --- [           main] c.e.l.mapper.Ab01Mapper.queryByAab002    : <==      Total: 1
2020-08-05 22:35:10.934 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@50df37eb]
2020-08-05 22:35:10.934  INFO 3640 --- [           main] com.example.lewjun.Ab01MapperTest        : 【1:[Ab01(aab001=30, aab002=SALES, aab003=CHICAGO)]】
2020-08-05 22:35:10.934 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : Creating a new SqlSession
2020-08-05 22:35:10.934 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2c7ad4f3] was not registered for synchronization because synchronization is not active
---> 2020-08-05 22:35:10.936 DEBUG 3640 --- [           main] com.example.lewjun.mapper.Ab01Mapper     : Cache Hit Ratio [com.example.lewjun.mapper.Ab01Mapper]: 0.5
2020-08-05 22:35:10.937 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2c7ad4f3]
2020-08-05 22:35:10.937  INFO 3640 --- [           main] com.example.lewjun.Ab01MapperTest        : 【2:[Ab01(aab001=30, aab002=SALES, aab003=CHICAGO)]】
2020-08-05 22:35:10.937 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : Creating a new SqlSession
2020-08-05 22:35:10.937 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7c421952] was not registered for synchronization because synchronization is not active
2020-08-05 22:35:10.937 DEBUG 3640 --- [           main] o.s.jdbc.datasource.DataSourceUtils      : Fetching JDBC Connection from DataSource
2020-08-05 22:35:10.937 DEBUG 3640 --- [           main] o.m.s.t.SpringManagedTransaction         : JDBC Connection [HikariProxyConnection@890054387 wrapping conn0: url=jdbc:h2:mem:test user=ROOT] will not be managed by Spring
2020-08-05 22:35:10.937 DEBUG 3640 --- [           main] c.e.lewjun.mapper.Ab01Mapper.delete      : ==>  Preparing: delete from ab01 where aab001=?
2020-08-05 22:35:10.937 DEBUG 3640 --- [           main] c.e.lewjun.mapper.Ab01Mapper.delete      : ==> Parameters: 20(Integer)
2020-08-05 22:35:10.938 DEBUG 3640 --- [           main] c.e.lewjun.mapper.Ab01Mapper.delete      : <==    Updates: 1
2020-08-05 22:35:10.938 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7c421952]
2020-08-05 22:35:10.938 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : Creating a new SqlSession
2020-08-05 22:35:10.938 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@27502e5c] was not registered for synchronization because synchronization is not active
2020-08-05 22:35:10.938 DEBUG 3640 --- [           main] com.example.lewjun.mapper.Ab01Mapper     : Cache Hit Ratio [com.example.lewjun.mapper.Ab01Mapper]: 0.3333333333333333
2020-08-05 22:35:10.938 DEBUG 3640 --- [           main] o.s.jdbc.datasource.DataSourceUtils      : Fetching JDBC Connection from DataSource
2020-08-05 22:35:10.938 DEBUG 3640 --- [           main] o.m.s.t.SpringManagedTransaction         : JDBC Connection [HikariProxyConnection@1261714285 wrapping conn0: url=jdbc:h2:mem:test user=ROOT] will not be managed by Spring
2020-08-05 22:35:10.938 DEBUG 3640 --- [           main] c.e.l.mapper.Ab01Mapper.queryByAab002    : ==>  Preparing: select * from ab01 where aab002=?
2020-08-05 22:35:10.939 DEBUG 3640 --- [           main] c.e.l.mapper.Ab01Mapper.queryByAab002    : ==> Parameters: SALES(String)
2020-08-05 22:35:10.939 DEBUG 3640 --- [           main] c.e.l.mapper.Ab01Mapper.queryByAab002    : <==      Total: 1
2020-08-05 22:35:10.939 DEBUG 3640 --- [           main] org.mybatis.spring.SqlSessionUtils       : Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@27502e5c]
2020-08-05 22:35:10.939  INFO 3640 --- [           main] com.example.lewjun.Ab01MapperTest        : 【3:[Ab01(aab001=30, aab002=SALES, aab003=CHICAGO)]】
```

第一次查询的时候，执行了sql语句，

第二次查询的时候命中了缓存，没有执行sql语句，直接就返回了数据
```log
Cache Hit Ratio [com.example.lewjun.mapper.Ab01Mapper]: 0.5
```
接下来发生了一次删除操作，delete from ab01 where aab001=?

第三次查询的时候又执行了sql查询语句。
