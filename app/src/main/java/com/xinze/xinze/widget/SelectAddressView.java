package com.xinze.xinze.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xinze.xinze.R;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.widget.bean.Address;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 普通货单的地址选择控件
 */
public class SelectAddressView extends LinearLayout {
    private  Unbinder mUnbinder;
    @BindView(R.id.province)
    RecyclerView province;
    @BindView(R.id.city)
    RecyclerView city;
    @BindView(R.id.country)
    RecyclerView country;

    private String extId = "0";
    public SelectAddressView(Context context) {
        this(context, null);
    }

    public SelectAddressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectAddressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.select_address_view, this, false);

        mUnbinder = ButterKnife.bind(view);

        initData();
    }

    private void initData() {
        HashMap<String,String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getAreaListByParentIdForSearch(headers,extId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<Address>>(){
                    @Override
                    protected void onSuccees(BaseEntity<List<Address>> t) throws Exception {
                        if (t != null){
                            if (t.isSuccess()){
                                List<Address> data = t.getData();

                            }
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }
}
