package com.lizc.manage.sys.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-06-27 14:28
 **/
public class MD5 {
    private static String byteArrayToHex(byte[] b) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        char[] ob = new char[2];
        StringBuilder s = new StringBuilder();
        for (byte b1 : b) {
            ob[0] = Digit[(b1 >>> 4) & 0X0F];
            ob[1] = Digit[b1 & 0X0F];
            s.append(new String(ob));
        }
        return s.toString();
    }

    public static String md5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return byteArrayToHex(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            return null;
        }
    }
}
