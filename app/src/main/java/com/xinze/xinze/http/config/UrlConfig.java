package com.xinze.xinze.http.config;

/**
 * @author yemao
 * @Date 2017/4/9
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
    String SEARCH_ROUTE_LIST = "transport/app/bill/searchRoute";
    String GET_MY_NOTICE = "transport/app/user/myNotice";
    String POST_MY_NOTICE_READ = "transport/app/user/myNotice/read";
    String GET_BILL_DETAIL="transport/app/bill/getBillDetail";
    String GET_CARRY_ORDER_RIGHT = "transport/app/truck/getCarryOrderRight";
    String GET_CARRY_TRUCK_LIST = "transport/app/truck/getCarryTruckList";
    String GET_PROTOCOL_BY_TYPE = "transport/app/protocol/getProtocolByType";
    String CREATE_BILL_ORDER = "transport/app/billorder/createBillOrder";
    String GET_MY_TRUCKOWNER_INVITATION = "transport/app/user/myInvitation/truckOwner";
    String GET_MY_OWNER_INVITATION = "transport/app/user/myInvitation/owner";
    String POST_RESPONSE_INVITATION = "transport/app/user/responseInvitation";
    String GET_MY_TRUCK_DRIVERS = "transport/app/user/myTruckDrivers";
}
