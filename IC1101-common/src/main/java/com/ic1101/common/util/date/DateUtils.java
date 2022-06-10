package com.ic1101.common.util.date;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 *
 * @author cwd
 * @date 6/10/22 9:23 AM
 */
public class DateUtils {
    /**
     * 时区 - 默认
     */
    public static final String TIME_ZONE_DEFAULT = "GMT+8";

    /**
     * 秒转换成毫秒
     */
    public static final long SECOND_MILLIS = 1000;

    public static final String FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static Date addTime(Duration duration) {
        return new Date(System.currentTimeMillis() + duration.toMillis());
    }

    public static boolean isExpired(Date time) {
        return System.currentTimeMillis() > time.getTime();
    }

    public static long diff(Date endTime, Date startTime) {
        return endTime.getTime() - startTime.getTime();
    }


    public static Date createTime(int year, int month, int day) {
        return createTime(year, month, day, 0, 0, 0);
    }

    /**
     * 创建指定时间
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   小时
     * @param minute 分钟
     * @param second 秒
     * @return 指定时间
     */
    public static Date createTime(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    public static Date max(Date d1, Date d2) {
        if (null == d1) {
            return d2;
        }
        if (null == d2) {
            return d1;
        }

        return d1.compareTo(d2) > 0 ? d1 : d2;
    }

    public static boolean beforeNow(Date date) {
        return date.getTime() < System.currentTimeMillis();
    }

    public static boolean afterNow(Date date) {
        return date.getTime() >= System.currentTimeMillis();
    }

    /**
     * 计算当期时间相差的日期
     *
     * @param field  日历字段.<br/>eg:Calendar.MONTH,Calendar.DAY_OF_MONTH,<br/>Calendar.HOUR_OF_DAY等.
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(int field, int amount) {
        return addDate(null, field, amount);
    }

    /**
     * 计算当期时间相差的日期
     *
     * @param date   设置时间
     * @param field  日历字段 例如说，{@link Calendar#DAY_OF_MONTH} 等
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(Date date, int field, int amount) {
        if (amount == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(field, amount);
        return c.getTime();
    }


}
