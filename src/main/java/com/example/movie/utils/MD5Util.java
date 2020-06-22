package com.example.movie.utils;

import com.example.movie.component.SecurityConstant;

import java.security.MessageDigest;

/**
 * @Description: MD5加密工具
 * @Author: Ou
 * @Date: 2020/6/10
 */
public class MD5Util {

    public static String encode(String password) {
        password = password + SecurityConstant.SALT;
        MessageDigest md5;
        try {
            //MessageDigest类为应用程序提供信息摘要算法的功能，如 MD5或SHA算法。
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        byte[] byteArray = password.getBytes();
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encode("admin"));
    }

}
