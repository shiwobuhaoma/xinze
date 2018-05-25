package com.xinze.xinze.module.trucks.model;

import java.io.Serializable;

/**
 * 
 * @功能 我的/车辆VO
 * @作者 jinlizhi
 * @时间 2018年5月22日上午11:47:42
 */
public class MyTruckVO  implements Serializable {
    
    /**
     * 车辆信息
     */
    private TruckEntity truck;

    /**
     * 关联司机信息
     */
    private TruckDriverVO truckDriver;

    private String truckType;

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public TruckDriverVO getTruckDriver() {
        return truckDriver;
    }

    public void setTruckDriver(TruckDriverVO truckDriver) {
        this.truckDriver = truckDriver;
    }

    public TruckEntity getTruck() {
        return truck;
    }

    public void setTruck(TruckEntity truck) {
        this.truck = truck;
    }
    
    

}
