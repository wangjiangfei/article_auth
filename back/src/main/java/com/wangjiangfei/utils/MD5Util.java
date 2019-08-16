package com.wangjiangfei.utils;

import java.security.MessageDigest;

/**
 * @author wangjiangfei
 * @date 2019/5/29 10:47
 * @description
 */
public class MD5Util {

    public static String md5Encode(String inStr) {
        MessageDigest md5 = null;
        byte[] byteArray ;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byteArray = inStr.getBytes("UTF-8");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

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
        System.out.println(md5Encode("123456"));
    }


}

