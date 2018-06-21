package com.xinze.xinze.module.trucks.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.bean.CategoryNewBean;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.trucks.model.MyTruckVO;
import com.xinze.xinze.module.trucks.view.IMyTruckView;
import com.xinze.xinze.module.trucks.view.MyTruckActivity;
import com.xinze.xinze.mvpbase.BasePresenterImpl;
import com.xinze.xinze.utils.ReturnResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author feibai on 2018/5/14.
 * desc:
 */

public class MyTruckPresenterImp extends BasePresenterImpl<IMyTruckView> implements IMyTruckPresenter {

    private MyTruckActivity mActivity;

    public MyTruckPresenterImp(IMyTruckView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        this.mActivity = (MyTruckActivity) mPresenterView;
    }


    @Override
    public void myTrucks(final int pageNum, int pageSize, String verifyFlag) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().myTrucks(headers, pageNum, pageSize, verifyFlag)
                .enqueue(new Callback<ReturnResult<List<MyTruckVO>>>() {

            @Override
            public void onResponse(Call<ReturnResult<List<MyTruckVO>>> call, Response<ReturnResult<List<MyTruckVO>>> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (returnResult != null &&  returnResult.getStatus() != AppConfig.REQUEST_STATUS_SUCESS) {
                    mActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }

                if (returnResult != null && returnResult.getData() != null) {
                    List<MyTruckVO> data = (List<MyTruckVO>) returnResult.getData();
                    if (data != null && data.size() == AppConfig.PAGE_SIZE) {
                        mActivity.setPageEndFlag(false);
                        mActivity.setData(data);
                        mActivity.getInitDataSuccess();
                    } else if (data != null && data.size() < AppConfig.PAGE_SIZE) {
                        // 获取到最后一页
                        mActivity.setPageEndFlag(true);
                        mActivity.setData(data);
                        mActivity.getInitDataSuccess();
                    } else {
                        mActivity.setPageEndFlag(true);
                    }
                } else {
                    // 响应数据为空
                    mActivity.setPageEndFlag(true);
                    if (pageNum == AppConfig.PAGE_NO) {
                        // 如果第一页需要显示说明页
                        mActivity.showEmptyPage();

                    }
                    mActivity.getInitDataSuccess();
                }
            }

            @Override
            public void onFailure(Call<ReturnResult<List<MyTruckVO>>> call, Throwable t) {
                // 请求失败
                mActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delMyTruck(String itemId) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().deleteMyTrucks(headers, itemId).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver() {
            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        mActivity.deleteMyTruckSuccess(t.getMsg());
                    } else {
                        mActivity.deleteMyTruckFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mActivity.deleteMyTruckFailed(msg);
            }
        });

    }

}
