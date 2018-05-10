package com.xinze.xinze.module.regular.module;

public class Menu {
    private int id;
    private String from;
    private String to;
    private boolean isShowDrawable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isShowDrawable() {
        return isShowDrawable;
    }

    public void setShowDrawable(boolean showDrawable) {
        isShowDrawable = showDrawable;
    }
}
