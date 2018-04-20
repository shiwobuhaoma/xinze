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
    private ArrayList<Banner> banners = new ArrayList<>();

    public HomePresenterImp(IHomeView iHomeView) {
        this.mHomeView = (HomeFragment)iHomeView;
        banners.add(new Banner("第一张","/transport/userfiles/1/images/transport/banner/2018/04/huaji%20-%20%E5%89%AF%E6%9C%AC.jpg"));
        banners.add(new Banner("第二张","/transport/userfiles/1/images/transport/ownerInfo/2018/04/a.jpg"));
        banners.add(new Banner("第三张","/transport/userfiles/1/images/transport/banner/2018/04/a.jpg"));
    }

    @Override
    public void getBanner(String type) {
        RetrofitFactory.getInstence().Api().getBanner(type).compose(this.<BaseEntity<BannerResponse>>setThread())
                .subscribe(new BaseObserver<BannerResponse>() {
                    @Override
                    protected void onSuccees(BaseEntity<BannerResponse> t) {
                        if (t != null) {
                            BannerResponse data = t.getData();
                            if (data != null) {
                                ArrayList<Banner> banners = data.getData();
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

}
