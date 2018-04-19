package com.xinze.xinze.login.modle;

/**
 * @author lxf
 * 登陆返回实体
 */
public class LoginResponse {

    /**
     * {"id":"1","loginName":"thinkgem","name":"系统管理员","mobileLogin":true,"sessionid":"cacb4f965453407292659c6dfc099844"}
     */
    private String id;
    private String name;
    private String loginName;

    public boolean isMobileLogin() {
        return mobileLogin;
    }

    public void setMobileLogin(boolean mobileLogin) {
        this.mobileLogin = mobileLogin;
    }

    private boolean mobileLogin;
    private String sessionid;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", mobileLogin='" + mobileLogin + '\'' +
                ", sessionid='" + sessionid + '\'' +
                '}';
    }
}
