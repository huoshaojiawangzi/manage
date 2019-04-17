package com.lizc.sports.common.utils;


import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName: StringUtils
 * @Description: 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author sunp@sdhuijin.cn
 * @date 2015年11月17日 上午11:28:15
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils
{

    /**
     * 保留任意位数的有效数字
     * 
     * @return
     */
    public static String holdValid(String oldS, int valid)
    {
        if (isBlank(oldS) || oldS.length() >= valid)
        {
            return oldS;
        }
        int i = 0;
        StringBuffer prefix = new StringBuffer();
        while (i < valid - oldS.length())
        {
            prefix.append("0");
            i++ ;
        }
        return prefix.append(oldS).toString();
    }

    public static String lowerFirst(String str)
    {
        if (StringUtils.isBlank(str))
        {
            return "";
        }
        else
        {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
    }

    public static String upperFirst(String str)
    {
        if (StringUtils.isBlank(str))
        {
            return "";
        }
        else
        {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html)
    {
        if (isBlank(html))
        {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val)
    {
        if (val == null)
        {
            return 0D;
        }
        try
        {
            return Double.valueOf(trim(val.toString()));
        }
        catch (Exception e)
        {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val)
    {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val)
    {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val)
    {
        return toLong(val).intValue();
    }

    /**
     * 转换为BigDecimal类型
     */
    public static BigDecimal toBigDecimal(Object val)
    {
        BigDecimal bd = null;
        if (val != null)
        {
            try
            {
                bd = new BigDecimal(trim(val.toString()));
            }
            catch (Exception e)
            {}
        }
        return bd;
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request)
    {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr))
        {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }
        else if (isNotBlank(remoteAddr))
        {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }
        else if (isNotBlank(remoteAddr))
        {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * Description: 生成随机名称
     * 
     * @param flag
     *            true 文件名 false 非文件名
     * @return
     * @see
     */
    public static String generateRandomFilename(boolean flag)
    {
        String RandomFilename = "";
        int random = 0;
        java.util.Date dt = new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat fmt = null;
        if (flag)
        {
            Random rand = new Random();// 生成随机数
            random = rand.nextInt();
            fmt = new SimpleDateFormat("yyyyMMddHHmmss_");
        }
        else
        {
            random = (int)(((Math.random() * 9 + 1) * 100000));
            fmt = new SimpleDateFormat("yyyyMMddHHmm");
        }
        String now = fmt.format(dt);
        RandomFilename = now + String.valueOf(random > 0 ? random : (-1) * random);
        return RandomFilename;
    }

    public static String generateRandomName()
    {
        java.util.Date dt = new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String now = fmt.format(dt);
        return now;
    }

    /**
     * 判断所有的字符串是否都不为空
     * 
     * @param args
     *            多个字符串
     * @return 都不为空，返回true，反之false
     */
    public static boolean isAllNotNull(String... args)
    {
        for (String arg : args)
        {
            if (arg == null || arg.trim().length() == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断所有的字符串是否都为空
     * 
     * @param args
     *            多个字符串
     * @return 都为空，返回true，反之false
     */
    public static boolean isAllNull(String... args)
    {
        for (String arg : args)
        {
            if (arg != null && arg.trim().length() != 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Description: 获取18位唯一码
     * 
     * @return
     * @see
     */
    public static String getRandomUUID()
    {
        // 1、创建时间戳
        java.util.Date dateNow = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateNowStr = dateFormat.format(dateNow);
        StringBuffer sb = new StringBuffer(dateNowStr);
        // 2、创建随机对象
        Random rd = new Random();
        // 3、产生4位随机数
        String n = "";
        int rdGet; // 取得随机数
        do
        {
            rdGet = Math.abs(rd.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
            // rdGet=Math.abs(rd.nextInt())%26+97; //产生97到122的随机数(a-z的键位值)
            char num1 = (char)rdGet;
            String dd = Character.toString(num1);
            n += dd;
        }
        while (n.length() < 4);// 假如长度小于4
        sb.append(n);
        // 4、返回唯一码
        return sb.toString();
    }
}
