package com.xinze.xinze.module.trucks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @feibai
 */
public class TruckEntity  implements Serializable {
    /**
    * 编号
    **/
    private String id;

    /**
    * 车主id
    **/
    private String userId;

    /**
    * 审核标记
    **/
    private String vertifyFlag;

    /**
    * 车牌号
    **/
    private String truckName;

    /**
    * 省区
    **/
    private String truckArea;

    /**
    * 字母
    **/
    private String truckLetter;

    /**
    * 号码
    **/
    private String plateNumber;

    /**
    * 行驶证照片
    **/
    private String vehicleLicenseImg;

    /**
    * 载重
    **/
    private Integer weight;

    /**
    * 车型编码
    **/
    private String truckCode;

    /**
    * 审核原因
    **/
    private String reason;

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

    public TruckEntity withId(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public TruckEntity withUserId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getVertifyFlag() {
        return vertifyFlag;
    }

    public TruckEntity withVertifyFlag(String vertifyFlag) {
        this.setVertifyFlag(vertifyFlag);
        return this;
    }

    public void setVertifyFlag(String vertifyFlag) {
        this.vertifyFlag = vertifyFlag == null ? null : vertifyFlag.trim();
    }

    public String getTruckName() {
        return truckName;
    }

    public TruckEntity withTruckName(String truckName) {
        this.setTruckName(truckName);
        return this;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName == null ? null : truckName.trim();
    }

    public String getTruckArea() {
        return truckArea;
    }

    public TruckEntity withTruckArea(String truckArea) {
        this.setTruckArea(truckArea);
        return this;
    }

    public void setTruckArea(String truckArea) {
        this.truckArea = truckArea == null ? null : truckArea.trim();
    }

    public String getTruckLetter() {
        return truckLetter;
    }

    public TruckEntity withTruckLetter(String truckLetter) {
        this.setTruckLetter(truckLetter);
        return this;
    }

    public void setTruckLetter(String truckLetter) {
        this.truckLetter = truckLetter == null ? null : truckLetter.trim();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public TruckEntity withPlateNumber(String plateNumber) {
        this.setPlateNumber(plateNumber);
        return this;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public String getVehicleLicenseImg() {
        return vehicleLicenseImg;
    }

    public TruckEntity withVehicleLicenseImg(String vehicleLicenseImg) {
        this.setVehicleLicenseImg(vehicleLicenseImg);
        return this;
    }

    public void setVehicleLicenseImg(String vehicleLicenseImg) {
        this.vehicleLicenseImg = vehicleLicenseImg == null ? null : vehicleLicenseImg.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public TruckEntity withWeight(Integer weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public TruckEntity withTruckCode(String truckCode) {
        this.setTruckCode(truckCode);
        return this;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode == null ? null : truckCode.trim();
    }

    public String getReason() {
        return reason;
    }

    public TruckEntity withReason(String reason) {
        this.setReason(reason);
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public TruckEntity withCreateBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public TruckEntity withCreateDate(String createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public TruckEntity withUpdateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public TruckEntity withUpdateDate(String updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public TruckEntity withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public TruckEntity withDelFlag(String delFlag) {
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
        sb.append(", userId=").append(userId);
        sb.append(", vertifyFlag=").append(vertifyFlag);
        sb.append(", truckName=").append(truckName);
        sb.append(", truckArea=").append(truckArea);
        sb.append(", truckLetter=").append(truckLetter);
        sb.append(", plateNumber=").append(plateNumber);
        sb.append(", vehicleLicenseImg=").append(vehicleLicenseImg);
        sb.append(", weight=").append(weight);
        sb.append(", truckCode=").append(truckCode);
        sb.append(", reason=").append(reason);
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
        TruckEntity other = (TruckEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getVertifyFlag() == null ? other.getVertifyFlag() == null : this.getVertifyFlag().equals(other.getVertifyFlag()))
            && (this.getTruckName() == null ? other.getTruckName() == null : this.getTruckName().equals(other.getTruckName()))
            && (this.getTruckArea() == null ? other.getTruckArea() == null : this.getTruckArea().equals(other.getTruckArea()))
            && (this.getTruckLetter() == null ? other.getTruckLetter() == null : this.getTruckLetter().equals(other.getTruckLetter()))
            && (this.getPlateNumber() == null ? other.getPlateNumber() == null : this.getPlateNumber().equals(other.getPlateNumber()))
            && (this.getVehicleLicenseImg() == null ? other.getVehicleLicenseImg() == null : this.getVehicleLicenseImg().equals(other.getVehicleLicenseImg()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getTruckCode() == null ? other.getTruckCode() == null : this.getTruckCode().equals(other.getTruckCode()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
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
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getVertifyFlag() == null) ? 0 : getVertifyFlag().hashCode());
        result = prime * result + ((getTruckName() == null) ? 0 : getTruckName().hashCode());
        result = prime * result + ((getTruckArea() == null) ? 0 : getTruckArea().hashCode());
        result = prime * result + ((getTruckLetter() == null) ? 0 : getTruckLetter().hashCode());
        result = prime * result + ((getPlateNumber() == null) ? 0 : getPlateNumber().hashCode());
        result = prime * result + ((getVehicleLicenseImg() == null) ? 0 : getVehicleLicenseImg().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getTruckCode() == null) ? 0 : getTruckCode().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }
}