package com.xinze.xinze.module.sysmsg.model;

/**
 * @author lxf
 * 系统消息实体
 * Created by LF on 2018/4/24.
 */

public class SystemMessage {
    private String time;
    private String message;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
