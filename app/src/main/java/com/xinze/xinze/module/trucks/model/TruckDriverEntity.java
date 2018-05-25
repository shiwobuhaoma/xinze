package com.xinze.xinze.module.trucks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @feibai
 */
public class TruckDriverEntity  implements Serializable {
    /**
    * 编号
    **/
    private String id;

    /**
    * 车辆id
    **/
    private String truckId;

    /**
    * 关联司机id
    **/
    private String driverId;

    /**
    * 抢单权限标识
    **/
    private String rightFlag;

    /**
    * 创建者
    **/
    @JsonIgnore
    private String createBy;

    /**
    * 创建时间
    **/
    @JsonIgnore
    private String createDate;

    /**
    * 更新者
    **/
    @JsonIgnore
    private String updateBy;

    /**
    * 更新时间
    **/
    @JsonIgnore
    private String updateDate;

    /**
    * 备注信息
    **/
    @JsonIgnore
    private String remarks;

    /**
    * 删除标记
    **/
    @JsonIgnore
    private String delFlag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public TruckDriverEntity withId(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTruckId() {
        return truckId;
    }

    public TruckDriverEntity withTruckId(String truckId) {
        this.setTruckId(truckId);
        return this;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId == null ? null : truckId.trim();
    }

    public String getDriverId() {
        return driverId;
    }

    public TruckDriverEntity withDriverId(String driverId) {
        this.setDriverId(driverId);
        return this;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId == null ? null : driverId.trim();
    }

    public String getRightFlag() {
        return rightFlag;
    }

    public TruckDriverEntity withRightFlag(String rightFlag) {
        this.setRightFlag(rightFlag);
        return this;
    }

    public void setRightFlag(String rightFlag) {
        this.rightFlag = rightFlag == null ? null : rightFlag.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public TruckDriverEntity withCreateBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public TruckDriverEntity withCreateDate(String createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public TruckDriverEntity withUpdateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public TruckDriverEntity withUpdateDate(String updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public TruckDriverEntity withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public TruckDriverEntity withDelFlag(String delFlag) {
        this.setDelFlag(delFlag);
        return this;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", truckId=").append(truckId);
        sb.append(", driverId=").append(driverId);
        sb.append(", rightFlag=").append(rightFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TruckDriverEntity other = (TruckDriverEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTruckId() == null ? other.getTruckId() == null : this.getTruckId().equals(other.getTruckId()))
            && (this.getDriverId() == null ? other.getDriverId() == null : this.getDriverId().equals(other.getDriverId()))
            && (this.getRightFlag() == null ? other.getRightFlag() == null : this.getRightFlag().equals(other.getRightFlag()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTruckId() == null) ? 0 : getTruckId().hashCode());
        result = prime * result + ((getDriverId() == null) ? 0 : getDriverId().hashCode());
        result = prime * result + ((getRightFlag() == null) ? 0 : getRightFlag().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }
}