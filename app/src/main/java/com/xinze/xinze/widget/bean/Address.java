package com.xinze.xinze.widget.bean;

/**
 * @author lxf
 * 省市区地址
 */
public class Address {

    /**
     * id : 370000
     * name : 山东省
     * pid :
     */

    private String id;
    private String name;
    private String pid;
    private int backgroundColor;
    private int textColor;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }


    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
