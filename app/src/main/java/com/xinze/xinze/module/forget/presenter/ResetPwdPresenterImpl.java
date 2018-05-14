package com.xinze.xinze.module.forget.presenter;

import android.content.Intent;

import com.xinze.xinze.App;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.module.forget.ResetPwdActivity;
import com.xinze.xinze.module.login.LoginActivity;
import com.xinze.xinze.utils.ReturnResult;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by feibai on 2018/5/14.
 * desc:
 */

public class ResetPwdPresenterImpl implements IResetPwdPresenter {

    private ResetPwdActivity mActivity;


    public ResetPwdPresenterImpl(ResetPwdActivity resetPwdActivity) {
        this.mActivity = resetPwdActivity;
    }

    @Override
    public void resetPwd(String phone, String code, String pwd) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("userid",App.mUser.getId());
        RetrofitFactory.getInstence().Api().resetPwd(phone, code,pwd).enqueue(new Callback<ReturnResult>() {
            @Override
            public void onResponse(Call<ReturnResult> call, Response<ReturnResult> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    mActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }
                mActivity.shotToast("重置密码成功");
                // 跳转至登陆页面
                Intent intent = new Intent(mActivity, LoginActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
            }

            @Override
            public void onFailure(Call<ReturnResult> call, Throwable t) {
                // 请求失败
                mActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });
    }
}
