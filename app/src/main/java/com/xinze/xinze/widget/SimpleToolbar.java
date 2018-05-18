package com.xinze.xinze.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.utils.UIUtils;

/**
 * @author lxf
 * 自定义标题栏
 */
public class SimpleToolbar extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    /**
     * 左侧Title
     */
    private TextView mTxtLeftTitle;
    /**
     * 中间容器
     */
    private LinearLayout mLlMiddleContainer;
    /**
     * 中间Title
     */
    private TextView mTxtMiddleTitle;
    /**
     * 右侧Title
     */
    private TextView mTxtRightTitle;


    private TitleOnClickListener mLeftListener;
    private TitleOnClickListener mTitleListener;
    private TitleOnClickListener mRightListener;
    private FrameLayout mFlTitle;

    public SimpleToolbar(Context context) {
        this(context, null);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        this.mContext = context;
        initView();
    }


    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.title_layout, this);
        mFlTitle = (FrameLayout) findViewById(R.id.fl_title);
        mTxtLeftTitle = (TextView) findViewById(R.id.txt_left_title);
        mTxtMiddleTitle = (TextView) findViewById(R.id.txt_main_title);
        mTxtRightTitle = (TextView) findViewById(R.id.txt_right_title);
        mLlMiddleContainer = (LinearLayout) findViewById(R.id.ll_main_title);

        mTxtLeftTitle.setOnClickListener(this);
        mTxtMiddleTitle.setOnClickListener(this);
        mTxtRightTitle.setOnClickListener(this);
        mLlMiddleContainer.setOnClickListener(this);
    }

    /**
     * 自定义标题栏中间的控件。调用该方法后，setMainTitle(String)和setMainTitleColor(int)这两个方法就不能用了
     */
    public void setTitleView(View view) {
        mLlMiddleContainer.removeAllViews();
        mLlMiddleContainer.addView(view);
    }

    /**
     * 沉浸式状态栏设置
     */
    public void setTitleMarginTop() {
        mFlTitle.setVisibility(VISIBLE);
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
     * 设置title左边文字可见
     */
    public void setLeftTitleVisible() {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
    }

    /**
     * 设置title左边文字不可见
     */
    public void setLeftTitleGone() {
        mTxtLeftTitle.setVisibility(View.GONE);
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
     * 设置资源文件到title右边文字
     */
    public void setRightTitleText(int text) {
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

    /**
     * 设置标题栏左边按钮的点击事件监听器
     */
    public void setLeftClickListener(TitleOnClickListener mLeftListener) {
        this.mLeftListener = mLeftListener;
    }

    /**
     * 设置标题栏中间标题的点击事件监听器
     */
    public void setTitleClickListener(TitleOnClickListener mTitleListener) {
        this.mTitleListener = mTitleListener;
    }

    /**
     * 设置标题栏右边按钮的点击事件监听器
     */
    public void setRightClickListener(TitleOnClickListener mRightListener) {
        this.mRightListener = mRightListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_left_title:
                if (mLeftListener != null) {
                    mLeftListener.onClick(v);
                }
                break;
            case R.id.txt_main_title:
                if (mTitleListener != null) {
                    mTitleListener.onClick(v);
                }
                break;
            case R.id.txt_right_title:
                if (mRightListener != null) {
                    mRightListener.onClick(v);
                }
                break;
            case R.id.ll_main_title:
                if (mTitleListener != null) {
                    mTitleListener.onClick(v);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 单击事件监听器
     */
    public interface TitleOnClickListener {
        /**
         * 单击回调方法
         *
         * @param view 点击的view
         */
        void onClick(View view);
    }
}
