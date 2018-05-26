package com.xinze.xinze.http.config;

/**
 * @author yemao
 *  2017/4/9
 *  网络接口地址!
 */
public interface UrlConfig {
    String LOGIN_TOKEN_URL="获取新token的地址";
    String LOGIN_URL = "transport/app/login";
    String LOGIN_OUT_URL = "transport/app/logout";
    String REGISTER_URL = "transport/app/user/register";
    String GET_VERIFICATION_CODE = "transport/app/user/getVerifyCode";
    String CHECK_VERIFICATION_CODE = "transport/app/user/checkVerifyCode";
    String RESET_PWD = "transport/app/user/resetPwd";
    String GET_BANNER = "transport/app/banner/getBannerListByType";
    String GET_UNREAD_NOTIFY_NUM = "transport/app/notify/getUnReadNotifyNum";
    String GET_FIX_BILL_NUM = "transport/app/bill/getFixBillNum";
    String GET_BILL_ORDER_LIST = "transport/app/billorder/getBillOrderList";
    String GET_BILL_ORDER_DETAIL = "transport/app/billorder/getBillOrderDetail";
    String CHANGE_BILL_ORDER_STATUS = "transport/app/billorder/changeBillOrderStatus";
    String GET_BILL_LIST = "transport/app/bill/getBillList";
    String GET_REGULAR_ROUTE_LIST = "transport/app/regularroute/getRegularRouteList";
    String ADD_REGULAR_ROUTE_LIST = "transport/app/regularroute/addRegularRoute";
    String DEL_REGULAR_ROUTE_LIST = "transport/app/regularroute/delRegularRoute";
    String EDIT_REGULAR_ROUTE_LIST = "transport/app/regularroute/editRegularRoute";
    String SEARCH_ROUTE_LIST = "transport/app/bill/searchRoute";
    String GET_MY_NOTICE = "transport/app/user/myNotice";
    String POST_MY_NOTICE_READ = "transport/app/user/myNotice/read";
    String BACK_BILL = "transport/app/bill/backBill";
    String GET_BILL_DETAIL="transport/app/bill/getBillDetail";
    String GET_CARRY_ORDER_RIGHT = "transport/app/truck/getCarryOrderRight";
    String GET_CARRY_TRUCK_LIST = "transport/app/truck/getCarryTruckList";
    String GET_PROTOCOL_BY_TYPE = "transport/app/protocol/getProtocolByType";
    String CREATE_BILL_ORDER = "transport/app/billorder/createBillOrder";
    String GET_MY_TRUCKOWNER_INVITATION = "transport/app/user/myInvitation/truckOwner";
    String GET_MY_OWNER_INVITATION = "transport/app/user/myInvitation/owner";
    String POST_RESPONSE_INVITATION = "transport/app/user/responseInvitation";
    String GET_MY_TRUCK_DRIVERS = "transport/app/user/myTruckDrivers";
    String POST_DEL_MY_DRIVER = "transport/app/user/truckownerDriver/del";
    String GET_INVITE_DRIVER = "transport/app/user/invite" ;
    String UPLOAD_IMAGE = "transport/app/upload/file" ;
    String DRIVER_CERTIFICATION = "transport/app/user/driver/auth" ;
    String GET_AREA_LIST = "transport/app/area/getAreaList";

    String GET_MY_TRUCKS = "transport/app/user/myTrucks";
    String GET_AREALIST_BY_PARENT_ID_FOR_SEARCH = "transport/app/area/getAreaListByParentIdForSearch";


}
