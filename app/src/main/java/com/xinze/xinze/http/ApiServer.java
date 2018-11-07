package com.xinze.xinze.http;


import com.xinze.xinze.http.config.UrlConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.module.about.modle.AboutUs;
import com.xinze.xinze.module.add.modle.AddCarRespones;
import com.xinze.xinze.module.certification.modle.CertificationRespones;
import com.xinze.xinze.module.invite.model.OwnerDriverVO;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.module.login.modle.LoginResponse;
import com.xinze.xinze.module.main.modle.AppUpdate;
import com.xinze.xinze.module.main.modle.Banner;
import com.xinze.xinze.module.main.modle.Count;
import com.xinze.xinze.module.main.modle.CustomerPhoneEntity;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.message.model.NotifyEntity;
import com.xinze.xinze.module.order.modle.OrderDetail;
import com.xinze.xinze.module.register.modle.RegisterResponse;
import com.xinze.xinze.module.regular.modle.Route;
import com.xinze.xinze.module.select.module.Protocol;
import com.xinze.xinze.module.transport.module.Car;
import com.xinze.xinze.module.transport.module.TransportDetails;
import com.xinze.xinze.module.trucks.model.MyTruckVO;
import com.xinze.xinze.mvpbase.BaseBean;
import com.xinze.xinze.utils.ReturnResult;
import com.xinze.xinze.widget.bean.Address;

import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.entity.Province;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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
     * 获取售后电话
     * @return 返回售后电话
     */
    @GET(UrlConfig.GET_CUSTOMER_PHONE)
    Observable<BaseEntity<CustomerPhoneEntity>> getCustomerPhone();
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
     * @param remark  关键字搜索使用（可选）
     * @return 返回状态
     */
    @GET(UrlConfig.GET_BILL_ORDER_LIST)
    Observable<BaseEntity<List<OrderItem>>> getBillOrderList(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("remark") String remark);

    /**
     * 获取订单详情信息
     *
     * @param headers 请求头
     * @param orderid 订单id
     * @return 返回订单详情
     */
    @GET(UrlConfig.GET_BILL_ORDER_DETAIL)
    Observable<BaseEntity<OrderDetail>> getBillOrderDetail(@HeaderMap Map<String, String> headers, @Query("orderid") String orderid);

    /**
     * 改变订单状态（撤单、拒绝、通过、发货等）
     *
     * @param headers 请求头
     * @param updateOrderState 状态
     * @return 返回订单状态
     */
    @Headers({"Content-Type: application/json;charset=UTF-8",
            "User-Agent: Retrofit-your-App"})
    @POST(UrlConfig.CHANGE_BILL_ORDER_STATUS)
    Observable<BaseEntity> changeBillOrderStatus(@HeaderMap Map<String, String> headers, @Body RequestBody updateOrderState);


    /**
     * 获取订单列表
     *
     * @param headers    请求头
     * @param wlBilltype 订单类型
     * @param pageNo     第几页
     * @param pageSize   多少条
     * @param remarks   备注
     * @return 返回订单列表
     */
    @GET(UrlConfig.GET_BILL_LIST)
    Observable<BaseEntity<List<OrderItem>>> getBillList(@HeaderMap Map<String, String> headers, @Query("wlBilltype") int wlBilltype, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("remarks") String remarks);


    /**
     * 获取常跑路线
     *
     * @param headers 请求头
     * @return 返回常跑路线集合
     */
    @GET(UrlConfig.GET_REGULAR_ROUTE_LIST)
    Observable<BaseEntity<List<Route>>> getRegularRouteList(@HeaderMap Map<String, String> headers);

    /**
     * 添加常跑路线
     * @param headers 请求头
     * @param fromAreaId 出发地
     * @param toAreaId 目的地
     * @return 返回添加状态
     */
    @GET(UrlConfig.ADD_REGULAR_ROUTE_LIST)
    Observable<BaseEntity>  addRegularRoute(@HeaderMap Map<String, String> headers, @Query("fromAreaId")String fromAreaId, @Query("toAreaId")String toAreaId);

    /**
     * 删除常跑路线
     * @param headers 请求头
     * @param id 路线id
     * @return 返回添加状态
     */
    @GET(UrlConfig.DEL_REGULAR_ROUTE_LIST)
    Observable<BaseEntity>  delRegularRoute(@HeaderMap Map<String, String> headers, @Query("id")String id);


    /**
     * 修改常跑路线
     * @param headers 请求头
     * @param id 路线id
     * @param fromAreaId 出发地
     * @param toAreaId 目的地
     * @return 返回修改状态
     */
    @GET(UrlConfig.EDIT_REGULAR_ROUTE_LIST)
    Observable<BaseEntity>  editRegularRoute(@HeaderMap Map<String, String> headers, @Query("id")String id,@Query("fromAreaId")String fromAreaId, @Query("toAreaId")String toAreaId);


    /**
     * 获取省市县接口
     * @param headers 请求头
     * @param extId 省市县对应的id
     * @return 返回信息
     */
    @GET(UrlConfig.GET_AREA_LIST)
    Observable<BaseEntity<List<Province>>>  getAreaList(@HeaderMap Map<String, String> headers, @Query("extId") String extId);



    /**
     * 搜索路线
     *
     * @param headers    请求头
     * @param fromAreaId 来自哪里
     * @param toAreaId   要到哪里
     * @param pageNo     第几页
     * @param pageSize   多少条数据
     * @return 返回搜索路线集合
     */
    @GET(UrlConfig.SEARCH_ROUTE_LIST)
    Observable<BaseEntity<List<OrderItem>>> searchRoute(@HeaderMap Map<String, String> headers, @Query("fromAreaId") String fromAreaId, @Query("toAreaId") String toAreaId, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);


    /**
     * 获取货运详情信息
     *
     * @param headers 请求头
     * @param id      id号
     * @return 货运详情
     */
    @GET(UrlConfig.GET_BILL_DETAIL)
    Observable<BaseEntity<TransportDetails>> getBillDetail(@HeaderMap Map<String, String> headers, @Query("id") String id);


    /**
     * 退单接口
     * @param headers 请求头
     * @param id  id号
     * @return 退单状态
     */
    @GET(UrlConfig.BACK_BILL)
    Observable<BaseEntity> backBill(@HeaderMap Map<String, String> headers, @Query("id") String id);

    /**
     * 抢单权限判断，抢单操作调用（可否进入选择车辆列表）
     *
     * @param headers 请求头
     * @param id      userId
     * @return 返回是否可以抢单结果
     */
    @GET(UrlConfig.GET_CARRY_ORDER_RIGHT)
    Observable<BaseEntity<Integer>> getCarryOrderRight(@HeaderMap Map<String, String> headers, @Query("id") String id);

    /**
     * 获取抢单后的车辆选择列表信息
     *
     * @param headers 请求头
     * @param id      userId
     * @return 返回车辆列表信息
     */
    @GET(UrlConfig.GET_CARRY_TRUCK_LIST)
    Observable<BaseEntity<List<Car>>> getCarryTruckList(@HeaderMap Map<String, String> headers, @Query("id") String id);

    /**
     * 获取协议类型
     * @param headers 请求头
     * @param protocolType 协议类型
     * @return 返回协议内容
     */
    @GET(UrlConfig.GET_PROTOCOL_BY_TYPE)
    Observable<BaseEntity<Protocol>> getProtocolByType(@HeaderMap Map<String, String> headers, @Query("protocolType") String protocolType);


    /**
     * 获取注册协议类型
     *
     * @param protocolType 协议类型
     * @return 返回协议内容
     */
    @GET(UrlConfig.GET_PROTOCOL_BY_TYPE)
    Observable<BaseEntity<Protocol>> getRegisterProtocolByType( @Query("protocolType") String protocolType);

    /**
     * 获取我的司机列表
     *
     * @param pageNo     第几页
     * @param pageSize   多少条
     * @param headers    请求头
     * @param verifyFlag 验证标记查询条件
     * @return 返回状态
     */
    @GET(UrlConfig.GET_MY_TRUCK_DRIVERS)
    Observable<BaseEntity<List<TruckownerDriverVO>>> getMyTrucksList(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("truck.verifyFlag") String verifyFlag);

    /**
     * 抢单接口
     *
     * @param headers              请求头
     * @param waybillOrderEntities 抢单的车辆集合信息
     * @return 抢单是否成功
     */
    @Headers({"Content-Type: application/json;charset=UTF-8",
            "User-Agent: Retrofit-your-App"})
    @POST(UrlConfig.CREATE_BILL_ORDER)
    Observable<BaseEntity> createBillOrder(@HeaderMap Map<String, String> headers, @Body RequestBody waybillOrderEntities);


    /**
     * 上传单张图片
     * @param headers  请求头
     * @param img 图片
     * @return 上传结果
     */
    @Multipart
    @POST(UrlConfig.UPLOAD_IMAGE)
    Observable<BaseEntity<List<AddCarRespones>>> imageUpload(@HeaderMap Map<String, String> headers, @Part() MultipartBody.Part img);

    /**
     * 上传多张图片
     * @param headers  请求头
     * @param imgs 多张图片
     * @return 上传结果
     */
    @Multipart
    @POST(UrlConfig.UPLOAD_IMAGE)
    Observable<BaseEntity<List<CertificationRespones>>> imagesUpload(@HeaderMap Map<String, String> headers, @Part() List<MultipartBody.Part> imgs);


    /**
     * 司机认证接口
     * @param headers 请求头
     * @param name 司机姓名
     * @param idcard 身份证id
     * @param areaId 所居住地区域id
     * @param detailAdress 详细信息
     * @param idcardImg 身份证图片
     * @param drivingLicenceImg 驾驶证图片
     * @return 返回认证信息
     */
    @POST(UrlConfig.DRIVER_CERTIFICATION)
    @FormUrlEncoded
    Observable<BaseEntity> driverCertification(@HeaderMap Map<String, String> headers, @Field("name") String name, @Field("idcard") String idcard, @Field("areaId") String areaId, @Field("detailAdress") String detailAdress, @Field("idcardImg") String idcardImg, @Field("drivingLicenceImg") String drivingLicenceImg);




    /**
     * 普通货单界面获取区域接口
     * @param extId 抢单的车辆集合信息
     * @return 抢单是否成功
     */
    @GET(UrlConfig.GET_AREALIST_BY_PARENT_ID_FOR_SEARCH)
    Observable<BaseEntity<List<Address>>> getAreaListByParentIdForSearch( @Query("extId") String extId);


    /**
     * 关于我们
     * @param aboutusType 类型
     * @return 返回结果
     */
    @GET(UrlConfig.ABOUT_US)
    Observable<BaseEntity<AboutUs>>   aboutUs(@Query("aboutusType") String aboutusType);


    /**
     * 添加司机
     * @param headers 请求头
     * @param truckName 车牌号
     * @param truckCode 车类型
     * @param weight 车载重
     * @param vehicleLicenseImg 行驶证照片
     * @return 返回添加司机信息
     */
    @POST(UrlConfig.ADD_TRUCK)
    @FormUrlEncoded
    Observable<BaseEntity> addTruck(@HeaderMap Map<String, String> headers, @Field("truckName") String truckName, @Field("truckCode") String truckCode, @Field("weight") String weight, @Field("vehicleLicenseImg") String vehicleLicenseImg);


    /**
     * 获取我的司机列表
     *
     * @param pageNo     第几页
     * @param pageSize   多少条
     * @param headers    请求头
     * @param inviteFlag 状态查询条件
     * @return 返回状态
     */
    @GET(UrlConfig.GET_MY_TRUCK_DRIVERS)
    Observable<BaseEntity<List<TruckownerDriverVO>>> getMyTruckDrivers(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("inviteFlag") String inviteFlag);

    /**
     * 分配司机接口
     * @param headers 请求头
     * @param truckId 车辆id
     * @param driverId 司机id
     * @param rightFlag 司机是否有权限抢单
     * @param id 修改司机id
     * @return 返回信息
     */
    @GET(UrlConfig.APPOINT_DRIVER_TRUCK)
    Observable<BaseEntity> appointDriver4Truck(@HeaderMap Map<String, String> headers, @Query("truckId")String truckId,  @Query("driverId")String driverId,  @Query("rightFlag")String rightFlag,  @Query("id")String id);


    /**
     * 删除司机接口
     * @param headers 请求头
     * @param id  司机id
     * @return 返回信息
     */
    @GET(UrlConfig.DELETE_MY_TRUCKS)
    Observable<BaseEntity> deleteMyTrucks(@HeaderMap Map<String, String> headers, @Query("id")String id);


    /**
     * 版本检查
     * @param apptype 1司机0货主
     * @param filetype  app类型 0 android 1 ios
     * @return 返回信息
     */
    @GET(UrlConfig.CHECK_UPDATE)
    Observable<BaseEntity<AppUpdate>> checkUpdate(@Query("apptype")String apptype, @Query("filetype")String filetype);


    /**
     * 修改登录密码接口
     * @param headers 请求头
     * @param oldpassword 旧密码
     * @param password 新密码
     * @return 修改信息
     */
    @GET(UrlConfig.CHANGE_PASS_WORD)
    Observable<BaseEntity> changePassWord(@HeaderMap Map<String, String> headers,@Query("oldpassword")String oldpassword, @Query("password")String password);


    /**
     * 获取数量
     * @param headers 请求头
     * @param type 可选参数,如果system,则只返回未读系统消息数量
     * @return 返回司机人数，车辆人数，系统消息数量
     */
    @GET(UrlConfig.GET_COUNT)
    Observable<BaseEntity<Count>> getCount(@HeaderMap Map<String, String> headers,@Query("type")String type);



















































































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
     * @param headers 请求头
     * @param id      id
     * @return Observable 返回接收观察者对象
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
     * @param phone    手机
     * @param code     验证码
     * @param password 密码
     * @return ReturnResult
     */
    @POST(UrlConfig.RESET_PWD)
    Call<ReturnResult> resetPwd(@Query("mobile") String phone, @Query("code") String code, @Query("password") String password);

    /**
     * 获取车主邀请信息列表
     *
     * @param pageNo     第几页
     * @param pageSize   多少条
     * @param headers    请求头
     * @param inviteFlag 状态查询条件
     * @return 返回状态
     */
    @GET(UrlConfig.GET_MY_TRUCKOWNER_INVITATION)
    Call<ReturnResult<List<TruckownerDriverVO>>> getTruckOwnerInvitation(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("inviteFlag") String inviteFlag);

    /**
     * 获取货主邀请信息列表
     *
     * @param pageNo     第几页
     * @param pageSize   多少条
     * @param headers    请求头
     * @param inviteFlag 状态查询条件
     * @return 返回状态
     */
    @GET(UrlConfig.GET_MY_OWNER_INVITATION)
    Call<ReturnResult<List<OwnerDriverVO>>> getOwnerInvitation(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("inviteFlag") String inviteFlag);

    /**
     * 响应邀请信息
     *
     * @param id           邀请信息id
     * @param responseType 响应消息类别
     * @param inviteFlag   响应结果
     * @param content      内容
     * @param headers      请求头
     * @return ReturnResult
     */
    @POST(UrlConfig.POST_RESPONSE_INVITATION)
    Call<ReturnResult> responseInvitation(@HeaderMap Map<String, String> headers, @Query("id") String id, @Query("responseType") String responseType, @Query("inviteFlag") String inviteFlag, @Query("content") String content);

    /**
     * 获取我的司机列表
     *
     * @param pageNo     第几页
     * @param pageSize   多少条
     * @param headers    请求头
     * @param inviteFlag 状态查询条件
     * @return 返回状态
     */
    @GET(UrlConfig.GET_MY_TRUCK_DRIVERS)
    Call<ReturnResult<List<TruckownerDriverVO>>> myTruckDrivers(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("inviteFlag") String inviteFlag);

    /**
     * 删除我的司机
     *
     * @param id           邀请信息id
     * @param headers      请求头
     * @return ReturnResult 响应结果接收类
     */
    @POST(UrlConfig.POST_DEL_MY_DRIVER)
    Call<ReturnResult> delMyDriver(@HeaderMap Map<String, String> headers, @Query("id") String id);
    /**
     * 通过手机号邀请司机
     *
     * @param mobile       手机号
     * @param headers      请求头
     * @return ReturnResult 响应结果接收类
     */
    @GET(UrlConfig.GET_INVITE_DRIVER)
    Call<ReturnResult> inviteDriver(@HeaderMap Map<String, String> headers, @Query("mobile") String mobile);
    /**
     * 获取我的车辆列表
     *
     * @param pageNo     第几页
     * @param pageSize   多少条
     * @param headers    请求头
     * @param verifyFlag 验证标记查询条件
     * @return 返回状态
     */
    @GET(UrlConfig.GET_MY_TRUCKS)
    Call<ReturnResult<List<MyTruckVO>>> myTrucks(@HeaderMap Map<String, String> headers, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("truck.verifyFlag") String verifyFlag);


}
