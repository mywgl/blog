package com.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description
 * @Date 2021/3/28 15:28
 * @Version 1.0
 */
public class MD5Utils {
    public static String code(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] byteDigest = md5.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte b : byteDigest) {
                i = b;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return  null;
        }
    }
    
    public static void main(String[] args) {
        String code = MD5Utils.code("123");
        System.out.println(code);
    }
}
