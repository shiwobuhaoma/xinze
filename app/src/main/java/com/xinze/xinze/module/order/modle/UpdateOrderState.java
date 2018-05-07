package com.xinze.xinze.module.order.modle;

import java.io.File;
import java.util.List;

public class UpdateOrderState {

    /**
     * id : WL—3AF21D6A82F04EA68E7E
     * orderStatus : 0
     * files : ["asasdasdas","asasdasdas"]
     * remarks : 哈哈哈哈
     */

    private String id;
    private String orderStatus;
    private String remarks;
    private List<File> files;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
