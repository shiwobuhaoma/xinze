package com.xinze.xinze.config;

import java.util.HashMap;

/**
 * app配置
 */
public class AppConfig {
    /**
     *  请求成功响应状态码
     */
    public static final Integer REQUEST_STATUS_SUCESS = 200;
    /**
     *  默认页码
     */
    public static final Integer PAGE_NO = 1;
    /**
     *  默认页数
     */
    public static final Integer PAGE_SIZE = 10;
    /**
     *  页面加载完毕通用toast内容
     */
    public static final String LOAD_INFO_FINISH = "没有更多了";
    /**
     *  通用错误响应信息
     */
    public static final String COMMON_FAILURE_RESPONSE = "网络错误";
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
    /**
     *  短信类型_注册
     */
    public static final String VIERFY_CODE_TYPE_REGISTER ="1";
    /**
     *  短信类型_其他
     */
    public static final String VIERFY_CODE_TYPE_OTHER ="0";
    /**
     *  短信验证码再次获取时间_秒
     */
    public static final Integer VIERFY_CODE_DELAY_TIME = 180;
    /**
     *  邀请信息状态_邀请中
     */
    public static final String INVITE_FLAG_CONTINUE ="2";

    public static final HashMap<String ,String> INVITE_FLAG_MAP = new HashMap<String ,String>();
    static {
        INVITE_FLAG_MAP.put(YES, "已同意");
        INVITE_FLAG_MAP.put(NO, "已拒绝");
        INVITE_FLAG_MAP.put(INVITE_FLAG_CONTINUE, "待确认");
    }


    public static final HashMap<String ,String> NOTIFY_TYPE_MAP = new HashMap<String ,String>();
    static {
        NOTIFY_TYPE_MAP.put(NOTIFY_TYPE_SYSTEM, "系统");
        NOTIFY_TYPE_MAP.put(NOTIFY_TYPE_INVITE, "邀请");
        NOTIFY_TYPE_MAP.put(NOTIFY_TYPE_WAYBILL, "货单");
        NOTIFY_TYPE_MAP.put(NOTIFY_TYPE_ORDER, "订单");
    }

}
