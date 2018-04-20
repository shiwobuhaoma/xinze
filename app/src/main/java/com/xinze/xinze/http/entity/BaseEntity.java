package com.xinze.xinze.http.entity;

/**
 * @author lxf
 * @date 2018/4/11
 * 解析实体基类!
 */

public class BaseEntity<T> {
    /**
     *  成功的code
     */
    private static int SUCCESS_CODE=200;
    private int status;
    private String msg;
    private T data;


    public boolean isSuccess(){
        return getStatus()==SUCCESS_CODE;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int code) {
        this.status = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }





}
