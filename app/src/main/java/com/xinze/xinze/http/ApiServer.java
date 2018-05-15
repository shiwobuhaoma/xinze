package com.xinze.xinze.http;


import com.xinze.xinze.http.config.UrlConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.module.login.modle.LoginResponse;
import com.xinze.xinze.module.main.modle.Banner;
import com.xinze.xinze.module.select.module.Protocol;
import com.xinze.xinze.module.sysmsg.model.NotifyEntity;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.order.modle.OrderDetail;
import com.xinze.xinze.module.register.Modle.RegisterResponse;
import com.xinze.xinze.module.regular.modle.Route;
import com.xinze.xinze.module.transport.module.Car;
import com.xinze.xinze.module.transport.module.TransportDetails;
import com.xinze.xinze.mvpbase.BaseBean;
import com.xinze.xinze.utils.ReturnResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author lxf
 * @date 2018/4/11
 * API接口!
 */

public interface ApiServer {

//    @GET(UrlConfig.baidu_url)
//    Observable<BaseEntity<ABean>> getBaidu(@Query("wd") String name);

    @POST(UrlConfig.LOGIN_TOKEN_URL)
    Call<String> loginByToken(@Query("mobile") String mobile, @Query("token") String cookie);

    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @param userType 登陆用户类型
     * @return 登陆信息
     */
    @POST(UrlConfig.LOGIN_URL)
    @FormUrlEncoded
    Observable<BaseEntity<LoginResponse>> login(@Field("loginName") String username, @Field("password") String password, @Field("userType") String userType);

    /**
     * 注销登录状态
     *
     * @param headers 请求头
     * @return 注销状态
     */
    @POST(UrlConfig.LOGIN_OUT_URL)
    Observable<BaseEntity> loginOut(@HeaderMap Map<String, String> headers);

    /**
     * 获取验证码接口
     *
     * @param type  验证码类型
     * @param phone 收取验证码的手机号
     * @return 返回状态
     */
    @GET(UrlConfig.GET_VERIFICATION_CODE)
    Observable<BaseEntity<BaseBean>> getVerificationCode(@Query("phone") String phone, @Query("type") String type);

    /**
     * 注册用户
     *
     * @param mobile   注册手机号码
     * @param code     注册验证码
     * @param password 注册密码
     * @param type     验证码类型
     * @param userType 用户类型
     * @return 注册状态
     */
    @POST(UrlConfig.REGISTER_URL)
    @FormUrlEncoded
    Observable<BaseEntity<RegisterResponse>> register(@Field("mobile") String mobile, @Field("code") String code, @Field("password") String password, @Field("type") String type, @Field("userType") String userType);

    /**
     * 获取轮播图接口
     *
     * @param bannerType bannerType=1  获取banner接口    1：司机   0：货主
     * @return 返回状态
     */
    @GET(UrlConfig.GET_BANNER)
    Observable<BaseEntity<List<Banner>>> getBannerListByType(@Query("bannerType") String bannerType);

    /**
     * 获取首页右上角未读消息数量（司机）
     *
     * @param id      用户id
     * @param headers 请求头
     * @return 返回状态
     */
    @GET(UrlConfig.GET_UNREAD_NOTIFY_NUM)
    Observable<BaseEntity<Integer>> getUnReadNotifyNum(@HeaderMap Map<String, String> headers, @Query("id") String id);

    /**
     * 获取定向货单未读数量
     *
     * @param id      用户id
     * @param headers 请求头
     * @return 返回状态
     */
    @GET(UrlConfig.GET_FIX_BILL_NUM)
    Observable<BaseEntity<Integer>> getFixBillNum(@HeaderMap Map<String, String> headers, @Query("id") String id);

    /**
     * 获取订单列表
     *
     * @param pageNo   第几页
     * @param pageSize 多少条
     * @param headers  请求头
     * @return 返回状态
     */
    @GET(UrlConfig.GET_BILL_ORDER_LIST)
    Observable<BaseEntity<List<OrderItem>>> getBillOrderList(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    /**
     * 获取订单详情信息
     * @param headers 请求头
     * @param orderid 订单id
     * @return 返回订单详情
     */
    @GET(UrlConfig.GET_BILL_ORDER_DETAIL)
    Observable<BaseEntity<OrderDetail>>  getBillOrderDetail(@HeaderMap Map<String, String> headers, @Query("orderid") String orderid);

    /**
     * 改变订单状态（撤单、拒绝、通过、发货等）
     * @param headers 请求头
     * @return 返回订单状态
     */
    @Headers({"Content-Type: application/json;charset=UTF-8",
            "User-Agent: Retrofit-your-App"})
    @POST(UrlConfig.CHANGE_BILL_ORDER_STATUS)
    Observable<BaseEntity>  changeBillOrderStatus(@HeaderMap Map<String, String> headers,@Body RequestBody updateOrderState);



    /**
     * 获取订单列表
     * @param headers 请求头
     * @param wlBilltype 订单类型
     * @param pageNo 第几页
     * @param pageSize 多少条
     * @return 返回订单列表
     */
    @GET(UrlConfig.GET_BILL_LIST)
    Observable<BaseEntity<List<OrderItem>>>  getBillList(@HeaderMap Map<String, String> headers,@Query("wlBilltype") int wlBilltype,@Query("pageNo") int pageNo, @Query("pageSize") int pageSize,@Query("remarks") String remarks);

    /**
     * 获取常跑路线
     * @param headers 请求头
     * @return 返回常跑路线集合
     */
    @GET(UrlConfig.GET_REGULAR_ROUTE_LIST)
    Observable<BaseEntity<List<Route>>> getRegularRouteList(@HeaderMap Map<String, String> headers);

    /**
     * 搜索路线
     * @param headers 请求头
     * @param fromAreaId 来自哪里
     * @param toAreaId 要到哪里
     * @param pageNo 第几页
     * @param pageSize 多少条数据
     * @return 返回搜索路线集合
     */
    @GET(UrlConfig.SEARCH_ROUTE_LIST)
    Observable<BaseEntity<List<OrderItem>>> searchRoute(@HeaderMap Map<String, String> headers, @Query("fromAreaId") String fromAreaId, @Query("toAreaId") String toAreaId,  @Query("pageNo")int pageNo,  @Query("pageSize")int pageSize);


    /**
     * 获取货运详情信息
     * @param headers 请求头
     * @param id id号
     * @return 货运详情
     */
    @GET(UrlConfig.GET_BILL_DETAIL)
    Observable<BaseEntity<TransportDetails>> getBillDetail(@HeaderMap Map<String, String> headers, @Query("id")String id);


    /**
     * 抢单权限判断，抢单操作调用（可否进入选择车辆列表）
     * @param headers 请求头
     * @param id userId
     * @return 返回是否可以抢单结果
     */
    @GET(UrlConfig.GET_CARRY_ORDER_RIGHT)
    Observable<BaseEntity<Integer>> getCarryOrderRight(@HeaderMap Map<String, String> headers,  @Query("id")String id);

    /**
     * 获取抢单后的车辆选择列表信息
     * @param headers 请求头
     * @param id userId
     * @return 返回车辆列表信息
     */
    @GET(UrlConfig.GET_CARRY_TRUCK_LIST)
    Observable<BaseEntity<List<Car>>> getCarryTruckList(@HeaderMap Map<String, String> headers,  @Query("id")String id);



    @GET(UrlConfig.GET_PROTOCOL_BY_TYPE)
    Observable<BaseEntity<Protocol>>  getProtocolByType(@HeaderMap Map<String, String> headers, @Query("protocolType")String protocolType);


    /**
     * 抢单接口
     * @param headers 请求头
     * @param waybillOrderEntities 抢单的车辆集合信息
     * @return 抢单是否成功
     */
    @Headers({"Content-Type: application/json;charset=UTF-8",
            "User-Agent: Retrofit-your-App"})
    @POST(UrlConfig.CREATE_BILL_ORDER)
    Observable<BaseEntity> createBillOrder(@HeaderMap Map<String, String> headers, @Body RequestBody waybillOrderEntities);





    /**
     * 上传单张图片
     *
     * @param img 图片
     * @return 上传结果
     */
    @POST("服务器地址")
    Observable<Object> imageUpload(@Part() MultipartBody.Part img);

    /**
     * 上传多张图片
     *
     * @param imgs 多张图片
     * @return 上传结果
     */
    @POST("服务器地址")
    Observable<Object> imagesUpload(@Part() List<MultipartBody.Part> imgs);

























////  -----------------feibai
    /**
     * 获取系统消息列表
     *
     * @param pageNo   第几页
     * @param pageSize 多少条
     * @param headers  请求头
     * @return 返回状态
     */
    @GET(UrlConfig.GET_MY_NOTICE)
    Observable<BaseEntity<List<NotifyEntity>>> getSystemMsgList(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    /**
     * 标记系统消息已读
     *
     * @param headers
     * @param id
     * @return
     */
    @POST(UrlConfig.POST_MY_NOTICE_READ)
    Observable<BaseEntity> markNoticeReaded(@HeaderMap Map<String, String> headers, @Query("id") String id);

    /**
     * 获取验证码接口
     *
     * @param type  验证码类型
     * @param phone 收取验证码的手机号
     * @return ReturnResult
     */
    @GET(UrlConfig.GET_VERIFICATION_CODE)
    Call<ReturnResult> getVerifiyCode(@Query("phone") String phone, @Query("type") String type);

    /**
     * 校验验证码
     *
     * @param code  验证码
     * @param phone 收取验证码的手机号
     * @return ReturnResult
     */
    @GET(UrlConfig.CHECK_VERIFICATION_CODE)
    Call<ReturnResult> checkVerifiyCode(@Query("phone") String phone, @Query("code") String code);

    /**
     * 重置密码
     *
     * @param phone 手机
     * @param code 验证码
     * @param password 密码
     * @return ReturnResult
     */
    @POST(UrlConfig.RESET_PWD)
    Call<ReturnResult> resetPwd(@Query("mobile") String phone, @Query("code") String code, @Query("password") String password);

    /**
     * 获取车主邀请信息列表
     *
     * @param pageNo   第几页
     * @param pageSize 多少条
     * @param headers  请求头
     * @param inviteFlag  状态查询条件
     * @return 返回状态
     */
    @GET(UrlConfig.GET_MY_TRUCKOWNER_INVITATION)
    Call<ReturnResult<List<TruckownerDriverVO>>> getTruckOwnerInvitation(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("inviteFlag")String inviteFlag);



}
