package com.xinze.xinze.module.main.modle;

import java.util.ArrayList;

/**
 * @author lxf
 * 轮播图的响应结果
 */
public class BannerResponse {
    public ArrayList<Banner> getData() {
        return data;
    }

    public void setData(ArrayList<Banner> data) {
        this.data = data;
    }

    private   ArrayList<Banner> data;
}
