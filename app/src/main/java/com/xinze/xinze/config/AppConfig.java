package com.xinze.xinze.config;

import java.util.HashMap;

/**
 * app配置
 */
public class AppConfig {
    /**
     * 货主端
     */
    public static final String CONSIGNOR = "4";
    /**
     * 司机端
     */
    public static final String DRIVER = "5";
    /**
     * 全局常量是
     */
    public static final String YES= "1";
    /**
     * 全局常量否
     */
    public static final String NO= "0";
    /**
     * 系统消息已读
     */
    public static final String SYSTEM_MSG_READ= "已读";
    /**
     * 系统消息已读
     */
    public static final String SYSTEM_MSG_UNREAD= "未读";

    /**
     * 通知类型 系统
     */
    public static final String NOTIFY_TYPE_SYSTEM ="1";
    /**
     * 通知类型 邀请
     */
    public static final String NOTIFY_TYPE_INVITE ="2";
    /**
     * 通知类型 货单
     */
    public static final String NOTIFY_TYPE_WAYBILL ="3";
    /**
     * 通知类型 订单
     */
    public static final String NOTIFY_TYPE_ORDER ="4";

    public static final HashMap<String ,String> NOTIFY_TYPE_MAP = new HashMap<String ,String>();
    static {
        NOTIFY_TYPE_MAP.put(NOTIFY_TYPE_SYSTEM, "系统");
        NOTIFY_TYPE_MAP.put(NOTIFY_TYPE_INVITE, "邀请");
        NOTIFY_TYPE_MAP.put(NOTIFY_TYPE_WAYBILL, "货单");
        NOTIFY_TYPE_MAP.put(NOTIFY_TYPE_ORDER, "订单");
    }

}
