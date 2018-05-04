package com.xinze.xinze.module.main.bean;

import java.math.BigDecimal;

/**
 * @author lxf
 * 我的界面条目
 */
public class OrderRecycleViewItem {
    private String id;
    private String start;
    private String end;
    private String state;
    private boolean isShowBottomSpace;
    /**
     * 运费
     */
    private BigDecimal freight;
    /**
     * 车型
     **/
    private String truckCode;
    /**
     * 货品
     **/
    private String productName;

    /**
     * 运距
     **/
    private BigDecimal distance;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isShowBottomSpace() {
        return isShowBottomSpace;
    }

    public void setShowBottomSpace(boolean showBottomSpace) {
        isShowBottomSpace = showBottomSpace;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
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



}
