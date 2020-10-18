package com.tcl.uf.common.base.util;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: TimeUtils
 * @Description:
 * @date 2018/1/15 上午4:35
 */

public class TimeUtils {

    /**
     * 周x 定义
     */
    public final static int WEEK_SUN = 1;
    public final static int WEEK_MON = 2;
    public final static int WEEK_TUE = 3;
    public final static int WEEK_WED = 4;
    public final static int WEEK_THU = 5;
    public final static int WEEK_FRI = 6;
    public final static int WEEK_SAT = 7;

    /**
     * 要用到的DATE Format的定义
     */
    private static String DATE_FORMAT_DATEONLY = "yyyy-MM-dd";
    public static String DATE_FORMAT_DATEONLY2 = "yyyyMMdd";
    public static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取今天年月日 日期
     *
     * @return
     */
    public static String getToday() {
        SimpleDateFormat s = new SimpleDateFormat(DATE_FORMAT_DATEONLY);
        return s.format(new Date());
    }

    /**
     * 获取昨天年月日 日期
     *
     * @return
     */
    public static String getYesterday() {
        SimpleDateFormat s = new SimpleDateFormat(DATE_FORMAT_DATEONLY);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);

        return s.format(calendar.getTime());
    }
    
    public static String addDay(Date time, int amount, String pattern) {
        SimpleDateFormat s = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return s.format(calendar.getTime());
    }

    public static Date addMonth(Date time, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }
    
    public static Date addDay(Date time, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }
    
    public static Date addYear(Date time, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    public static Date addWeek(Date time, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.WEEK_OF_MONTH, amount);
        return calendar.getTime();
    }

    /**
     * DATE_FORMAT_DATEONLY to Unix
     *
     * @param date
     * @return
     */
    public static long parseDateToUnix(String date) {
        SimpleDateFormat s = new SimpleDateFormat(DATE_FORMAT_DATEONLY);
        try {
            Date parse = s.parse(date);
            return parse.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return System.currentTimeMillis() / 1000;
        }
    }

    public static String getCurrentTime() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = s.format(new Date());
        return format;
    }

    public static String getBeforDay() {

        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);

        return s.format(calendar.getTime());
    }

    public static String formatDate(Date date) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = s.format(date);
        return format;
    }
    
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat s = new SimpleDateFormat(pattern);
        String format = s.format(date);
        return format;
    }

    /**
     * 获取当天周几 返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
     *
     * @return
     */
    public static int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取当天几号
     * @return
     */
    public static int getDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String getNowTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static long getTimeLong(String date) throws ParseException {
        if (date == null || Objects.equals(date, "")) {
            date = getNowTimeFormat();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.parse(date).getTime();
    }

    /**
     * 获取当前格式化的时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date getDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse(date);
        return parse;
    }

    public static Timestamp getTimestamp(String date) {
        return Timestamp.valueOf(date);
    }

    //date格式为  yyyyMMdd  
    public static Timestamp getTimestampByDateStr(String date) throws ParseException {
    	
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_DATEONLY2);
        Date parse = simpleDateFormat.parse(date);
    	
        return new Timestamp(parse.getTime());
    }

    public static Timestamp getTimestamp(long time) {
        return new Timestamp(time);
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());

    }

    /**
     * 得到时间戳格式字串
     *
     * @param date
     * @return
     */
    public static String getTimeStampStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 得到时间戳格式字串
     *
     * @param date
     * @return
     */
    public static String getDateStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    /**
     * 得到时间戳格式字串
     *
     * @param date
     * @return
     */
    public static String getTimeStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 得到长日期格式字串
     *
     * @return
     */
    public static String getLongDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取短日期格式字符(天)
     *
     * @return
     */
    public static String getShortDayDateStr(long longTime) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(longTime));
    }

    /**
     * 获取短日期格式字符(天)
     *
     * @return
     */
    public static String getShortDayDateStr() {
        return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
    }

    /**
     * 得到长日期格式字串
     *
     * @return
     */
    public static String getLongDateStr(long longTime) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(longTime));
    }

    public static String getLongDateStr(Timestamp time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static String getDateStr(Long time) {
        if (time != null && time > 0) {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time * 1000));
        }
        return "";
    }

    /**
     * 时间转换成时间戳
     *
     * @param time
     * @return
     */
    public static long dateToTimestamp(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(time);
            long ts = date.getTime() / 1000;
            return ts;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * TODO 获取当前所在月份的起始月份
     * @return
     */
    public static String getCurrentBeginMonth(){
        Calendar now = Calendar.getInstance();
        String today = null;
        if ((now.get(Calendar.MONTH) + 1) <10){
            today = now.get(Calendar.YEAR)+"-0"+(now.get(Calendar.MONTH) + 1)+"-0" + 1 +" "+"00:00:00";
        }else {
            today = now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1)+"-0" + 1 + " "+"00:00:00";
        }
        System.out.println("字符串转成日期：" + today);
        return today;
    }

    /**
     * TODO 获取当前所在月份的结束月份
     * @return
     */
    public static String getCurrentEndMonth(){
        Calendar now = Calendar.getInstance();
        String today = null;
        if ((now.get(Calendar.MONTH) + 1 + 1) < 10){
            today = now.get(Calendar.YEAR)+"-0"+(now.get(Calendar.MONTH) + 1 + 1) + "-0" + 1 + " "+"00:00:00";
        }else {
            today = now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1 + 1) + "-0" + 1 + " "+"00:00:00";
        }
        System.out.println("字符串转成日期：" + today);
        return today;
    }
    
    /**
     * TODO 当前时间累加分钟数
     * @return
     */
    public static String addMinuteByCurrentTime(int minute) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, minute);
		return sdf.format(nowTime.getTime());
    }
    
    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestamp 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(String timestamp, String formats) {
    	if(StringUtils.isEmpty(formats)) {
    		formats = "yyyy-MM-dd HH:mm:ss";
    	}
    	if(StringUtils.isEmpty(timestamp)) {
    		return "";
    	}
    	//Unix时间戳转换指定格式
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat(formats, Locale.CHINA);
    	Long times = Long.parseLong(timestamp) * 1000;
        return simpleDateFormat.format(new Date(times));
    }
    
    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestamp 时间戳 如：1473048265;
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(long timestamp, String formats) {
    	if(StringUtils.isEmpty(formats)) {
    		formats = "yyyy-MM-dd HH:mm:ss";
    	}
    	if(StringUtils.isEmpty(timestamp)) {
    		return "";
    	}
    	//Unix时间戳转换指定格式
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat(formats, Locale.CHINA);
    	Long times = timestamp * 1000;
        return simpleDateFormat.format(new Date(times));
    }
    
    public static String getLongDate(Timestamp time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }
    
    public static boolean compareDate(String joinTime,String today) {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	boolean isCompare=false;
    	try {
			Date dt1 = df.parse(joinTime);
			Date dt2 = df.parse(today);
			if(dt2.getTime()>dt1.getTime()) //今天已经大于参与日期了。
			{
				isCompare=true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return isCompare;
    }
    
    public static String getTimeDifference(String strTime1,String strTime2) {
    	String timeDifference = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   try{
			   Date now = df.parse(strTime1);
			   Date date=df.parse(strTime2);
			   long l=now.getTime()-date.getTime();       //获取时间差
			   long day=l/(24*60*60*1000);
			   long hour=(l/(60*60*1000)-day*24);
			   long min=((l/(60*1000))-day*24*60-hour*60);
			   long s=(l/1000-day*24*60*60-hour*60*60-min*60);
			   timeDifference=""+day+"天"+hour+"小时"+min+"分"+s+"秒";
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return timeDifference;
	}
    
    /**
     * 获取两个日期之间的所有日期
     * 
     * @param startTime
     *            开始日期
     * @param endTime
     *            结束日期
     * @return
     */
    public static List<String> getSectionDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
    
    public static String getMillisecondTime(Timestamp timestamp) {
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(timestamp);
    }
    
    public static String getMillisecondTime() {
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
    }
    
    public static long getDayCurrentUnixTime() {
        Date date =new Date();
        return date.getTime()/1000;
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static long getDayEndUnixTime() {
    	Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),23, 59, 59);
        Date data = calendar1.getTime();
        return data.getTime()/1000;
    }
    
    public static String getTimsNumber() {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        String format = s.format(new Date());
        return format;
    }
    
    public static String getTimestampDateStr1(Timestamp time) {
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(time);
    }
    
    public static String getTimestampDateStr2(Timestamp time) {
        return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(time);
    }
    
    public static String getTimestampDate(Timestamp time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }
    
    public static boolean compareDate(String startTime,String endTime,String compareTime) {
		boolean compare = false;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date d1 = sf.parse(startTime);
			Date d2 = sf.parse(endTime);
			Date d3 = sf.parse(compareTime);
			if (d3.before(d2) && d3.after(d1)) {
				compare = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return compare;
	}
    
    public static Timestamp conversionDate(String conversionDate) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Timestamp ts =null;
    	try {
			Date date=df.parse(conversionDate);
			ts = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return ts;
	}

    public static boolean isOverlap(Date leftStartDate, Date leftEndDate, Date rightStartDate, Date rightEndDate) {
        boolean intersect = false;
        if (((leftStartDate.getTime() >= rightStartDate.getTime())
                && leftStartDate.getTime() < rightEndDate.getTime())
                || ((leftStartDate.getTime() > rightStartDate.getTime())
                && leftStartDate.getTime() <= rightEndDate.getTime())
                || ((rightStartDate.getTime() >= leftStartDate.getTime())
                && rightStartDate.getTime() < leftEndDate.getTime())
                || ((rightStartDate.getTime() > leftStartDate.getTime())
                && rightStartDate.getTime() <= leftEndDate.getTime())) {
            intersect = true;
        }
        return intersect;
    }

    public static boolean isOverlap(String startTime1, String endTime1, String startTime2, String endTime2) {
        boolean intersect = false;
        try {
            /* 时间1区间[开始时间 结束时间]*/
            Date leftStartDate = format.parse(startTime1);
            Date leftEndDate = format.parse(endTime1);

            /* 时间2区间[开始时间 结束时间]*/
            Date rightStartDate = format.parse(startTime2);
            Date rightEndDate = format.parse(endTime2);

            /*判断*/
            if (((leftStartDate.getTime() >= rightStartDate.getTime())
                    && leftStartDate.getTime() < rightEndDate.getTime())
                    || ((leftStartDate.getTime() > rightStartDate.getTime())
                    && leftStartDate.getTime() <= rightEndDate.getTime())
                    || ((rightStartDate.getTime() >= leftStartDate.getTime())
                    && rightStartDate.getTime() < leftEndDate.getTime())
                    || ((rightStartDate.getTime() > leftStartDate.getTime())
                    && rightStartDate.getTime() <= leftEndDate.getTime())) {
                intersect = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return intersect;
    }

    /**
     * TODO 获取指定时间距离当天已过去的秒数
     * @param date
     * @return java.lang.Long
     * @date 2019/11/26 19:07
     */
    public static long  getOverTimeFromDate(Date date) {
        long overTime = 0;
        try {
            SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_DATEONLY);
            overTime = (date.getTime() - (sf.parse(sf.format(date)).getTime()))/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  overTime;
    }

    /**
     * TODO 秒转时分 28800 ->  08:0
     * @param time
     * @return java.lang.String
     * @date 2019/11/27 0:24
     */
    public static String secToHM(int time) {
        StringBuilder stringBuilder = new StringBuilder();
        Integer hour = time / 3600;
        Integer minute = time / 60 % 60;
        //Integer second = time % 60;
        if(hour<10){
            stringBuilder.append("0");
        }
        stringBuilder.append(hour);
        stringBuilder.append(":");
        if(minute < 10){
            stringBuilder.append("0");
        }
        stringBuilder.append(minute);
        return stringBuilder.toString();
    }


    /**
     * TODO 时分转秒 08:00 -> 28800
     * @param time
     * @return int
     * @date 2019/11/27 0:16
     */
    public static int hmToSec(String time) {
        String[] str = time.split(":");
        Integer hour = Integer.valueOf(str[0]);
        Integer minute = Integer.valueOf(str[1]);
        Integer second = hour * 3600 + minute * 60;
        return second;
    }
    
    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat s = new SimpleDateFormat(pattern);
        try {
			return s.parse(date);
		} catch (ParseException e) {
			return null;
		}
    }
    
    public static int calcDays(Date start, Date end) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(start);
    	int days = 0;
    	while (c.getTime().before(end) || c.getTime().equals(end)) {
    		++days;
    		c.add(Calendar.DAY_OF_MONTH, 1);
    	}
    	return days;
    }

    /**
     * TODO 日期转年月日数组
     * @param date
     * @return int[] year，month，day
     * @date 2019/12/18 17:16
     */
    public static int[] dateToArr(Date date) {
        int[] dateYMD = new int[3];
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);

        dateYMD[0] = calender.get(Calendar.YEAR);
        dateYMD[1] = calender.get(Calendar.MONTH) + 1;
        dateYMD[2] = calender.get(Calendar.DATE);

        return dateYMD;
    }

    /**
     * TODO 年月日数组转为日期
     * @param arr
     * @return Date
     * @date 2019/12/18 17:16
     */
    public static Date arrToDate(int[] arr) {
        Calendar calender = Calendar.getInstance();
        arr[1] = arr[1] > 0 ? arr[1] - 1 : arr[1];
        calender.set(arr[0], arr[1], arr[2]);
        return calender.getTime();
    }
    
    public static Date[] getFirstAndLastTimeOfDay(Date day) {
    	if (day == null) {
    		 day = new Date();
    	}
    	String date = formatDate(day, "yyyyMMdd");
		Date min = parseDate(date, "yyyyMMdd");
    	Date max = parseDate(date + "235959999", "yyyyMMddHHmmssSSS");
    	return new Date[]{min, max};
    }
    
    public static Date[] getFirstAndLastDayOfMonth(Date day) {
    	if (day == null) {
    		 day = new Date();
    	}
    	Calendar c = Calendar.getInstance();
    	c.setTime(day);
    	int actualMinimum = c.getActualMinimum(Calendar.DAY_OF_MONTH);
    	int actualMaximum = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    	Date actualMinimumDay = parseDate(formatDate(day, "yyyyMM") + (actualMinimum < 10 ? "0" : "") + actualMinimum, "yyyyMMdd");
    	Date actualMaximumDay = parseDate(formatDate(day, "yyyyMM") + (actualMaximum < 10 ? "0" : "") + actualMaximum, "yyyyMMdd");
    	return new Date[]{actualMinimumDay, actualMaximumDay};
    }
    
    /**
     * 获取指定日期月份最后几天
     * @param day
     * @param N
     * @return
     */
    public static Date[] getLastNDayOfMonth(Date day, int N) {
    	if (day == null) {
    		 day = new Date();
    	}
    	Calendar c = Calendar.getInstance();
    	c.setTime(day);
    	int actualMaximum = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    	Date actualMaximumDay = parseDate(formatDate(day, "yyyyMM") + (actualMaximum < 10 ? "0" : "") + actualMaximum, "yyyyMMdd");
    	c.setTime(actualMaximumDay);
    	c.add(Calendar.DAY_OF_MONTH, -N);
    	return new Date[]{parseDate(formatDate(c.getTime(), "yyyyMMdd"), "yyyyMMdd"), actualMaximumDay};
    }
    
    public static Date[] getFirstAndLastDayOfYear(Date day) {
    	if (day == null) {
    		 day = new Date();
    	}
    	Calendar c = Calendar.getInstance();
    	c.setTime(day);
    	int actualMinimum = c.getActualMinimum(Calendar.DAY_OF_YEAR);
    	int actualMaximum = c.getActualMaximum(Calendar.DAY_OF_YEAR);
    	Date actualMinimumDay = parseDate(formatDate(day, "yyyy01") + (actualMinimum < 10 ? "0" : "") + actualMinimum, "yyyyMMdd");
    	Date actualMaximumDay = addDay(actualMinimumDay, actualMaximum - 1);
    	return new Date[]{actualMinimumDay, actualMaximumDay};
    }
    
    public static boolean isDay(int day) {
    	Calendar c = Calendar.getInstance();
    	return day == c.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean compareDateBefore(String time,String compareTime) {
        boolean compare = false;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date sTime = sf.parse(time);
            Date cTime = sf.parse(compareTime);
            if (cTime.before(sTime)) {
                compare = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return compare;
    }

    public static boolean compareDateAfter(String time,String compareTime) {
        boolean compare = false;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date sTime = sf.parse(time);
            Date cTime = sf.parse(compareTime);
            if (cTime.after(sTime)) {
                compare = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return compare;
    }

    /**
     *字符串的日期格式的计算
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static String getLastTimeInterval() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        String lastBeginDate = sdf.format(calendar1.getTime());
        String lastEndDate = sdf.format(calendar2.getTime());
        return lastBeginDate + "," + lastEndDate;
    }


    public static String getCurrentTimes() {
        SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
        String format = s.format(new Date());
        return format;
    }

    public static Long getLongTimes(String strDate) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long timestamp = null;
        try {
            Date date = format.parse(strDate);
            timestamp=date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public static String getTimsStrNumber() {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = s.format(new Date());
        return format;
    }

    public static Integer getLengthOfMonth(int year, int month) {
        LocalDate now = LocalDate.of(year, month, 1);
        int daysOfMonth = now.lengthOfMonth();
        return daysOfMonth;
    }

    public static Date[] getFirstAndLastDayOfMonthWeek(int year, int month, int week) {
        Calendar cal = Calendar.getInstance();
        if(year != 0){
            cal.set(Calendar.YEAR,year);
        }
        cal.set(Calendar.MONTH,month - 1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date monthFirstDay = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date monthLastDay = cal.getTime();
        cal.set(Calendar.WEEK_OF_MONTH,week);
        cal.set(Calendar.DAY_OF_WEEK,1);
        Date weekFirstDay = cal.getTime();
        cal.set(Calendar.DAY_OF_WEEK,7);
        Date weekLastDay =  cal.getTime();

        if(weekFirstDay.getTime() < monthFirstDay.getTime()){
            weekFirstDay = monthFirstDay;
        }
        if(weekLastDay.getTime() > monthLastDay.getTime()){
            weekLastDay = monthLastDay;
        }
        if(weekFirstDay.getTime() > monthLastDay.getTime()){
            return null;
        }

        return new Date[]{weekFirstDay, weekLastDay};
    }

    public static Integer getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer dayOfWeed = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeed;
    }

    /**
     * 给指定时间加上N个小时
     * @param day 当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @param hour 需要加的时间
     * @return
     */
    public static String addDateMinut(String day, int hour) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return format.format(cal.getTime());
    }

    /**
     * 给当前时间加上N个月
     * @param month 需要加的时间
     * @return
     */
    public static long addDateMonth(int month) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);

        Date newDate  = cal.getTime();
        return newDate.getTime();
    }

    /**
     * 置顶时间添加多个月
     * @param month 月
     * @param month 月
     * @return
     */
    public static long addDateMonth(Date date,int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);

        Date newDate  = cal.getTime();
        return newDate.getTime();
    }

    public static int compare(Date first, Date second) {
        return first.compareTo(second);
    }

    /**
     * 每月1号
     */
    public static Date nextMonthFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }
}
