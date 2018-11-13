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
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.config.HttpConfig;
import com.xinze.xinze.module.about.view.AboutUsActivity;
import com.xinze.xinze.module.main.adapter.HomeRecycleViewAdapter;
import com.xinze.xinze.module.main.bean.HomeRecycleViewItem;
import com.xinze.xinze.module.main.modle.CustomerPhoneEntity;
import com.xinze.xinze.module.main.presenter.HomePresenterImp;
import com.xinze.xinze.module.main.view.IHomeView;
import com.xinze.xinze.module.regular.view.RegularRunActivity;
import com.xinze.xinze.module.send.view.SendGoodsActivity;
import com.xinze.xinze.module.message.view.SystemMsgActivity;
import com.xinze.xinze.module.web.WebViewActivity;
import com.xinze.xinze.utils.DialogUtil;
import com.xinze.xinze.utils.GlideImageLoader;
import com.xinze.xinze.utils.MessageEvent;
import com.xinze.xinze.utils.UrlUtils;
import com.xinze.xinze.widget.SimpleToolbar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private ArrayList<String> linksUrl = new ArrayList<>();

    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    @BindView(R.id.main_tool_bar)
    SimpleToolbar mainToolBar;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    private List<HomeRecycleViewItem> homeRecycleViewItems = new ArrayList<>();
    private HomeRecycleViewAdapter mAdapter;
    private HomePresenterImp mPresenter;
    private HomeRecycleViewItem service_hotline;
    private String hotLine;
    private HomeRecycleViewItem directional;

    @Override
    protected int initLayout() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        initTitleBar();

        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_find_goods, "", R.mipmap.home_find_goods, true, 0, false));
        directional = new HomeRecycleViewItem(R.string.home_directional_shipper, "", R.mipmap.home_deliver_goods, true, 0, true);
        homeRecycleViewItems.add(directional);
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_regular_route, "", R.mipmap.home_regular_route, true, 0, true));
        homeRecycleViewItems.add(new HomeRecycleViewItem(R.string.home_about_us, "", R.mipmap.home_about_us, true, 0, false));

        mAdapter = new HomeRecycleViewAdapter(mActivity);
        mHomeRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mHomeRv.setAdapter(mAdapter);
        mHomeRv.setNestedScrollingEnabled(false);

        mAdapter.setOnItemClickListener(new HomeRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jump(position);
            }
        });
    }

    private void jump(int position) {
        if (position == 0) {

            openActivity(SendGoodsActivity.class, "CurrentRadio", "0");

        } else if (position == 1) {

            if (App.mUser.isLogin()) {
                openActivity(SendGoodsActivity.class, "CurrentRadio", "1");
            } else {
                DialogUtil.showUnloginDialog(mActivity);
            }

        } else if (position == 2) {

            if (App.mUser.isLogin()) {
                openActivity(RegularRunActivity.class);
            } else {
                DialogUtil.showUnloginDialog(mActivity);
            }

        } else if (position == 3) {
            openActivity(AboutUsActivity.class);
        } else if (position == 4) {

            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + hotLine)));
        }
    }

    private void initTitleBar() {
        mainToolBar.setMainTitle(R.string.home_page);
        mainToolBar.setLeftTitleGone();
        mainToolBar.setTitleMarginTop();
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

        mPresenter = new HomePresenterImp(this, mActivity);
        mPresenter.getBanner("1");
        mPresenter.getCustomerPhone();
    }

    private void refreshPage() {
        if (App.mUser != null && App.mUser.isLogin()) {
            if (mPresenter == null){
                mPresenter = new HomePresenterImp(this, mActivity);
            }
            mPresenter.getFixBillNum(App.mUser.getId());
            mPresenter.getUnReadNotifyNum(App.mUser.getId());
        } else {
            directional.setRightText("0");
            homeRecycleViewItems.set(1,directional);
            mAdapter.setData(homeRecycleViewItems);
            mainToolBar.setRightTitleDrawable(R.mipmap.home_msg);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshPage();
    }

    public void initBanner(ArrayList<String> urlImages, ArrayList<String> urlTitles, ArrayList<String> linksUrl) {
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
                        String url = linksUrl.get(position);
                        openActivity(WebViewActivity.class, "URL", url);
                    }
                }).start();
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    public void setToolBarUnreadNum(boolean isShow) {
        if (isShow) {
            mainToolBar.setRightTitleDrawable(R.mipmap.home_notice_msg);
        } else {
            mainToolBar.setRightTitleDrawable(R.mipmap.home_msg);
        }

    }

    public void updateFixBillNum(int num) {
        homeRecycleViewItems.get(1).setRightText(String.valueOf(num));
        mAdapter.notifyDataSetChanged();
    }

    public void setBannerList(List<com.xinze.xinze.module.main.modle.Banner> banners) {
        for (com.xinze.xinze.module.main.modle.Banner banner : banners) {
            String imgUrl = HttpConfig.IMAGE_BASE_URL + banner.getImgUrl();
            String bannerName = banner.getBannerName();
            String linkUrl = banner.getLinkUrl();
            linksUrl.add(linkUrl);
            urlImages.add(imgUrl);
            urlTitles.add(bannerName);
        }
        initBanner(urlImages, urlTitles, linksUrl);
    }

    public void setData(CustomerPhoneEntity data) {
        hotLine = data.getPhone();
        service_hotline = new HomeRecycleViewItem("服务热线：" + hotLine, "", 0, false, false, 0);
        homeRecycleViewItems.add(service_hotline);
        mAdapter.setData(homeRecycleViewItems);
    }

    @Override
    public void getCustomerPhoneSuccess() {

    }

    @Override
    public void getCustomerPhoneFailed() {
        mAdapter.setData(homeRecycleViewItems);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void clear(MessageEvent messageEvent) {
        if (AppConfig.UNLOGIN.equals(messageEvent.getMessage()) || AppConfig.CLEAR_DATA.equals(messageEvent.getMessage())) {
            if (mAdapter != null) {
                refreshPage();
            }
        }

    }
}
