package cn.layku.verify.kit.tool;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author dongdingzhuo
 * @Title: DateUtil
 * @Package cn.layku.verify.kit.tool;
 * @Description: 日期时间工具类
 * @date 2020/4/2 10:44
 */
public class DateUtil {
    /**
     * 日期时间转换为字符串格式
     *
     * @param source  待处理的日期
     * @param pattern 转换的格式
     * @return
     */
    public static String dateToStr(LocalDateTime source, String pattern) {
        return source.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String longToDate(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return sdf.format(date);
    }

    /**
     * 字符串转换为日期格式
     *
     * @param source  待处理的字符串
     * @param pattern 转换的格式
     * @return
     */
    public static LocalDateTime strToDate(String source, String pattern) {
        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(pattern));
    }

    public static String currentDateTimeStr() {
        return dateToStr(LocalDateTime.now(), "yyyyMMddHHmmss");
    }

    public static String currentDateTimeStr2() {
        return dateToStr(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String currentYearStr() {
        return dateToStr(LocalDateTime.now(), "yyyy");
    }

    public static String currentYearMonthStr() {
        return dateToStr(LocalDateTime.now(), "yyyyMM");
    }

    public static String currentDateStr() {
        return dateToStr(LocalDateTime.now(), "yyyyMMdd");
    }

    public static String currentSimpleDateStr() {
        return dateToStr(LocalDateTime.now(), "yyMMdd");
    }

    public static long currentMillis() {
        return Instant.now().toEpochMilli();
    }

    public static long currentSeconds() {
        return Instant.now().getEpochSecond();
    }
}
