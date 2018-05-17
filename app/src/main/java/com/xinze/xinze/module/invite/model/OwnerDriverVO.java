package com.xinze.xinze.module.invite.model;

/**
 * 
 * @功能 我(货主)的司机VO,关联了一些司机/货主信息
 * @作者 jinlizhi
 * @时间 2018年4月25日下午9:04:17
 */
public class OwnerDriverVO extends OwnerDriver {

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
     * 自有车辆
     */
    private String ownerTruckCount;
    /**
     * 关联车辆
     */
    private String appointTruckCount;
    
    
    
    /**
     * 货主姓名
     */
    private String ownerName;
    /**
     * 货主手机号
     */
    private String ownerMobile;
    /**
     * 货主类型
     */
    private String ownerType;
    /**
     * 货主类型说明
     */
    private String ownerTypeDesc;
    

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public String getOwnerTruckCount() {
        return ownerTruckCount;
    }

    public void setOwnerTruckCount(String ownerTruckCount) {
        this.ownerTruckCount = ownerTruckCount;
    }

    public String getAppointTruckCount() {
        return appointTruckCount;
    }

    public void setAppointTruckCount(String appointTruckCount) {
        this.appointTruckCount = appointTruckCount;
    }

    public String getOwnerTypeDesc() {
        return ownerTypeDesc;
    }

    public void setOwnerTypeDesc(String ownerTypeDesc) {
        this.ownerTypeDesc = ownerTypeDesc;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

}
