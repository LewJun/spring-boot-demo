# spring-boot-orm-jdbcTemplate

> 一个spring boot集成jdbcTemplate的例子

[TOC]

## spring-boot-starter-jdbc 依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
```

## 数据源配置

```yaml
# DataSource Config
spring:
  datasource:
    driver-class-name: org.h2.Driver
    schema: classpath:db/schema-h2.sql
    data: classpath:db/data-h2.sql
    url: jdbc:h2:mem:test
    username: root
    password: test
```

## 增删改查操作

[Ab01ServiceImpl.java](src/main/java/com/example/lewjun/service/impl/Ab01ServiceImpl.java)

### update
凡是增删改的操作，都调用这个方法
```java
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(final Ab01 ab01) {
        return jdbcTemplate.update("insert into ab01(aab002, aab003) values(?, ?)", ab01.getAab002(), ab01.getAab003());
    }

    @Override
    public int delete(final Integer pk) {
        return jdbcTemplate.update("delete from ab01 where aab001=?", pk);
    }

    @Override
    public int update(final Ab01 ab01) {
        return jdbcTemplate.update("update ab01 set aab002=?, aab003=? where aab001=?", ab01.getAab002(), ab01.getAab003(), ab01.getAab001());
    }
```

### query
用于返回多行数据，例如list
```java

    @Override
    public List<Ab01> queryAll() {
        return jdbcTemplate.query("select * from ab01", (resultSet, i) ->
                new Ab01()
                        .setAab001(resultSet.getInt("aab001"))
                        .setAab002(resultSet.getString("aab002"))
                        .setAab003(resultSet.getString("aab003"))
        );
    }
```

* 使用query(sql, ...params, new BeanPropertyRowMapper<>(Class<T> cls))的方式返回集合
上面也可以使用如下方式：

```java

    @Override
    public List<Ab01> queryAll() {
//        return jdbcTemplate.query("select * from ab01", (resultSet, i) ->
//                new Ab01()
//                        .setAab001(resultSet.getInt("aab001"))
//                        .setAab002(resultSet.getString("aab002"))
//                        .setAab003(resultSet.getString("aab003"))
//        );


//        使用错误new BeanPropertyRowMapper<>()
//        java.lang.IllegalStateException: Mapped class was not specified
//        return jdbcTemplate.query("select * from ab01", new Object[]{}, new BeanPropertyRowMapper<>());// ×××

        // 需要把Ab01.class传入到BeanPropertyRowMapper中
        return jdbcTemplate.query("select * from ab01", new Object[]{}, new BeanPropertyRowMapper<>(Ab01.class));
    }
```

### queryForObject
用于返回一行一列数据，例如Integer类型
```java
jdbcTemplate.queryForObject("select count(1) from ab01", Integer.class)
```

或者返回一个实体对象 例如Ab01类型，需要用BeanPropertyRowMapper转换
```java
jdbcTemplate.queryForObject("select * from ab01 where aab001=?", new Object[]{id}, new BeanPropertyRowMapper<>(Ab01.class))
```

### queryForList
并不能返回一个对象的集合，而是返回多行一列的集合
```java
    @Override
    public List<Integer> queryAllAab001() {
        return jdbcTemplate.queryForList("select aab001 from ab01", new Object[]{}, Integer.class);
    }
```

## 单元测试
[Ab01ServiceTest.java](src/test/java/com/example/lewjun/Ab01ServiceTest.java)

```java
    @Test
    public void test() {
        log.info("【queryAll: {}】", ab01Service.queryAll());
        log.info("【queryById: {}】", ab01Service.queryById(20));
        log.info("【getAb01Count: {}】", ab01Service.getAb01Count());

        log.info("【queryByAab002: {}】", ab01Service.queryByAab002("SALES"));
        log.info("【queryByAab003: {}】", ab01Service.queryByAab003("DALLAS"));

        log.info("【delete: {}】", ab01Service.delete(40));

        ab01Service.update(new Ab01().setAab001(10).setAab002("aab002").setAab003("aab003"));

        ab01Service.save(new Ab01().setAab002("002").setAab003("003"));

        log.info("【queryAll: {}】", ab01Service.queryAll());
    }
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

