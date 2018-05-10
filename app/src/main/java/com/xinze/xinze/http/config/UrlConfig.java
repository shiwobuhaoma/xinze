package com.xinze.xinze.http.config;

/**
 * @author yemao
 * @date 2017/4/9
 *  网络接口地址!
 */

public interface UrlConfig {
    String LOGIN_TOKEN_URL="获取新token的地址";
    String LOGIN_URL = "transport/app/login";
    String LOGIN_OUT_URL = "transport/app/logout";
    String REGISTER_URL = "transport/app/user/register";
    String GET_VERIFICATION_CODE = "transport/app/user/getVerifyCode";
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
}
