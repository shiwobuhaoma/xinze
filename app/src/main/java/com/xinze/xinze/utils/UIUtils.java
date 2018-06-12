package com.xinze.xinze.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.xinze.xinze.App;


/**
 *
 * 界面相关的工具类
 *
 * Created by xzhang on 2017/5/15.
 */

public class UIUtils {

    /** dip转换px */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** 获取上下文 */
    public static Context getContext() {
        return App.getContext();
    }

    /** pxz转换dip */

    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    /** 获取字符数组 */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    /** 获取颜色id */
    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    /** 根据id获取尺寸 */
    public static int getDimens(int id) {
        return getResources().getDimensionPixelSize(id);
    }

    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }


    public static String getString(int id) {
        return getResources().getString(id);
    }
    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }

    public static float getDensity(){
      return   getContext().getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth() {

        return getContext().getResources().getDisplayMetrics().widthPixels;
    }
}
