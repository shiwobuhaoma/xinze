package com.xinze.xinze.module.main.presenter;


import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HttpConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.fragment.HomeFragment;
import com.xinze.xinze.module.main.modle.Banner;
import com.xinze.xinze.module.main.modle.BannerResponse;
import com.xinze.xinze.module.main.modle.UnreadCountResponse;
import com.xinze.xinze.module.main.view.IHomeView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.ArrayList;

/**
 *@author lxf
 * 首页主持人实现获取轮播图片地址接口
 *
 */
public class HomePresenterImp extends BasePresenterImpl<IHomeView> implements IHomePresenter {
    private HomeFragment mHomeView;


    private ArrayList<String> urlTitles = new ArrayList<>();
    private ArrayList<String> urlImages = new ArrayList<>();
    private ArrayList<Banner> banners = new ArrayList<>();

    public HomePresenterImp(IHomeView iHomeView) {
        this.mHomeView = (HomeFragment)iHomeView;
    }

    @Override
    public void getBanner(String type) {
        RetrofitFactory.getInstence().Api().getBannerListByType(type).compose(this.<BaseEntity<BannerResponse>>setThread())
                .subscribe(new BaseObserver<BannerResponse>() {
                    @Override
                    protected void onSuccees(BaseEntity<BannerResponse> t) {
                        if (t != null) {
                            BannerResponse data = t.getData();
                            if (data != null) {
                                banners = data.getData();
                                for (Banner banner :banners) {
                                    String imgUrl = HttpConfig.IMAGE_BASE_URL + banner.getImgUrl();
                                    String bannerName = banner.getBannerName();
                                    urlImages.add(imgUrl);
                                    urlTitles.add(bannerName);
                                }
                                mHomeView.initBanner(urlImages,urlTitles);
                            }
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError){
                        mHomeView.shotToast(e.getMessage());
                        for (Banner banner :banners) {
                            String imgUrl = HttpConfig.IMAGE_BASE_URL + banner.getImgUrl();
                            String bannerName = banner.getBannerName();
                            urlImages.add(imgUrl);
                            urlTitles.add(bannerName);
                        }
                        mHomeView.initBanner(urlImages,urlTitles);
                    }
                });
    }

    @Override
    public void getUnReadNotifyNum(String id) {
        RetrofitFactory.getInstence().Api().getUnReadNotifyNum(id).compose(this.<BaseEntity<UnreadCountResponse>>setThread()).subscribe(new BaseObserver<UnreadCountResponse>() {
            @Override
            protected void onSuccees(BaseEntity<UnreadCountResponse> t) throws Exception {
                if (t != null){
                    int unReadNum = t.getData().getData();
                    mHomeView.setToolBarUnreadNum(unReadNum);
                }else{
                    mHomeView.shotToast(t.getMsg());
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
                    int unReadNum = t.getData().getData();
                    mHomeView.updateFixBillNum(unReadNum);
                }else{
                    mHomeView.shotToast(t.getMsg());
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

            }
        });
    }

}
