package com.xinze.xinze.module.select.module;

import java.util.List;

public class BillOrder {

    /**
     * wayBillid : d3a75b8e595d44cc802f828395e7d6ac
     * waybillOrderEntities : [{"truckOwnerid":"cc9202e676884f15ad8ba640d97be189","driverId":"eaadfc955a604770a8ab30002875b5c5","truckId":"666a6366246842f4b296f0f71a745555"},{"truckOwnerid":"f6f0490b16d74c0cac1359f1a0c59625","driverId":"eaadfc955a604770a8ab30002875b5c5","truckId":"999994163f1a4f9a8d38285291f77777"}]
     */

    private String wayBillid;
    private List<WaybillOrderEntitiesBean> waybillOrderEntities;

    public String getWayBillid() {
        return wayBillid;
    }

    public void setWayBillid(String wayBillid) {
        this.wayBillid = wayBillid;
    }

    public List<WaybillOrderEntitiesBean> getWaybillOrderEntities() {
        return waybillOrderEntities;
    }

    public void setWaybillOrderEntities(List<WaybillOrderEntitiesBean> waybillOrderEntities) {
        this.waybillOrderEntities = waybillOrderEntities;
    }

    public static class WaybillOrderEntitiesBean {
        /**
         * truckOwnerid : cc9202e676884f15ad8ba640d97be189
         * driverId : eaadfc955a604770a8ab30002875b5c5
         * truckId : 666a6366246842f4b296f0f71a745555
         */

        private String truckOwnerid;
        private String driverId;
        private String truckId;

        public String getTruckOwnerid() {
            return truckOwnerid;
        }

        public void setTruckOwnerid(String truckOwnerid) {
            this.truckOwnerid = truckOwnerid;
        }

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getTruckId() {
            return truckId;
        }

        public void setTruckId(String truckId) {
            this.truckId = truckId;
        }
    }
}
