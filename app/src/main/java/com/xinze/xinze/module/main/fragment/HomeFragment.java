package com.xinze.xinze.module.main.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.http.config.HttpConfig;
import com.xinze.xinze.module.about.AboutUsActivity;
import com.xinze.xinze.module.find.FindGoodsActivity;
import com.xinze.xinze.module.main.adapter.HomeRecycleViewAdapter;
import com.xinze.xinze.module.main.bean.HomeRecycleViewItem;
import com.xinze.xinze.module.main.presenter.HomePresenterImp;
import com.xinze.xinze.module.main.view.IHomeView;
import com.xinze.xinze.module.regular.activity.RegularRunActivity;
import com.xinze.xinze.module.send.activity.SendGoodsActivity;
import com.xinze.xinze.module.sysmsg.SystemMsgActivity;
import com.xinze.xinze.utils.DialogUtil;
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

    private ArrayList<String> urlTitles = new ArrayList<>();
    private ArrayList<String> urlImages = new ArrayList<>();

    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    @BindView(R.id.main_tool_bar)
    SimpleToolbar mainToolBar;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    private List<HomeRecycleViewItem> homeRecycleViewItems = new ArrayList<>();
    private HomeRecycleViewAdapter hyva;
    private HomePresenterImp hpi;

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
        hyva = new HomeRecycleViewAdapter(mActivity, homeRecycleViewItems);
        mHomeRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mHomeRv.setAdapter(hyva);


        hyva.setOnItemClickListener(new HomeRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jump(position);
            }
        });
    }

    private void jump(int position) {
        if (App.mUser.isLogin()){
            if (position == 1){
                openActivity(SendGoodsActivity.class);
            }else if (position == 0){
                openActivity(FindGoodsActivity.class);
            }else if (position == 2){
                openActivity(RegularRunActivity.class);
            }

        }else{
            DialogUtil.showUnloginDialog(mActivity);
        }
        if (position == 3){
            openActivity(AboutUsActivity.class);
        }else if (position == 4){
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4001245566")));
        }
    }

    private void initTitleBar() {
        mainToolBar.setMainTitle(R.string.home_page);
        mainToolBar.setLeftTitleGone();
        mainToolBar.setRightTitleDrawable(R.mipmap.home_msg);
        mainToolBar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(SystemMsgActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        hpi = new HomePresenterImp(this,mActivity);
        hpi.getBanner("1");
        refreshPage();

    }

    private void refreshPage() {
        if (App.mUser.isLogin()){
            hpi.getFixBillNum(App.mUser.getId());
            hpi.getUnReadNotifyNum(App.mUser.getId());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshPage();
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

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public void success() {

    }

    @Override
    public void failed() {

    }


    public void setToolBarUnreadNum(boolean isShow) {
        if (isShow){
            mainToolBar.setRightTitleDrawable(R.mipmap.home_notice_msg);
        }else{
            mainToolBar.setRightTitleDrawable(R.mipmap.home_msg);
        }

    }

    public void updateFixBillNum(int num) {
        homeRecycleViewItems.get(1).setRightText(String.valueOf(num));
        hyva.notifyDataSetChanged();
    }

    public void setBannerList(List<com.xinze.xinze.module.main.modle.Banner> banners){
        for (com.xinze.xinze.module.main.modle.Banner banner :banners) {
            String imgUrl = HttpConfig.IMAGE_BASE_URL + banner.getImgUrl();
            String bannerName = banner.getBannerName();
            urlImages.add(imgUrl);
            urlTitles.add(bannerName);
        }
        initBanner(urlImages,urlTitles);
    }
}
