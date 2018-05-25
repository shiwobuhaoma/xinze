package com.xinze.xinze.module.trucks.model;

/**
 * 
 * @功能 车辆关联司机信息VO
 * @作者 jinlizhi
 * @时间 2018年5月22日上午11:49:50
 */
public class TruckDriverVO  extends TruckDriverEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 司机手机号
     */
    private String driverPhone;
    /**
     * 司机姓名
     */
    private String driverName;

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

}
