package com.xinze.xinze.main.presenter;


import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HttpConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.main.fragment.HomeFragment;
import com.xinze.xinze.main.modle.Banner;
import com.xinze.xinze.main.modle.BannerResponse;
import com.xinze.xinze.main.view.IHomeView;
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

    public ArrayList<String> getUrlImages() {
        return urlImages;
    }

    public ArrayList<String> getUrlTitles() {
        return urlTitles;
    }

    public HomePresenterImp(IHomeView iHomeView) {
        this.mHomeView = (HomeFragment)iHomeView;

    }

    @Override
    public void getBanner(String type) {
        RetrofitFactory.getInstence().API().getBanner("1").compose(this.<BaseEntity<BannerResponse>>setThread())
                .subscribe(new BaseObserver<BannerResponse>() {
                    @Override
                    protected void onSuccees(BaseEntity<BannerResponse> t) throws Exception {
                        if (t != null) {
                            BannerResponse data = t.getData();
                            if (data != null) {
                                ArrayList<Banner> banners = data.getData();
                                for (Banner banner :
                                        banners) {
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
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }

}
