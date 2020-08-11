package com.example.lewjun;

import com.example.lewjun.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Date;
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
        final LocalDateTime localDateTime = LocalDateTime.now();
        log.info("【Hour: {}】", localDateTime.getHour());
        log.info("【Minute: {}】", localDateTime.getMinute());
        log.info("【Second: {}】", localDateTime.getSecond());
        log.info("【milli second: {}】", localDateTime.get(ChronoField.MILLI_OF_SECOND));
        log.info("【dayOfMonth: {}】", localDateTime.get(ChronoField.DAY_OF_MONTH));
    }

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
}
