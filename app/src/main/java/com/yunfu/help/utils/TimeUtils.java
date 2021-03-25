package com.yunfu.help.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtils {

    private static Calendar cd = Calendar.getInstance();
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM");

    /**
     * 获取当前时间
     * @return
     */
    public static long getCurrentTime(){
        return  System.currentTimeMillis();
    }

    /**
     * 格式到秒
     * @return
     */
    public static String getMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }


    /**
     * 格式到天
     * @return
     */
    public static String getDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }


    /**
     * 获取年
     * @return
     */
    public static int getYear(){
        return  cd.get(Calendar.YEAR);
    }

    /**
     * 获取月
     * @return
     */
    public static int getMonth(){
        return  cd.get(Calendar.MONTH)+1;
    }

    /**
     * 获取日
     * @return
     */
    public static int getDay(){
        return  cd.get(Calendar.DATE);
    }

    /**
     * 获取时
     * @return
     */
    public static int getHour(){
        return  cd.get(Calendar.HOUR);
    }

    /**
     * 获取分
     * @return
     */
    public static int getMinute(){
        return  cd.get(Calendar.MINUTE);
    }

    /**
     * 获取秒
     * @return
     */
    public static int getSecond(){
        return cd.get(Calendar.SECOND);
    }


    /**
     * 获取某年某月有多少天
     */
    public static int getDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        return c.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取两个时间相差的天数
     * @return time1 - time2相差的天数
     */
    public static int getDayOffset(long time1, long time2) {
        // 将小的时间置为当天的0点
        long offsetTime;
        if (time1 > time2) {
            offsetTime = time1 - getDayStartTime(getCalendar(time2)).getTimeInMillis();
        } else {
            offsetTime = getDayStartTime(getCalendar(time1)).getTimeInMillis() - time2;
        }
        return (int) (offsetTime / 1000 * 60 * 60 * 24);
    }

    public static Calendar getDayStartTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar getCalendar(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar;
    }


    /**
     * 计算某个时间
     * @param time
     * @return
     */
    public static String getDurationInString(long time) {
        String durStr = "";
        if (time == 0) {
            return "0秒";
        }
        time = time / 1000;
        long hour = time / (60 * 60);
        time = time - (60 * 60) * hour;
        long min = time / 60;
        time = time - 60 * min;
        long sec = time;
        if (hour != 0) {
            durStr = hour + "时" + min + "分" + sec + "秒";
        } else if (min != 0) {
            durStr = min + "分" + sec + "秒";
        } else {
            durStr = sec + "秒";
        }
        return durStr;
    }


    /**
     * 判断时间大小
     * @param time1
     * @param time2
     * @return
     */
    public static boolean judgeTime(String time1, String time2){
        try {
            long long1=format2.parse(time1).getTime();
            long long2=format2.parse(time2).getTime();
            if(long1>long2){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }


    private static String getTime(Date date) {//可根据需要自行截取数据显示
        return format.format(date);
    }

    private static String getTime2(Date date) {
        return format2.format(date);
    }

    private static String getTime3(Date date) {
        return format3.format(date);
    }



    /**
     * 获取年列表
     * @return
     */
    public static List<String> getYearList(){
        List<String> list=new ArrayList<>();
        for(int i=1990;i<2060;i++){
            list.add(i+"年");
        }
        return list;
    }


    /**
     * 获取月列表
     * @return
     */
    public static List<String> getMonthList(){
        List<String> list=new ArrayList<>();
        list.add("01月");list.add("02月");list.add("03月");list.add("04月");list.add("05月");list.add("06月");list.add("07月");
        list.add("08月");list.add("09月");list.add("10月");list.add("11月");list.add("12月");
        return list;
    }


    /**
     * 获取天数列表
     * @return
     */
    public static List<String> getDateList(int year,int month){
        final int totalDate=getDayOfMonth(year,month);
        List<String> list=new ArrayList<>();
        for (int i=1;i<=totalDate;i++){
            if(i<10){
                list.add("0"+i+"日");
            }else{
                list.add(i+"日");
            }
        }
        return list;
    }


    /**
     * 获取小时
     * @return
     */
    public static List<String> getHourList(){
        List<String> list=new ArrayList<>();
        for (int i=0;i<24;i++){
            if(i<10){
                list.add("0"+i+"时");
            }else{
                list.add(i+"时");
            }
        }
        return list;
    }


    /**
     * 获取分钟
     * @return
     */
    public static List<String> getMinuteList(){
        List<String> list=new ArrayList<>();
        for (int i=0;i<60;i++){
            if(i<10){
                list.add("0"+i+"分");
            }else{
                list.add(i+"分");
            }
        }
        return list;
    }


    /**
     * 获取秒
     * @return
     */
    public static List<String> getSecondsList(){
        List<String> list=new ArrayList<>();
        for (int i=0;i<60;i++){
            if(i<10){
                list.add("0"+i+"秒");
            }else{
                list.add(i+"秒");
            }
        }
        return list;
    }
}
