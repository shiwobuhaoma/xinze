package com.xinze.xinze.http.config;

/**
 * @author yemao
 * @date 2017/4/9
 *  网络接口地址!
 */

public interface URLConfig {
    String login_token_url="获取新token的地址";
    String login_url = "app/login";
    String login_out_url = "transport/app/logout";
    String get_banner = "a/transport/banner/getBannerListByType";
}