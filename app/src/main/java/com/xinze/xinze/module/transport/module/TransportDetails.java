package com.xinze.xinze.module.transport.module;

import java.math.BigDecimal;

public class TransportDetails {

    /**
     * detail : {"id":"d3a75b8e595d44cc802f828395e7d6ac","userId":"4eb0c89c16a04160bcf125eb731aa5a6","fromAreaId":"150204","fromDetailAdress":"内蒙古自治区包头市青山区阿斯顿阿斯顿","fromName":"阿斯顿","toAreaId":"150602","toDetailAdress":"内蒙古自治区鄂尔多斯市东胜区蒙泰集团","toName":"法国是","productName":"渣渣","distance":22,"price":22,"dateFrom":"2018-04-15 16:00:03","dateTo":"2018-04-28 16:00:06","truckNumber":111,"truckCode":"半挂车","journeyLoss":44,"msgPrice":200,"loadPrice":100,"unloadPrice":7,"backFlag":"0","left_number":109}
     * owner : {"isNewRecord":true,"name":"ceshi货主002","mobile":"13111111111","loginFlag":"1","ownertype":"0","companyname":"XX公司","admin":false,"roleNames":""}
     */

    private DetailBean detail;
    private OwnerBean owner;

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public static class DetailBean {
        /**
         * id : d3a75b8e595d44cc802f828395e7d6ac
         * userId : 4eb0c89c16a04160bcf125eb731aa5a6
         * fromAreaId : 150204
         * fromDetailAdress : 内蒙古自治区包头市青山区阿斯顿阿斯顿
         * fromName : 阿斯顿
         * toAreaId : 150602
         * toDetailAdress : 内蒙古自治区鄂尔多斯市东胜区蒙泰集团
         * toName : 法国是
         * productName : 渣渣
         * distance : 22
         * price : 22
         * dateFrom : 2018-04-15 16:00:03
         * dateTo : 2018-04-28 16:00:06
         * truckNumber : 111
         * truckCode : 半挂车
         * journeyLoss : 44
         * msgPrice : 200
         * loadPrice : 100
         * unloadPrice : 7
         * backFlag : 0
         * left_number : 109
         */

        private String id;
        private String userId;
        private String fromAreaId;
        private String fromDetailAdress;
        private String fromName;
        private String toAreaId;
        private String toDetailAdress;
        private String toName;
        private String productName;
        private BigDecimal distance;
        private BigDecimal price;
        private String dateFrom;
        private String dateTo;
        private int truckNumber;
        private String truckCode;
        private BigDecimal journeyLoss;
        private BigDecimal msgPrice;
        private BigDecimal loadPrice;
        private BigDecimal unloadPrice;
        private String backFlag;
        private int left_number;

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        private String remarks;

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

        public BigDecimal getJourneyLoss() {
            return journeyLoss;
        }

        public void setJourneyLoss(BigDecimal journeyLoss) {
            this.journeyLoss = journeyLoss;
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

        public int getLeft_number() {
            return left_number;
        }

        public void setLeft_number(int left_number) {
            this.left_number = left_number;
        }

        public String getRemarks() {
            return remarks;
        }
    }

    public static class OwnerBean {
        /**
         * isNewRecord : true
         * name : ceshi货主002
         * mobile : 13111111111
         * loginFlag : 1
         * ownertype : 0
         * companyname : XX公司
         * admin : false
         * roleNames :
         */

        private boolean isNewRecord;
        private String name;
        private String mobile;
        private String loginFlag;
        private String ownertype;
        private String companyname;
        private boolean admin;
        private String roleNames;

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
        }

        public String getOwnertype() {
            return ownertype;
        }

        public void setOwnertype(String ownertype) {
            this.ownertype = ownertype;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }
    }
}
