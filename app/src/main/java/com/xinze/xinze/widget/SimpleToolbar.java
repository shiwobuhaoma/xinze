package com.xinze.xinze.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinze.xinze.R;

/**
 * @author lxf
 * 自定义标题栏
 */
public class SimpleToolbar extends LinearLayout {
    private  Context mContext;
    /**
     * 左侧Title
     */
    private TextView mTxtLeftTitle;
    /**
     * 中间Title
     */
    private TextView mTxtMiddleTitle;
    /**
     * 右侧Title
     */
    private TextView mTxtRightTitle;

    public SimpleToolbar(Context context) {
        this(context,null);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        this.mContext = context;
        initView();
    }



    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.title_layout, this);
        mTxtLeftTitle = (TextView) findViewById(R.id.txt_left_title);
        mTxtMiddleTitle = (TextView) findViewById(R.id.txt_main_title);
        mTxtRightTitle = (TextView) findViewById(R.id.txt_right_title);
    }




    /**
     * 设置中间title的内容
     */

    public void setMainTitle(String text) {
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setText(text);
    }
    /**
     * 设置中间title的内容
     */

    public void setMainTitle(int text) {
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setText(text);
    }


    /**
     * 设置中间title的内容文字的颜色
     */
    public void setMainTitleColor(int color) {
        mTxtMiddleTitle.setTextColor(color);
    }

    /**
     * 设置title左边文字
     */
    public void setLeftTitleText(String text) {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
        mTxtLeftTitle.setText(text);
    }

    /**
     * 设置title左边文字颜色
     */
    public void setLeftTitleColor(int color) {
        mTxtLeftTitle.setTextColor(color);
    }

    /**
     * 设置title左边图标
     */
    public void setLeftTitleDrawable(int res) {
        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
        mTxtLeftTitle.setCompoundDrawables(dwLeft, null, null, null);
    }

    /**
     * 设置title左边点击事件
     */
    public void setLeftTitleClickListener(OnClickListener onClickListener) {
        mTxtLeftTitle.setOnClickListener(onClickListener);
    }

    /**
     * 设置title右边文字
     */
    public void setRightTitleText(String text) {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        mTxtRightTitle.setText(text);
    }

    /**
     * 设置title右边文字颜色
     */
    public void setRightTitleColor(int color) {
        mTxtRightTitle.setTextColor(color);
    }

    /**
     * 设置title右边图标
     */
    public void setRightTitleDrawable(int res) {
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
        mTxtRightTitle.setCompoundDrawables(null, null, dwRight, null);
    }

    /**
     * 设置title右边点击事件
     */
    public void setRightTitleClickListener(OnClickListener onClickListener) {
        mTxtRightTitle.setOnClickListener(onClickListener);
    }
}
