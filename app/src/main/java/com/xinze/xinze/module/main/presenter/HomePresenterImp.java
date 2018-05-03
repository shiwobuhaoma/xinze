package com.xinze.xinze.module.main.presenter;


import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.fragment.HomeFragment;
import com.xinze.xinze.module.main.modle.Banner;
import com.xinze.xinze.module.main.modle.UnreadCountResponse;
import com.xinze.xinze.module.main.view.IHomeView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.List;

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
                    protected void onFailure(Throwable e, boolean isNetWorkError){
                        mHomeView.shotToast(e.getMessage());
                    }
                });
    }

    @Override
    public void getUnReadNotifyNum(String id) {
        RetrofitFactory.getInstence().Api().getUnReadNotifyNum(id).compose(this.<BaseEntity<UnreadCountResponse>>setThread()).subscribe(new BaseObserver<UnreadCountResponse>() {
            @Override
            protected void onSuccees(BaseEntity<UnreadCountResponse> t) throws Exception {
                if (t != null){
                    if(t.isSuccess()){
                        int unReadNum = t.getData().getData();
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
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

            }
        });
    }

    @Override
    public void getFixBillNum(String id) {
        RetrofitFactory.getInstence().Api().getFixBillNum(id).compose(this.<BaseEntity<UnreadCountResponse>>setThread()).subscribe(new BaseObserver<UnreadCountResponse>() {
            @Override
            protected void onSuccees(BaseEntity<UnreadCountResponse> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        int unReadNum = t.getData().getData();
                        mHomeView.updateFixBillNum(unReadNum);
                    }else{
                        mHomeView.shotToast(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

            }
        });
    }

}
