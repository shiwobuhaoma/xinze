package com.xinze.xinze.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.main.adapter.HomeRecycleViewAdapter;
import com.xinze.xinze.main.bean.HomeRecycleViewItem;
import com.xinze.xinze.main.presenter.HomePresenterImp;
import com.xinze.xinze.main.view.IHomeView;
import com.xinze.xinze.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 首页
 *
 * @author lxf
 * Created by lxf on 2016/5/15.
 */
public class HomeFragment extends BaseFragment implements IHomeView {


    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;
    private List<HomeRecycleViewItem> homeRecycleViewItems = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initView() {



        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_general_shipper, R.mipmap.ic_launcher, true, 0));
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_directional_shipper, R.mipmap.ic_launcher, true, 0));
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_about_us, R.mipmap.ic_launcher, true, 0));
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_service_hotline, 0, false, 0));
        HomeRecycleViewAdapter hyva = new HomeRecycleViewAdapter(mActivity, homeRecycleViewItems);
        mHomeRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mHomeRv.setAdapter(hyva);
        hyva.setOnItemClickListener(new HomeRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void initData() {
        super.initData();

        HomePresenterImp hpi = new HomePresenterImp(this);
        ArrayList<String> urlImages = hpi.getUrlImages();
        ArrayList<String> urlTitles = hpi.getUrlTitles();
        initBanner(urlImages, urlTitles);
    }

    public void initBanner(ArrayList<String> urlImages, ArrayList<String> urlTitles) {
        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new GlideImageLoader())
                .setImages(urlImages)
                .setBannerAnimation(Transformer.DepthPage)
                .setBannerTitles(urlTitles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                }).start();
    }

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void success() {

    }

    @Override
    public void failed() {

    }

    @Override
    public void shotToast(String msg) {

    }
}
