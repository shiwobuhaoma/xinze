package com.xinze.xinze.module.invite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author feibai
 */
public class OwnerDriver implements Serializable {
    /**
    * 编号
    **/
    private String id;
    /**
    * 货主id
    **/
    private String ownerUserId;

    /**
    * 司机id
    **/
    private String driverUserId;

    /**
    * 邀请状态
    **/
    private String inviteFlag;

    /**
    * 内容
    **/
    private String content;

    /**
    * 创建者
    **/
    @JsonIgnore
    private String createBy;

    /**
    * 创建时间
    **/
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

    public OwnerDriver withId(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public OwnerDriver withOwnerUserId(String ownerUserId) {
        this.setOwnerUserId(ownerUserId);
        return this;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId == null ? null : ownerUserId.trim();
    }

    public String getDriverUserId() {
        return driverUserId;
    }

    public OwnerDriver withDriverUserId(String driverUserId) {
        this.setDriverUserId(driverUserId);
        return this;
    }

    public void setDriverUserId(String driverUserId) {
        this.driverUserId = driverUserId == null ? null : driverUserId.trim();
    }

    public String getInviteFlag() {
        return inviteFlag;
    }

    public OwnerDriver withInviteFlag(String inviteFlag) {
        this.setInviteFlag(inviteFlag);
        return this;
    }

    public void setInviteFlag(String inviteFlag) {
        this.inviteFlag = inviteFlag == null ? null : inviteFlag.trim();
    }

    public String getContent() {
        return content;
    }

    public OwnerDriver withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public OwnerDriver withCreateBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public OwnerDriver withCreateDate(String createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public OwnerDriver withUpdateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public OwnerDriver withUpdateDate(String updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public OwnerDriver withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public OwnerDriver withDelFlag(String delFlag) {
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
        sb.append(", ownerUserId=").append(ownerUserId);
        sb.append(", driverUserId=").append(driverUserId);
        sb.append(", inviteFlag=").append(inviteFlag);
        sb.append(", content=").append(content);
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
        OwnerDriver other = (OwnerDriver) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnerUserId() == null ? other.getOwnerUserId() == null : this.getOwnerUserId().equals(other.getOwnerUserId()))
            && (this.getDriverUserId() == null ? other.getDriverUserId() == null : this.getDriverUserId().equals(other.getDriverUserId()))
            && (this.getInviteFlag() == null ? other.getInviteFlag() == null : this.getInviteFlag().equals(other.getInviteFlag()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
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
        result = prime * result + ((getOwnerUserId() == null) ? 0 : getOwnerUserId().hashCode());
        result = prime * result + ((getDriverUserId() == null) ? 0 : getDriverUserId().hashCode());
        result = prime * result + ((getInviteFlag() == null) ? 0 : getInviteFlag().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }
}