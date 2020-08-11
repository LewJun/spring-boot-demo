# java 8 新日期api的使用

[TOC]

## 所在包
java.time包

## 使用

[DateTimeTest.java](src/test/java/com/example/lewjun/DateTimeTest.java)
```java
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

@Slf4j
public class DateTimeTest {
    @Test
    public void testLocalDate() {
        // 获取当前日期
        final LocalDate localDate = LocalDate.now();
        log.info("【localDate: {}】", localDate);
        log.info("【year: {}】", localDate.getYear());
        log.info("【month: {}】", localDate.getMonthValue());
        log.info("【dayOfMonth: {}】", localDate.getDayOfMonth());
        log.info("【dayOfWeek: {}】", localDate.getDayOfWeek().getValue());
        log.info("【dayOfYear: {}】", localDate.getDayOfYear());
        for (final TextStyle textStyle : TextStyle.values()) {
            log.info("【month: {}, {}】", textStyle.name(), localDate.getMonth().getDisplayName(textStyle, Locale.CHINA));
        }
    }

    @Test
    public void testLocalDateCreate() {
        final LocalDate localDate = LocalDate.of(2020, 8, 11);
        log.info("【localDate: {}】", localDate);
        log.info("【year: {}】", localDate.getYear());
        log.info("【month: {}】", localDate.getMonthValue());

        log.info("【LocalDate.parse: {}】", LocalDate.parse("2020-08-23"));
        log.info("【LocalDate.parse: {}】", LocalDate.parse("2020-08-23", DateTimeFormatter.ISO_LOCAL_DATE));

        log.info("【change date: {}】", localDate.plusYears(1).plusMonths(2).plusDays(-3));
    }

    @Test
    public void testZonedDateTime() {
        final ZonedDateTime zonedDateTime = ZonedDateTime.now();
        log.info("【zonedDateTime: {}】", zonedDateTime);// 【zonedDateTime: 2020-08-11T16:48:06.093+08:00[Asia/Shanghai]】
        log.info("【zone: {}】", zonedDateTime.getZone().getId());// 【zone: Asia/Shanghai】
        log.info("【nano: {}】", zonedDateTime.getNano());// 【nano: 81000000】
        log.info("【getChronology: {}】", zonedDateTime.getChronology());// 【getChronology: ISO】
        log.info("【getOffset: {}】", zonedDateTime.getOffset());// 【getOffset: +08:00】
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("【Hour: {}】", localDateTime.getHour());
        log.info("【Minute: {}】", localDateTime.getMinute());
        log.info("【Second: {}】", localDateTime.getSecond());
        log.info("【milli second: {}】", localDateTime.get(ChronoField.MILLI_OF_SECOND));
        log.info("【dayOfMonth: {}】", localDateTime.get(ChronoField.DAY_OF_MONTH));
    }
}
```

## Date, long, LocalDate, LocalDateTime之间的转换

[DateUtils.java](src/main/java/com/example/lewjun/util/DateUtils.java)
```java
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Date, long, LocalDate, LocalDateTime之间的转换
 */
public class DateUtils {
    public static Date asDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(final Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(final Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static long asLong(final LocalDate localDate) {
        return asDate(localDate).getTime();
    }

    public static long asLong(final LocalDateTime localDateTime) {
        return asDate(localDateTime).getTime();
    }

    public static LocalDate asLocalDate(final long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(final long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
```


测试用例

```java
    @Test
    public void testLocalDateAsDate() {
        final Date date = DateUtils.asDate(LocalDate.now());
        log.info("【date: {}】", date);//【date: Tue Aug 11 00:00:00 CST 2020】
    }

    @Test
    public void testLocalDateTimeAsDate() {
        final Date date = DateUtils.asDate(LocalDateTime.now());
        log.info("【date: {}】", date);// 【date: Tue Aug 11 17:14:41 CST 2020】
    }

    @Test
    public void testDateAsLocalDate() {
        final Date date = new Date();
        final LocalDate localDate = DateUtils.asLocalDate(date);
        log.info("【localDate: {}】", localDate);
    }

    @Test
    public void testLongAsLocalDate() {
        final long stamp = System.currentTimeMillis();
        log.info("【localDate: {}】", DateUtils.asLocalDate(stamp));
    }
```
