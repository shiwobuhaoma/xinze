package com.xinze.xinze.bean;

/**
 * Created by Administrator on 2017/12/9.
 */

public class NewAct {
    public NewAct() {

    }
    public NewAct(String url, String titile, String context) {
        this.url = url;
        this.titile = titile;
        this.context = context;
    }

    public String url;
    public String titile;
    public String context;
}
