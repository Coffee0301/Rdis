package com.coffee.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static final String salt="1a2b3c4d";

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    //99123456c3
    public static  String inputPassToFromPass(String inputPass){
        String pass=""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        System.out.println(pass);
        return md5(pass);
    }
    public static String fromPassToDBPass(String str,String salt){
        String pass=""+salt.charAt(0)+salt.charAt(2)+str+salt.charAt(5)+salt.charAt(4);
        return md5(pass);
    }

    public static void main(String[] args) {

        String s=inputPassToFromPass("123456");
        System.out.println(s);
        System.out.println(fromPassToDBPass(s,"1a2b3c4d"));
    }
}
