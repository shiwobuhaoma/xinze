package com.xinze.xinze.module.order.modle;

import java.math.BigDecimal;

public class OrderDetail {

    /**
     * id : 36ca3aa5455a4f8ba765d674a18cfdbb
     * userId : 4eb0c89c16a04160bcf125eb731aa5a6
     * fromAreaId : 110000
     * fromDetailAdress : 天安门
     * fromName : 毛主席
     * fromPhone : 19999999999
     * toAreaId : 120000
     * toDetailAdress : 大宅门
     * toName : 霍元甲
     * toPhone : 11111111111
     * productName : 银元
     * distance : 300
     * price : 300
     * dateFrom : 2018-05-03 00:00:00
     * dateTo : 2018-06-30 00:00:00
     * truckNumber : 321
     * truckCode : 集装箱
     * journeyLoss : 4.5
     * wlBilltype : 1
     * msgPrice : 123.5
     * loadPrice : 100.5
     * unloadPrice : 100.5
     * backFlag : 0
     * confirmFlag : 0
     * username : ceshi货主002
     * mobile : 13111111111
     * ownertype : 0
     * ownername : 公司
     * companyname : XX公司
     * orderid : WL—0F08353BC42847C1B646
     * orderstatus : 0
     * orderstatus_desc : 已接单
     * backflag_desc : 未退单
     * confirmflag_desc : 无需确认
     * left_number : 315
     */

    private String id;
    private String userId;
    private String fromAreaId;
    private String fromDetailAdress;
    private String fromName;
    private String fromPhone;
    private String toAreaId;
    private String toDetailAdress;
    private String toName;
    private String toPhone;
    private String productName;
    private int distance;
    private BigDecimal price;
    private String dateFrom;
    private String dateTo;
    private int truckNumber;
    private String truckCode;
    private BigDecimal journeyLoss;
    private int wlBilltype;
    private BigDecimal msgPrice;
    private BigDecimal loadPrice;
    private BigDecimal unloadPrice;
    private String backFlag;
    private String confirmFlag;
    private String username;
    private String mobile;
    private String ownertype;
    private String ownername;
    private String companyname;
    private String orderid;
    private String orderstatus;
    private String remarks;
    private String orderstatus_desc;
    private String backflag_desc;
    private String confirmflag_desc;
    private int left_number;
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public int getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(int truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public BigDecimal getJourneyLoss() {
        return journeyLoss;
    }

    public void setJourneyLoss(BigDecimal journeyLoss) {
        this.journeyLoss = journeyLoss;
    }

    public int getWlBilltype() {
        return wlBilltype;
    }

    public void setWlBilltype(int wlBilltype) {
        this.wlBilltype = wlBilltype;
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

    public int getLeft_number() {
        return left_number;
    }

    public void setLeft_number(int left_number) {
        this.left_number = left_number;
    }
}
