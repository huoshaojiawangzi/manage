/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved. Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lizc.sports.common.utils;


import org.apache.commons.lang.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author ThinkGem
 * @version 2013-3-15
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils
{

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd",
        "yyyy.MM.dd", "yyyy-MM-dd HH:mm:ss.S"};
    
    public static Date parseAverage(Date timeSum, int number) throws ParseException {
        long avg = (timeSum.getTime() - parseOriginalDate().getTime())/number;
        long hours = avg/(1000* 60 * 60);
        long minutes = (avg-hours*(1000* 60 * 60))/(1000* 60);
        long second = (avg-hours*(1000* 60 * 60)-minutes*(1000*60))/(1000);
        String avgString = ""+hours+":"+minutes+":"+second;
        return parseDateByHour(avgString);
    }
    
    /**获得原始日期
     * @return
     * @throws ParseException 
     */
    public static Date parseOriginalDate() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return df.parse("1970-01-01 00:00:00");
    }
    
    public static Date parsefutureDate() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return df.parse("2070-01-01 00:00:00");
    }
    
    /**通过headDate的年月日，和tailDate的时分秒，获得连接的日期
     * @param headDate
     * @param tailDate
     * @return
     * @throws ParseException
     */
    public static Date parseDateByLink(Date headDate,Date tailDate) throws ParseException {
        headDate = parseDateToDate(headDate, "yyyy-MM-dd");
        tailDate = parseDateToDate(tailDate, "HH:mm:ss");
        String headDateString = formatDate(headDate);
        String tailDateString = formatDate(tailDate, "HH:mm:ss");
        return DateUtils.parseDate(headDateString + " "+tailDateString);
    }
    
    
    /**通过两个日期，得到两者之间所有的日期（以天为单位）
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static ArrayList<Date> parseDatesBySlot(Date startDate,Date endDate) throws ParseException{
        ArrayList<Date> dates = new ArrayList<Date>();
        if(endDate == null) {
            endDate = startDate;
        }
        Date startDateFormat =  parseDateToDate(startDate, "yyyy-MM-dd");
        Date endDateFormat = parseDateToDate(endDate, "yyyy-MM-dd");
        while( startDateFormat.getTime()<=endDateFormat.getTime()) {
            dates.add(startDateFormat);
            startDateFormat = DateUtils.parseDateByChangeDay(startDateFormat, 1);
        }
        return dates;
        
    }
    
    /**通过传递date和天数，来得到所加减天数的日期
     * @param oldDate
     * @param day
     * @return
     */
    public static Date parseDateByChangeDay(Date oldDate,int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }
    
    /**通过小时，获得格式化的时间
     * @param hour
     * @return
     */
    public static Date parseDateByHour(String hour) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormatString = "1970-01-01 "+hour; 
        Date dateFormat = null;
        try
        {
            dateFormat = df.parse(dateFormatString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return dateFormat;
    }
    /**把日期的格式转换为另一种格式
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDateToDate(Date date, String format) throws ParseException {
        DateFormat dFormat = new SimpleDateFormat(format);
        String s = dFormat.format(date);
        return dFormat.parse(s);
    }
    
    /**日期相减
     * @return
     * @throws ParseException 
     */
    public static Date subtractDate(Date dateBig,Date dateSmall) throws ParseException {
        long diff = dateBig.getTime() - dateSmall.getTime();//这样得到的差值是微秒级别
        long hours = diff/(1000* 60 * 60);
        long minutes = (diff-hours*(1000* 60 * 60))/(1000* 60);
        long second = (diff-hours*(1000* 60 * 60)-minutes*(1000*60))/(1000);
        String difference = ""+hours+":"+minutes+":"+second;
        return parseDateByHour(difference);
    }
    /**日期相加
     * @param dateBig
     * @param dateSmall
     * @return
     * @throws ParseException
     */
    public static Date addDate(Date dateBig,Date dateSmall)throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date orga =parseOriginalDate();
        long addTime = dateBig.getTime()+dateSmall.getTime()-2*orga.getTime();
        long hours = addTime/(1000* 60 * 60);
        long minutes = (addTime-hours*(1000* 60 * 60))/(1000* 60);
        long second = (addTime-hours*(1000* 60 * 60)-minutes*(1000*60))/(1000);
        String difference = "1970-01-01 "+hours+":"+minutes+":"+second;
        return df.parse(difference);
    }
    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate()
    {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern)
    {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern)
    {
        String formatDate = null;
        if (date != null)
        {
            if (pattern != null && pattern.length > 0)
            {
                formatDate = DateFormatUtils.format(date, pattern[0].toString());
            }
            else
            {
                formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
            }
        }
        else
        {
            formatDate = "";
        }
        return formatDate;
    }

    public static String formatDate1(Date date)
    {
        if (date != null)
        {
            return DateFormatUtils.format(date, "yyyy年MM月dd日");
        }
        return "    年    月    日";
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date)
    {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy/MM/dd）
     */
    public static String formatDateTime2(Date date)
    {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime()
    {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime()
    {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrentDateTime()
    {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear()
    {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth()
    {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay()
    {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek()
    {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
     * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 获取过去的天数
     * 
     * @param date
     * @return
     */
    public static long pastDays(Date date)
    {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    public static Date getDateStart(Date date)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args)
        throws ParseException
    {
        // System.out.println(formatDate(parseDate("2010/3/6")));
        // System.out.println(getDate("yyyy年MM月dd日 E"));
        // long time = new Date().getTime()-parseDate("2012-11-19").getTime();
        // System.out.println(time/(24*60*60*1000));
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * Description: <br> 验证日期的有效性
     * 
     * @param date
     *            日期
     * @return 日期为“”或者null,返回true;若日期验证正确,返回true;若日期不正确，返回false;
     * @see
     */
    public static boolean validateDate(String date)
    {
        boolean boo = false;
        if (StringUtils.isBlank(date))
        {
            boo = true;
        }
        else
        {
            if (date.matches(
                "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))"))
            {
                boo = validateDateIsExist(date.split("-"));
            }
            else if (date.matches(
                "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/(((0[13578]|1[02])/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)/(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))"))
            {
                boo = validateDateIsExist(date.split("/"));
            }
            else if (date.matches("(\\d{4}(\\/\\d{1,2}){2})"))
            {
                boo = validateDateIsExist(date.split("/"));
            }
            else if (date.matches("(\\d{4}(\\-\\d{1,2}){2})"))
            {
                boo = validateDateIsExist(date.split("-"));
            }
            else if (date.matches("\\d{8}"))
            {
                String[] dates = new String[3];
                dates[0] = date.substring(0, 4);
                dates[1] = date.substring(4, 6);
                dates[2] = date.substring(6, 8);
                boo = validateDateIsExist(dates);
            }
        }
        return boo;
    }

    /**
     * Description: <br> 验证日期的真实性
     * 
     * @param dates
     *            日期数组，例如{"2017", "2", "30"}
     * @return 真实存在，返回true；不是真实日期，返回false
     * @see
     */
    public static boolean validateDateIsExist(String[] dates)
    {
        int y = Integer.parseInt(dates[0]);
        int m = Integer.parseInt(dates[1]);
        int d = Integer.parseInt(dates[2]);
        boolean boo = true;
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0)
        {
            if (m == 2)
            {
                if (d > 29)
                {
                    boo = false;
                }
            }
            else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)
            {
                if (d > 31)
                {
                    boo = false;
                }
            }
            else if (m == 4 || m == 6 || m == 9 || m == 11)
            {
                if (d > 30)
                {
                    boo = false;
                }
            }
            else if (m > 12)
            {
                boo = false;
            }
        }
        else
        {
            if (m == 2)
            {
                if (d > 28)
                {
                    boo = false;
                }
            }
            else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)
            {
                if (d > 31)
                {
                    boo = false;
                }
            }
            else if (m == 4 || m == 6 || m == 9 || m == 11)
            {
                if (d > 30)
                {
                    boo = false;
                }
            }
            else if (m > 12)
            {
                boo = false;
            }
        }
        return boo;
    }

    /**
     * 比较一个日期距离现在的天数 Description: <br>
     * 
     * @param s
     * @return
     * @see
     */
    public static long daysBetweenNow(String s)
    {
        Date date = new Date();
        Date date1 = DateUtils.parseDate(s);
        long days = (long)((date1.getTime() - getDateStart(date).getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * Description: 对日期进行加减
     * 
     * @param oldDate
     *            旧的日期
     * @param countDate
     *            要加减的天数
     * @return 新的日期
     * @see
     */
    public static String addDays(String oldDate, String countDate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = DateUtils.parseDate(oldDate);
        String newDate = df.format(
            date1.getTime() + (Integer.parseInt(countDate) * 1000 * 3600 * 24));
        return newDate;
    }
}
