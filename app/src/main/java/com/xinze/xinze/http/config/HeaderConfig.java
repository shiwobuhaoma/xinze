package com.xinze.xinze.http.config;

import com.xinze.xinze.App;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置请求头
 *
 * @author lxf
 */
public class HeaderConfig {
    public static Map getHeaders() {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        return headers;
    }
}
