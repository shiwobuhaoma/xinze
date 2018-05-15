package com.xinze.xinze.module.invite.model;

/**
 * 
 * @功能 我(司机)的司机VO,关联了一些司机/车主信息
 * @作者 jinlizhi
 * @时间 2018年4月24日下午8:56:28
 */
public class TruckownerDriverVO extends TruckownerDriver {

    private static final long serialVersionUID = 1L;
    /**
     * 司机姓名
     */
    private String driverName;
    /**
     * 司机头像
     */
    private String driverPhoto;
    /**
     * 司机手机号
     */
    private String driverMobile;
    /**
     * 车主姓名
     */
    private String truckOwnerName;
    /**
     * 车主手机号
     */
    private String truckOwnerMobile;

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public String getTruckOwnerName() {
        return truckOwnerName;
    }

    public void setTruckOwnerName(String truckOwnerName) {
        this.truckOwnerName = truckOwnerName;
    }

    public String getTruckOwnerMobile() {
        return truckOwnerMobile;
    }

    public void setTruckOwnerMobile(String truckOwnerMobile) {
        this.truckOwnerMobile = truckOwnerMobile;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

}
