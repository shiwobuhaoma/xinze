package com.xinze.xinze.bean;

/**
 * Created by Administrator on 2017/12/9.
 */

public class MusicBean {
    public MusicBean() {
    }

    public MusicBean(String mp4Url, String showImgUrl, String title) {
        this.mp4Url = mp4Url;
        this.showImgUrl = showImgUrl;
        this.title = title;
    }

    public String mp4Url;
    public String showImgUrl;
    public String title;
}
