package com.xinze.xinze.module.send.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.regular.activity.RegularRunActivity;
import com.xinze.xinze.module.send.adapter.SelectPageAdapter;
import com.xinze.xinze.module.send.fragment.DirectionalBillFragment;
import com.xinze.xinze.module.send.fragment.OrdinaryBillFragment;
import com.xinze.xinze.widget.NoScrollViewPager;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author lxf
 * 定向货单
 */
public class SendGoodsActivity extends BaseActivity {
    @BindView(R.id.send_goods_toolbar)
    SimpleToolbar sendGoodsToolbar;

    @BindView(R.id.send_goods_vp)
    NoScrollViewPager sendGoodsVp;

    private RadioButton mRbStart;
    private RadioButton mRbEnd;

    private static final int RADIO_START = 0;
    private static final int RADIO_END = 1;

    private int mCurrentRadio = 0;
    @Override
    protected int initLayout() {
        return R.layout.send_goods_activity;
    }

    @Override
    protected void initView() {
        initToolBar();
        initViewPager();
    }

    private void initToolBar() {
        // 初始化标题栏上的RadioButton
        RadioGroup radioTitle = (RadioGroup) LayoutInflater.from(this).inflate(
                R.layout.title_bar_two_radio, null);

        mRbStart = (RadioButton) radioTitle.findViewById(R.id.title_left);
        mRbEnd = (RadioButton) radioTitle.findViewById(R.id.title_right);
        mRbStart.setText(getString(R.string.bill_ordinary));
        mRbEnd.setText(getString(R.string.bill_directional));
        sendGoodsToolbar.setTitleView(radioTitle);
        sendGoodsToolbar.setLeftTitleVisible();
        sendGoodsToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sendGoodsToolbar.setRightTitleText("搜长跑");
        sendGoodsToolbar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(RegularRunActivity.class);
            }
        });
        radioTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.title_left:
                        changeToolbarChecked(RADIO_START);
                        break;
                    case R.id.title_right:
                        changeToolbarChecked(RADIO_END);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void changeToolbarChecked(int where) {
        sendGoodsVp.setCurrentItem(where);
        mCurrentRadio = where;
        switch (mCurrentRadio) {
            case RADIO_START:
                mRbStart.setChecked(true);
                mRbEnd.setChecked(false);
                break;
            case RADIO_END:
                mRbStart.setChecked(false);
                mRbEnd.setChecked(true);
                break;
            default:
                break;
        }
    }

    private void initViewPager() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new OrdinaryBillFragment());
        list.add(new DirectionalBillFragment());

        SelectPageAdapter adapter = new SelectPageAdapter(getSupportFragmentManager(), list);
        sendGoodsVp.setAdapter(adapter);
        sendGoodsVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                changeToolbarChecked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

}
