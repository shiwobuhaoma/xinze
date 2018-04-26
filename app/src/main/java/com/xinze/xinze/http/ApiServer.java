package com.xinze.xinze.http;


import com.xinze.xinze.http.config.UrlConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.module.login.modle.LoginResponse;
import com.xinze.xinze.module.main.modle.BannerResponse;
import com.xinze.xinze.module.main.modle.UnreadCountResponse;
import com.xinze.xinze.module.register.Modle.RegisterResponse;
import com.xinze.xinze.mvpbase.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
     * @param username 用户名
     * @param password 密码
     * @param userType 登陆用户类型
     * @return 登陆信息
     */
    @POST(UrlConfig.LOGIN_URL)
    @FormUrlEncoded
    Observable<BaseEntity<LoginResponse>> login(@Field("loginName") String username, @Field("password") String password,  @Field("userType") String userType);

    /**
     * 注销登录状态
     * @return 注销状态
     */
    @POST(UrlConfig.LOGIN_OUT_URL)
    @FormUrlEncoded
    Observable<BaseEntity<BaseBean>> loginOut();

    /**
     * 获取验证码接口
     * @param type 验证码类型
     * @param phone 收取验证码的手机号
     * @return 返回状态
     */
    @GET(UrlConfig.GET_VERIFICATION_CODE)
    Observable<BaseEntity<BaseBean>> getVerificationCode(@Query("phone") String phone,@Query("type") String type);

    /**
     * 注册用户
     * @param mobile 注册手机号码
     * @param code 注册验证码
     * @param password 注册密码
     * @param type 验证码类型
     * @param userType 用户类型
     * @return 注册状态
     */
    @POST(UrlConfig.REGISTER_URL)
    @FormUrlEncoded
    Observable<BaseEntity<RegisterResponse>> register(@Field("mobile") String mobile, @Field("code") String code, @Field("password") String password, @Field("type") String type, @Field("userType") String userType);

    /**
     * 获取轮播图接口
     * @param bannerType bannerType=1  获取banner接口    1：司机   0：货主
     * @return 返回状态
     */
    @GET(UrlConfig.GET_BANNER)
    Observable<BaseEntity<BannerResponse>> getBannerListByType(@Query("bannerType") String bannerType);

    /**
     * 获取首页右上角未读消息数量（司机）
     * @param id 用户id
     * @return 返回状态
     */
    @GET(UrlConfig.GET_UNREAD_NOTIFY_NUM)
    Observable<BaseEntity<UnreadCountResponse>> getUnReadNotifyNum(@Query("id") String id);

    /**
     * 获取定向货单未读数量
     * @param id 用户id
     * @return 返回状态
     */
    @GET(UrlConfig.GET_FIX_BILL_NUM)
    Observable<BaseEntity<UnreadCountResponse>>  getFixBillNum(@Query("id") String id);


    /**
     * 上传单张图片
     * @param img 图片
     * @return 上传结果
     */
    @POST("服务器地址")
    Observable<Object> imageUpload(@Part() MultipartBody.Part img);

    /**
     * 上传多张图片
     * @param imgs 多张图片
     * @return 上传结果
     */
    @POST("服务器地址")
    Observable<Object> imagesUpload(@Part() List<MultipartBody.Part> imgs);
}
