package com.xinze.xinze.module.main.fragment;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;

import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.main.adapter.HomeRecycleViewAdapter;
import com.xinze.xinze.module.main.bean.HomeRecycleViewItem;
import com.xinze.xinze.module.main.presenter.HomePresenterImp;
import com.xinze.xinze.module.main.view.IHomeView;
import com.xinze.xinze.utils.GlideImageLoader;
import com.xinze.xinze.widget.SimpleToolbar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
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

    @BindView(R.id.main_tool_bar)
    SimpleToolbar mainToolBar;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    private List<HomeRecycleViewItem> homeRecycleViewItems = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initView() {

        initTitleBar();

        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_find_goods, "", R.mipmap.home_find_goods, true, 0, false));
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_directional_shipper, "", R.mipmap.home_deliver_goods, true, 0, true));
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_regular_route, "", R.mipmap.home_regular_route, true, 0, true));
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_about_us, "", R.mipmap.home_about_us, true, 0, false));
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_service_hotline, "", 0, false, 0, false));
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

    private void initTitleBar() {
        mainToolBar.setMainTitle(R.string.home_page);
        mainToolBar.setRightTitleDrawable(R.mipmap.home_msg);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void initData() {
        super.initData();

        HomePresenterImp hpi = new HomePresenterImp(this);
        hpi.getBanner("1");
        hpi.getFixBillNum(App.mUser.getId());
        hpi.getUnReadNotifyNum(App.mUser.getId());
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
        RxToast.showToast(msg);
    }

    public void setToolBarUnreadNum(int num) {

    }

    public void updateFixBillNum(int num) {
    }
}
