package com.xinze.xinze.module.regular.modle;

import java.io.Serializable;

/**
 * @author lxf
 * 路线实体
 */
public class Route implements Serializable{

    /**
     * id : eaadfc955a604770a8ab30002875b5c5
     * userId : eaadfc955a604770a8ab30002875b5c5
     * fromAreaId : 110102
     * toAreaId : 110104
     * createBy : 1
     * createDate : 2018-04-25 16:09:41
     * updateBy : 1
     * updateDate : 2018-04-30 01:34:42
     * delFlag : 0
     * from_area_name : 西城区
     * to_area_name : 宣武区
     */

    private String id;
    private String userId;
    private String fromAreaId;
    private String toAreaId;
    private String createBy;
    private String createDate;
    private String updateBy;
    private String updateDate;
    private String delFlag;
    private String from_area_name;
    private String to_area_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromAreaId() {
        return fromAreaId;
    }

    public void setFromAreaId(String fromAreaId) {
        this.fromAreaId = fromAreaId;
    }

    public String getToAreaId() {
        return toAreaId;
    }

    public void setToAreaId(String toAreaId) {
        this.toAreaId = toAreaId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getFrom_area_name() {
        return from_area_name;
    }

    public void setFrom_area_name(String from_area_name) {
        this.from_area_name = from_area_name;
    }

    public String getTo_area_name() {
        return to_area_name;
    }

    public void setTo_area_name(String to_area_name) {
        this.to_area_name = to_area_name;
    }
}
