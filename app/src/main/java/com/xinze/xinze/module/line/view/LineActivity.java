package com.xinze.xinze.module.line.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.line.adapter.SelectPageAdapter;
import com.xinze.xinze.module.regular.modle.Route;
import com.xinze.xinze.widget.NoScrollViewPager;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author lxf
 * 我的界面跳转的常跑路线
 */
public class LineActivity extends BaseActivity implements LineListFragment.OnNextPageListener {
    @BindView(R.id.line_tool_bar)
    SimpleToolbar lineToolBar;
    @BindView(R.id.line_vp)
    NoScrollViewPager lineVp;

    private LineEditFragment editFragment;

    private int mCurrentPosition;
    private LineListFragment listFragment;


    @Override
    protected int initLayout() {
        return R.layout.line_activity;
    }

    @Override
    protected void initView() {
        initToolBar();

        ArrayList<Fragment> list = new ArrayList<>();

        listFragment = new LineListFragment();
        listFragment.setOnNextPageListener(this);
        list.add(listFragment);

        editFragment = new LineEditFragment();
        editFragment.setOnNextPageListener(this);
        list.add(editFragment);

        SelectPageAdapter adapter = new SelectPageAdapter(getSupportFragmentManager(), list);
        lineVp.setAdapter(adapter);
        lineVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCurrentPosition = position;
                if (position == 1){
                    editFragment = (LineEditFragment) list.get(position);
                    editFragment.refresh();
                }else{
                    if (editFragment.isRequested()){
                        listFragment = (LineListFragment) list.get(position);
                        listFragment.initData();
                    }
                }


            }
        });

    }

    private void initToolBar() {
        lineToolBar.setMainTitle("常跑路线");
        lineToolBar.setLeftTitleDrawable(R.mipmap.ic_back);
        lineToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentPosition == 0) {
                    finish();
                } else {
                    lineVp.setCurrentItem(0);
                }

            }
        });
    }

    @Override
    public void next(Route route) {
        if (mCurrentPosition == 0){
            Bundle args = new Bundle();
            args.putSerializable("line", route);
            editFragment.setArguments(args);
            lineVp.setCurrentItem(1);
        }else{
            lineVp.setCurrentItem(0);
        }

    }

    @Override
    public void onBackPressed() {
        if (mCurrentPosition == 0) {
            finish();
        } else {
            lineVp.setCurrentItem(0);
        }
    }
}
