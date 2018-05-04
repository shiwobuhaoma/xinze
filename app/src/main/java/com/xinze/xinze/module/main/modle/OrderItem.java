package com.xinze.xinze.module.main.modle;

import java.math.BigDecimal;

public class OrderItem {

    /**
     * id : 36ca3aa5455a4f8ba765d674a18cfdbb
     * fromAreaId : 110000
     * fromDetailAdress : 北京市天安门
     * toAreaId : 120000
     * toDetailAdress : 天津市大宅门
     * productName : 银元
     * distance : 300
     * price : 300
     * dateFrom : 2018-05-03 00:00:00
     * dateTo : 2018-06-30 00:00:00
     * truckNumber : 321
     * truckCode : 集装箱
     * orderid : WL—5A8BD481879B4C4C9447
     * orderstatus : 0
     * orderstatus_desc : 已接单
     * left_number : 315
     */

    private String id;
    private String fromAreaId;
    private String fromDetailAdress;
    private String toAreaId;
    private String toDetailAdress;
    private String productName;
    private BigDecimal distance;
    private BigDecimal price;
    private String dateFrom;
    private String dateTo;
    private int truckNumber;
    private String truckCode;
    private String orderid;
    private String orderstatus;
    private String orderstatus_desc;
    private int left_number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getLeft_number() {
        return left_number;
    }

    public void setLeft_number(int left_number) {
        this.left_number = left_number;
    }
}
