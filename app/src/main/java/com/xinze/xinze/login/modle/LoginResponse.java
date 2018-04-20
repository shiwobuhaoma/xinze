package com.xinze.xinze.login.modle;

/**
 * @author lxf
 * 登陆返回实体
 */
public class LoginResponse {

    /**
     * isNewRecord : false
     * createDate : 2013-05-27 08:00:00
     * updateDate : 2018-04-18 15:07:54
     * no : 0001
     * userType : 4
     * loginIp : 10.10.10.1
     * loginDate : 2018-04-20 16:31:39
     * loginFlag : 1
     * oldLoginIp : 10.10.10.1
     * oldLoginDate : 2018-04-20 16:31:39
     * admin : true
     * roleNames : 公司管理员,系统管理员
     */
    private String id;
    private String name;
    private String loginName;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String no;
    private String userType;
    private String loginIp;
    private String loginDate;
    private String loginFlag;
    private String oldLoginIp;
    private String oldLoginDate;
    private boolean admin;
    private String roleNames;

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


    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getOldLoginIp() {
        return oldLoginIp;
    }

    public void setOldLoginIp(String oldLoginIp) {
        this.oldLoginIp = oldLoginIp;
    }

    public String getOldLoginDate() {
        return oldLoginDate;
    }

    public void setOldLoginDate(String oldLoginDate) {
        this.oldLoginDate = oldLoginDate;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }
}
