package com.xinze.xinze.module.invite.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.invite.fragment.DriverInviteFragment;
import com.xinze.xinze.module.invite.fragment.OwnerInviteFragment;
import com.xinze.xinze.module.send.adapter.SelectPageAdapter;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by feibai on 2018/5/14.
 * desc:我的邀请acvitity
 */

public class InviteActivity extends BaseActivity {

    @BindView(R.id.invite_toolbar)
    SimpleToolbar inviteToolbar;
    @BindView(R.id.invite_vp)
    ViewPager inviteViewPager;

    private RadioButton mStartRadioButton;
    private RadioButton mEndRadioButton;

    private static final int RADIO_START = 0;
    private static final int RADIO_END = 1;
    private int mCurrentRadio = 0;

    @Override
    protected int initLayout() {
        return R.layout.invite_activity;
    }

    @Override
    protected void initView() {
        initToolBar();
        initViewPager();
        inviteToolbar.setTitleMarginTop();
    }

    private void initViewPager() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new DriverInviteFragment());
        list.add(new OwnerInviteFragment());

        SelectPageAdapter adapter = new SelectPageAdapter(getSupportFragmentManager(), list);
        inviteViewPager.setAdapter(adapter);
        inviteViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                changeToolbarChecked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initToolBar() {
        // 初始化标题栏上的RadioButton
        RadioGroup radioTitle = (RadioGroup) LayoutInflater.from(this).inflate(
                R.layout.title_bar_two_radio, null);

        mStartRadioButton = (RadioButton) radioTitle.findViewById(R.id.title_left);
        mEndRadioButton = (RadioButton) radioTitle.findViewById(R.id.title_right);
        mStartRadioButton.setText(R.string.invite_driver_invite);
        mEndRadioButton.setText(R.string.invite_owner_invite);
        inviteToolbar.setTitleView(radioTitle);
        inviteToolbar.setLeftTitleVisible();
        inviteToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        inviteViewPager.setCurrentItem(where);
        mCurrentRadio = where;
        switch (mCurrentRadio) {
            case RADIO_START:
                mStartRadioButton.setChecked(true);
                mEndRadioButton.setChecked(false);
                break;
            case RADIO_END:
                mStartRadioButton.setChecked(false);
                mEndRadioButton.setChecked(true);
                break;
            default:
                break;
        }
    }

}
