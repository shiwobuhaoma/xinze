package com.xinze.xinze.module.main.presenter;


import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.fragment.HomeFragment;
import com.xinze.xinze.module.main.modle.Banner;
import com.xinze.xinze.module.main.modle.CustomerPhoneEntity;
import com.xinze.xinze.module.main.modle.UnreadCountResponse;
import com.xinze.xinze.module.main.view.IHomeView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author lxf
 * 首页主持人实现获取轮播图片地址接口
 *
 */
public class HomePresenterImp extends BasePresenterImpl<IHomeView> implements IHomePresenter {
    private HomeFragment mHomeView;

    public HomePresenterImp(IHomeView iHomeView, Context mContext) {
        super(iHomeView,mContext);
        this.mHomeView = (HomeFragment)iHomeView;
    }

    @Override
    public void getBanner(String type) {
        RetrofitFactory.getInstence().Api().getBannerListByType(type).compose(this.<BaseEntity<List<Banner>>>setThread())
                .subscribe(new BaseObserver<List<Banner>>(mContext) {
                    @Override
                    protected void onSuccees(BaseEntity<List<Banner>> t) {
                        if (t != null) {
                            if (t.isSuccess()){
                                List<Banner> data = t.getData();
                                if (data != null) {
                                    mHomeView.setBannerList(data);
                                }
                            }else{
                                mHomeView.shotToast(t.getMsg());
                            }

                        }
                    }

                    @Override
                    protected void onFailure(String msg){
                        mHomeView.shotToast(msg);
                    }
                });
    }

    @Override
    public void getUnReadNotifyNum(String id) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid",App.mUser.getId());
        RetrofitFactory.getInstence().Api().getUnReadNotifyNum(headers,id)
                .compose(this.<BaseEntity<Integer>>setThread())
                .subscribe(new BaseObserver<Integer>(mContext) {
            @Override
            protected void onSuccees(BaseEntity<Integer> t) throws Exception {
                if (t != null){
                    if(t.isSuccess()){
                        int unReadNum = t.getData();
                        if (0 != unReadNum){
                            mHomeView.setToolBarUnreadNum(true);
                        }else{
                            mHomeView.setToolBarUnreadNum(false);
                        }
                    }else{
                        mHomeView.shotToast(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                System.out.println(msg);
            }
        });
    }

    @Override
    public void getFixBillNum(String id) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid",App.mUser.getId());
        RetrofitFactory.getInstence().Api().getFixBillNum(headers,id)
                .compose(this.<BaseEntity<Integer>>setThread())
                .subscribe(new BaseObserver<Integer>(mContext) {
            @Override
            protected void onSuccees(BaseEntity<Integer> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        int unReadNum = t.getData();
                        mHomeView.updateFixBillNum(unReadNum);
                    }else{
                        mHomeView.shotToast(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                System.out.println(msg);
            }
        });
    }

    @Override
    public void getCustomerPhone() {
        RetrofitFactory.getInstence().Api().getCustomerPhone()
                .compose(this.<BaseEntity<CustomerPhoneEntity>>setThread())
                .subscribe(new BaseObserver<CustomerPhoneEntity>(mContext) {
                    @Override
                    protected void onSuccees(BaseEntity<CustomerPhoneEntity> t) throws Exception {
                        if (t != null){
                            if (t.isSuccess()){
                                CustomerPhoneEntity data = t.getData();
                                mHomeView.setData(data);
                                mHomeView.getCustomerPhoneSuccess();
                            }else{
                                mHomeView.getCustomerPhoneFailed();
                            }
                        }
                    }

                    @Override
                    protected void onFailure(String msg) throws Exception {
                        mHomeView.getCustomerPhoneFailed();
                    }
                });
    }

}
