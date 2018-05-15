package com.xinze.xinze.module.transport.module;

public class Car {

    /**
     * id : 0f978a07bd8847e3ac4ca706b884975e
     * truck_name : 蒙K888
     * vertify_flag : 1
     * vertify_desc : 已审核
     * reason : 已关联司机
     * driver_id : 743617f5064a4486ad7767ed3811f045
     * ownFlag : 0
     * ownDesc : (自有)
     * truck_ownerid : eaadfc955a604770a8ab30002875b5c5
     */

    private String id;
    private String truck_name;
    private String vertify_flag;
    private String vertify_desc;
    private String reason;
    private String driver_id;
    private String ownFlag;
    private String ownDesc;
    private String truck_ownerid;

    public String getRight_flag() {
        return right_flag;
    }

    public void setRight_flag(String right_flag) {
        this.right_flag = right_flag;
    }

    private String right_flag;

    public boolean isSelected() {
        return isSelected;
    }

    private boolean isSelected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTruck_name() {
        return truck_name;
    }

    public void setTruck_name(String truck_name) {
        this.truck_name = truck_name;
    }

    public String getVertify_flag() {
        return vertify_flag;
    }

    public void setVertify_flag(String vertify_flag) {
        this.vertify_flag = vertify_flag;
    }

    public String getVertify_desc() {
        return vertify_desc;
    }

    public void setVertify_desc(String vertify_desc) {
        this.vertify_desc = vertify_desc;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getOwnFlag() {
        return ownFlag;
    }

    public void setOwnFlag(String ownFlag) {
        this.ownFlag = ownFlag;
    }

    public String getOwnDesc() {
        return ownDesc;
    }

    public void setOwnDesc(String ownDesc) {
        this.ownDesc = ownDesc;
    }

    public String getTruck_ownerid() {
        return truck_ownerid;
    }

    public void setTruck_ownerid(String truck_ownerid) {
        this.truck_ownerid = truck_ownerid;
    }

    public void setSelected(boolean select) {
        this.isSelected = select;
    }
}
