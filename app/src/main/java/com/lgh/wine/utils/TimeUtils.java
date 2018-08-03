package com.lgh.wine.utils;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by niujingtong on 2018/7/18.
 * 模块：
 */
public class TimeUtils {
    public static String changeToYMD(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String changeToTime(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.format(date);
    }

    public static String changeMDHM(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(date);
    }

    public static String changeToHM(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static long changeToLong(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        try {
            Date date = format.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        String format1 = format.format(new Date());
        return format1;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        String str = format.format(date);
        return str;
    }


    /**
     * 将一个时间转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp / (long) 1000;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 获取当天0点的毫秒数
     *
     * @return
     */
    public static long getBeginOfDay() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date beginOfDate = calendar1.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Logger.d("当天0点时间:" + format.format(beginOfDate));
        return beginOfDate.getTime();
    }

    /**
     * 获取当月0点的毫秒数
     *
     * @return
     */
    public static long getBeginOfMonth() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), 1, 0, 0, 0);
        Date beginOfDate = calendar1.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Logger.d("当月0点时间:" + format.format(beginOfDate));
        return beginOfDate.getTime();
    }

    /**
     * 获取当天日
     */
    public static int getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMinutes(String startTime, long inTime) {
        String inTime1 = changeToHM(inTime);

        //开始时间
        String[] start = startTime.split(":");
        int sth = Integer.parseInt(start[0]);//小时
        int stm = Integer.parseInt(start[1]);//秒
        int start1 = sth * 60 + stm;

        //结束时间
        String[] end = inTime1.split(":");
        int eth = Integer.parseInt(end[0]);//小时
        int etm = Integer.parseInt(end[1]);//秒
        int end1 = eth * 60 + etm;

        return end1 - start1;
    }

    public static int getMinutes(long outTime, String endTime) {
        String outTime1 = changeToHM(outTime);

        //开始时间
        String[] end = endTime.split(":");
        int sth = Integer.parseInt(end[0]);//小时
        int stm = Integer.parseInt(end[1]);//秒
        int end1 = sth * 60 + stm;

        //结束时间
        String[] outTime2 = outTime1.split(":");
        int eth = Integer.parseInt(outTime2[0]);//小时
        int etm = Integer.parseInt(outTime2[1]);//秒
        int outTime3 = eth * 60 + etm;

        return end1 - outTime3;
    }

    public static int getDay(long ctime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ctime));
        Date date = new Date(ctime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String format1 = format.format(date);

        int i = calendar.get(Calendar.DAY_OF_MONTH);
        return i;
    }

    public static String getDeadline(long create_time) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date(create_time));
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH),
                calendar1.get(Calendar.DAY_OF_MONTH) + 2, 0, 0, 0);
        Date date1 = calendar1.getTime();

        Calendar calendar2 = Calendar.getInstance();
        Date date2 = calendar2.getTime();

        long second = (date1.getTime() - date2.getTime()) / (long) 1000;
        int hour = (int) (second / 3600);
        int minute = (int) ((second - hour * 3600) / 60);
        int se = (int) (second - hour * 300 - minute * 60) / 1000;
        String str = hour + "小时" + minute + "分钟" + se + "秒";
        return str;
    }
}
