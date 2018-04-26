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
}
