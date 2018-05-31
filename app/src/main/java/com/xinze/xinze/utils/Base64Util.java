package com.xinze.xinze.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * 加密方法
 * @author lxf
 */
public class Base64Util {
    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {

            s = Base64.encodeToString(b, Base64.DEFAULT);
        }
        return s;
    }

    // 解密  
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            try {
                b = Base64.decode(s, Base64.DEFAULT);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
