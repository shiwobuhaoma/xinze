package com.xinze.xinze.module.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.main.adapter.SelectPageAdapter;
import com.xinze.xinze.module.main.fragment.HomeFragment;
import com.xinze.xinze.module.main.fragment.MyFragment;
import com.xinze.xinze.module.main.fragment.OrderFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author lxf
 * 主界面
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.vp_main)
    ViewPager mVp;
    private HomeFragment home;
    private ArrayList<Fragment> fragments;
    private MyFragment my;
    private OrderFragment order;
    private SelectPageAdapter spa;

    @Override
    protected int initLayout() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView() {
        /**
         * MODE_DEFAULT
         *   如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         * MODE_FIXED
         *   填充模式，未选中的Item会显示文字，没有换挡动画。
         * MODE_SHIFTING
         *   换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

       /**
        *    BACKGROUND_STYLE_DEFAULT
        *       如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
        *    BACKGROUND_STYLE_STATIC
        *        点击的时候没有水波纹效果
        *   BACKGROUND_STYLE_RIPPLE
        *       点击的时候有水波纹效果
        */
        mBottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        //值得一提，模式跟背景的设置都要在添加tab前面，不然不会有效果。
        mBottomNavigationBar
                .setActiveColor(R.color.main_ic_orange);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.main_ic_home_select, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.main_ic_order, "订单"))
                .addItem(new BottomNavigationItem(R.mipmap.main_ic_my, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
        setBottomNavigationItem(mBottomNavigationBar,10,26,10);
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                mVp.setCurrentItem(position);
            }
        });
    }

    private void initViewPager() {

        spa = new SelectPageAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(spa);
        mVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }
        });

        mVp.setCurrentItem(0);
    }

    @Override
    protected void initData() {
        super.initData();
        fragments = getFragments();
        initViewPager();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        mVp.setCurrentItem(0);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        home = HomeFragment.newInstance("Home");
        order = OrderFragment.newInstance("Order");
        my = MyFragment.newInstance("My");
        fragments.add(home);
        fragments.add(order);
        fragments.add(my);
        return fragments;
    }
    /**
     @param bottomNavigationBar，需要修改的 BottomNavigationBar
     @param space 图片与文字之间的间距
     @param imgLen 单位：dp，图片大小，应 <= 36dp
     @param textSize 单位：dp，文字大小，应 <= 20dp

     使用方法：直接调用setBottomNavigationItem(bottomNavigationBar, 6, 26, 10);
     代表将bottomNavigationBar的文字大小设置为10dp，图片大小为26dp，二者间间距为6dp
     **/

    private void setBottomNavigationItem(BottomNavigationBar bottomNavigationBar, int space, int imgLen, int textSize){
        Class barClass = bottomNavigationBar.getClass();
        Field[] fields = barClass.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            if(field.getName().equals("mTabContainer")){
                try{
                    //反射得到 mTabContainer
                    LinearLayout mTabContainer = (LinearLayout) field.get(bottomNavigationBar);
                    for(int j = 0; j < mTabContainer.getChildCount(); j++){
                        //获取到容器内的各个Tab
                        View view = mTabContainer.getChildAt(j);
                        //获取到Tab内的各个显示控件
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(56));
                        FrameLayout container = (FrameLayout) view.findViewById(R.id.fixed_bottom_navigation_container);
                        container.setLayoutParams(params);
                        container.setPadding(dip2px(12), dip2px(0), dip2px(12), dip2px(0));

                        //获取到Tab内的文字控件
                        TextView labelView = (TextView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title);
                        //计算文字的高度DP值并设置，setTextSize为设置文字正方形的对角线长度，所以：文字高度（总内容高度减去间距和图片高度）*根号2即为对角线长度，此处用DP值，设置该值即可。
                        labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                        labelView.setIncludeFontPadding(false);
                        labelView.setPadding(0,0,0,dip2px(20-textSize - space/2));

                        //获取到Tab内的图像控件
                        ImageView iconView = (ImageView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
                        //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                        params = new FrameLayout.LayoutParams(dip2px(imgLen), dip2px(imgLen));
                        params.setMargins(0,0,0,space/2);
                        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                        iconView.setLayoutParams(params);
                    }
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
