package com.xinze.xinze.http;


import com.xinze.xinze.http.config.URLConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.module.login.modle.LoginResponse;
import com.xinze.xinze.module.main.modle.BannerResponse;

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

//    @GET(URLConfig.baidu_url)
//    Observable<BaseEntity<ABean>> getBaidu(@Query("wd") String name);

    @POST(URLConfig.login_token_url)
    Call<String> loginByToken(@Query("mobile") String mobile, @Query("token") String cookie);

    /**
     * 登录接口
     * @param username 用户名
     * @param password 密码
     * @param userType 登陆用户类型
     * @return 登陆信息
     */
    @POST(URLConfig.login_url)
    @FormUrlEncoded
    Observable<BaseEntity<LoginResponse>> login(@Field("loginName") String username, @Field("password") String password,  @Field("userType") String userType);

    /**
     * 注销登录状态
     * @return 注销状态
     */
    @POST(URLConfig.login_out_url)
    @FormUrlEncoded
    Observable<BaseEntity<LoginResponse>> loginOut();



    //http://ip/transport/a/transport/banner/getBannerListByType?bannerType=1  获取banner接口    1：司机   0：货主
    @GET(URLConfig.get_banner)
    Observable<BaseEntity<BannerResponse>> getBanner(@Query("bannerType") String type);


    //上传单张图片
    @POST("服务器地址")
    Observable<Object> imageUpload(@Part() MultipartBody.Part img);

    //上传多张图片
    @POST("服务器地址")
    Observable<Object> imagesUpload(@Part() List<MultipartBody.Part> imgs);
}