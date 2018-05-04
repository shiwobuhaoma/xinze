package com.xinze.xinze.module.main.modle;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     * 发货单号
     **/
    private String id;

    /**
     * 货主id
     **/
    private String userId;

    /**
     * 从省级编码
     **/
    private String fromAreaId;

    /**
     * 从详细地址
     **/
    private String fromDetailAdress;

    /**
     * 托运人姓名
     **/
    private String fromName;

    /**
     * 托运人手机
     **/
    private String fromPhone;

    /**
     * 到省级编码
     **/
    private String toAreaId;

    /**
     * 到详细地址
     **/
    private String toDetailAdress;

    /**
     * 收货人名字
     **/
    private String toName;

    /**
     * 收货人手机
     **/
    private String toPhone;

    /**
     * 货品
     **/
    private String productName;

    /**
     * 运距
     **/
    private BigDecimal distance;

    /**
     * 运费
     **/
    private BigDecimal price;

    /**
     * 发货日期从
     **/
    private Date dateFrom;

    /**
     * 发货日期到
     **/
    private Date dateTo;

    /**
     * 车数
     **/
    private Integer truckNumber;

    /**
     * 车型
     **/
    private String truckCode;

    /**
     * 车长
     **/
    private BigDecimal truckLong;

    /**
     * 路损
     **/
    private BigDecimal journeyLoss;

    /**
     * 创建者
     **/
    private String createBy;

    /**
     * 创建时间
     **/
    private Date createDate;

    /**
     * 更新者
     **/
    private String updateBy;

    /**
     * 更新时间
     **/
    private Date updateDate;

    /**
     * 备注信息
     **/
    private String remarks;

    /**
     * 删除标记
     **/
    private String delFlag;

    /**
     * 发货单类型 0：普通发货 1：定向发货
     **/
    private Integer wlBilltype;

    /**
     * 目标司机用户id
     **/
    private String driverId;

    /**
     * 信息费
     **/
    private BigDecimal msgPrice;

    /**
     * 装车费
     **/
    private BigDecimal loadPrice;

    /**
     * 卸车费
     **/
    private BigDecimal unloadPrice;

    /**
     * 退单标记,0：未退单 1：退单
     **/
    private String backFlag;

    /**
     * 确认标记,0：不需要确认 1：需要确认
     **/
    private String confirmFlag;

    /**
     * 从区域全称
     **/
    private String fromAreaName;
    /**
     * 到区域全称
     **/
    private String toAreaName;
    /**
     * 剩余车辆数
     **/
    private Integer leftTruckNumber;
    /**
     * 车型名称
     **/
    private String truckName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 货主类型
     */
    private String ownertype;
    /**
     * 货主类型名称
     */
    private String ownername;
    /**
     * 公司名
     */
    private String companyname;
    /**
     * 订单id
     */
    private String orderid;
    /**
     * 订单状态
     */
    private String orderstatus;
    /**
     * 订单状态描述
     */
    private String orderstatus_desc;

    /**
     * 退单描述
     */
    private String backflag_desc;
    /**
     * 是否需要确认描述
     */
    private String confirmflag_desc;
    /**
     * 是否已确认
     */
    private String confrimed;
    /**
     * 是否已确认
     */
    private String confrimed_desc;
    /**
     * 剩余车辆
     */
    long left_number;

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

    public String getFromDetailAdress() {
        return fromDetailAdress;
    }

    public void setFromDetailAdress(String fromDetailAdress) {
        this.fromDetailAdress = fromDetailAdress;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getToAreaId() {
        return toAreaId;
    }

    public void setToAreaId(String toAreaId) {
        this.toAreaId = toAreaId;
    }

    public String getToDetailAdress() {
        return toDetailAdress;
    }

    public void setToDetailAdress(String toDetailAdress) {
        this.toDetailAdress = toDetailAdress;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(Integer truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public BigDecimal getTruckLong() {
        return truckLong;
    }

    public void setTruckLong(BigDecimal truckLong) {
        this.truckLong = truckLong;
    }

    public BigDecimal getJourneyLoss() {
        return journeyLoss;
    }

    public void setJourneyLoss(BigDecimal journeyLoss) {
        this.journeyLoss = journeyLoss;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getWlBilltype() {
        return wlBilltype;
    }

    public void setWlBilltype(Integer wlBilltype) {
        this.wlBilltype = wlBilltype;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public BigDecimal getMsgPrice() {
        return msgPrice;
    }

    public void setMsgPrice(BigDecimal msgPrice) {
        this.msgPrice = msgPrice;
    }

    public BigDecimal getLoadPrice() {
        return loadPrice;
    }

    public void setLoadPrice(BigDecimal loadPrice) {
        this.loadPrice = loadPrice;
    }

    public BigDecimal getUnloadPrice() {
        return unloadPrice;
    }

    public void setUnloadPrice(BigDecimal unloadPrice) {
        this.unloadPrice = unloadPrice;
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    public String getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public String getFromAreaName() {
        return fromAreaName;
    }

    public void setFromAreaName(String fromAreaName) {
        this.fromAreaName = fromAreaName;
    }

    public String getToAreaName() {
        return toAreaName;
    }

    public void setToAreaName(String toAreaName) {
        this.toAreaName = toAreaName;
    }

    public Integer getLeftTruckNumber() {
        return leftTruckNumber;
    }

    public void setLeftTruckNumber(Integer leftTruckNumber) {
        this.leftTruckNumber = leftTruckNumber;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOwnertype() {
        return ownertype;
    }

    public void setOwnertype(String ownertype) {
        this.ownertype = ownertype;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderstatus_desc() {
        return orderstatus_desc;
    }

    public void setOrderstatus_desc(String orderstatus_desc) {
        this.orderstatus_desc = orderstatus_desc;
    }

    public String getBackflag_desc() {
        return backflag_desc;
    }

    public void setBackflag_desc(String backflag_desc) {
        this.backflag_desc = backflag_desc;
    }

    public String getConfirmflag_desc() {
        return confirmflag_desc;
    }

    public void setConfirmflag_desc(String confirmflag_desc) {
        this.confirmflag_desc = confirmflag_desc;
    }

    public String getConfrimed() {
        return confrimed;
    }

    public void setConfrimed(String confrimed) {
        this.confrimed = confrimed;
    }

    public String getConfrimed_desc() {
        return confrimed_desc;
    }

    public void setConfrimed_desc(String confrimed_desc) {
        this.confrimed_desc = confrimed_desc;
    }

    public long getLeft_number() {
        return left_number;
    }

    public void setLeft_number(long left_number) {
        this.left_number = left_number;
    }

}
