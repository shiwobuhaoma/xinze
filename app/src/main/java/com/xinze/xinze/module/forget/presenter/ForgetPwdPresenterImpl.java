package com.xinze.xinze.module.forget.presenter;

import android.content.Intent;

import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.module.forget.ForgetPassWordActivity;
import com.xinze.xinze.module.forget.ResetPwdActivity;
import com.xinze.xinze.module.forget.view.IForgetPwdView;
import com.xinze.xinze.utils.ReturnResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by feibai on 2018/5/13.
 * desc:
 */

public class ForgetPwdPresenterImpl implements IForgetPwdPresenter {

    private IForgetPwdView forgetPwdView;
    private ForgetPassWordActivity forgetPassWordActivity;

    public ForgetPwdPresenterImpl(ForgetPassWordActivity forgetPassWordActivity) {
        this.forgetPwdView = forgetPassWordActivity;
        this.forgetPassWordActivity = (ForgetPassWordActivity) forgetPwdView;
    }

    @Override
    public void getVerfifyCode(String phoneNum, String type) {
        RetrofitFactory.getInstence().Api().getVerifiyCode(phoneNum, type).enqueue(new Callback<ReturnResult>() {
            @Override
            public void onResponse(Call<ReturnResult> call, Response<ReturnResult> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    forgetPassWordActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ReturnResult> call, Throwable t) {
                // 请求失败
                forgetPassWordActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });

    }

    @Override
    public void checkVerfifyCode(final String phone, final String code) {
        RetrofitFactory.getInstence().Api().checkVerifiyCode(phone, code).enqueue(new Callback<ReturnResult>() {
            @Override
            public void onResponse(Call<ReturnResult> call, Response<ReturnResult> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    forgetPassWordActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }
                // 跳转至修改密码页面
                Intent intent = new Intent(forgetPassWordActivity, ResetPwdActivity.class);
                intent.putExtra("phone",phone);
                forgetPassWordActivity.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ReturnResult> call, Throwable t) {
                // 请求失败
                forgetPassWordActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });
    }
}
