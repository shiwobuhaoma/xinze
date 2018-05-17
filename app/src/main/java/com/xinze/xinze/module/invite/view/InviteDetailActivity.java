package com.xinze.xinze.module.invite.view;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.invite.fragment.DriverInviteFragment;
import com.xinze.xinze.module.invite.fragment.OwnerInviteFragment;
import com.xinze.xinze.module.invite.model.OwnerDriverVO;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.module.invite.presenter.InvitePresenterImp;
import com.xinze.xinze.utils.DialogUtil;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
 * @author feibai
 * @date 2018/5/15
 * desc: 邀请信息详情 InviteDetailActivity
 */
public class InviteDetailActivity extends BaseActivity {

    @BindView(R.id.invite_detail_toolbar)
    SimpleToolbar inviteDetailToolbar;
    @BindView(R.id.invite_detail_name_tv)
    TextView inviteDetailNameTextView;
    @BindView(R.id.invite_detail_call_iv)
    ImageView inviteDetailCallImageView;
    @BindView(R.id.invite_detail_status_tv)
    TextView inviteDetailStatusTextView;
    @BindView(R.id.invite_detail_content_tv)
    TextView inviteDetailContentTextView;
    @BindView(R.id.invite_detail_button_rv)
    RelativeLayout inviteDetailButtonRelativeLayout;
    @BindView(R.id.invite_detail_accept_bt)
    Button inviteDetailAcceptButton;
    @BindView(R.id.invite_detail_refuse_bt)
    Button inviteDetailRefuseButton;

    protected InvitePresenterImp mPresenter;


    @Override
    protected int initLayout() {
        return R.layout.invite_detail_activity;
    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter = new InvitePresenterImp(this);
    }

    @Override
    protected void initView() {
        // 初始化标题栏
        inviteDetailToolbar.setTitleMarginTop();
        inviteDetailToolbar.setMainTitle(getString(R.string.invite_detail_title));
        inviteDetailToolbar.setLeftTitleVisible();
        inviteDetailToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 获取传递过来的数据并初始化
        Intent intent = getIntent();
        if (intent != null) {
            // 获取数据类型标记
            String type = intent.getStringExtra("type");

            if (type != null && type.equals(AppConfig.INVITE_RESPONSE_TYPE_TRUCKOWNER)) {
                TruckownerDriverVO truckownerDriver = (TruckownerDriverVO) intent.getSerializableExtra("data");
                if (truckownerDriver != null) {
                    // 初始化车主邀请信息详情
                    initTruckownerDriver(truckownerDriver);
                }
            } else if (type != null && type.equals(AppConfig.INVITE_RESPONSE_TYPE_OWNER)) {
                OwnerDriverVO ownerDriver = (OwnerDriverVO) intent.getSerializableExtra("data");
                if (ownerDriver != null) {
                    // 初始化货主邀请信息详情
                    initOwnerDriver(ownerDriver);
                }

            }
        }
    }

    /**
     * 初始化货主邀请信息详情
     *
     * @param ownerDriver 货主邀请信息
     * @author feibai
     * @time 2018/5/17  10:13
     * @desc
     */
    private void initOwnerDriver(OwnerDriverVO ownerDriver) {
        String ownerName = ownerDriver.getOwnerName();
        final String ownerMobile = ownerDriver.getOwnerMobile();
        String itemId = ownerDriver.getId();
        String inviteFlag = ownerDriver.getInviteFlag() == null ? AppConfig.INVITE_FLAG_CONTINUE : ownerDriver.getInviteFlag();
        String content = ownerDriver.getContent();

        inviteDetailNameTextView.setText(ownerName);
        inviteDetailContentTextView.setText(content);
        // 初始化邀请消息状态
        initInviteFlag(inviteFlag, itemId, AppConfig.INVITE_RESPONSE_TYPE_OWNER);

        // 给电话图片绑定点击事件
        inviteDetailCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + ownerMobile));
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化车主邀请信息详情
     *
     * @param truckownerDriver 车主邀请信息
     * @author feibai
     * @time 2018/5/17  10:12
     * @desc
     */
    private void initTruckownerDriver(TruckownerDriverVO truckownerDriver) {
        String truckOwnerName = truckownerDriver.getTruckOwnerName();
        final String truckOwnerMobile = truckownerDriver.getTruckOwnerMobile();
        String itemId = truckownerDriver.getId();
        String inviteFlag = truckownerDriver.getInviteFlag() == null ? AppConfig.INVITE_FLAG_CONTINUE : truckownerDriver.getInviteFlag();
        String content = truckownerDriver.getContent();

        inviteDetailNameTextView.setText(truckOwnerName);
        inviteDetailContentTextView.setText(content);
        // 初始化邀请消息状态
        initInviteFlag(inviteFlag, itemId, AppConfig.INVITE_RESPONSE_TYPE_TRUCKOWNER);

        // 给电话图片绑定点击事件
        inviteDetailCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + truckOwnerMobile));
                startActivity(intent);
            }
        });
    }


    /**
     * 初始化邀请消息状态
     *
     * @param inviteFlag   响应结果
     * @param itemId       id
     * @param responseType 响应类别
     * @author feibai
     * @time 2018/5/16  15:49
     * @desc
     */

    private void initInviteFlag(String inviteFlag, final String itemId, String responseType) {
        switch (inviteFlag) {
            case AppConfig.INVITE_FLAG_CONTINUE:
                // 待确认
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                inviteDetailStatusTextView.setTextColor(getResources().getColor(R.color.hint_color));
                // 如果是待确认相关按钮布局需要显示
                inviteDetailButtonRelativeLayout.setVisibility(View.VISIBLE);
                // 按钮绑定点击事件
                bind4Button(itemId, responseType);
                break;
            case AppConfig.YES:
                // 已同意
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                inviteDetailStatusTextView.setTextColor(getResources().getColor(R.color.themeBlue));
                // 相关按钮布局需要隐藏
                inviteDetailButtonRelativeLayout.setVisibility(View.GONE);
                break;
            case AppConfig.NO:
                // 已拒绝
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                inviteDetailStatusTextView.setTextColor(getResources().getColor(R.color.themeOrange));
                // 相关按钮布局需要隐藏
                inviteDetailButtonRelativeLayout.setVisibility(View.GONE);
                break;

            default:
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(AppConfig.INVITE_FLAG_CONTINUE);
                break;
        }
        inviteDetailStatusTextView.setText(inviteFlag);
    }

    /**
     * 确认与拒绝按钮绑定点击事件
     *
     * @param itemId       id
     * @param responseType 响应类别
     * @author feibai
     * @time 2018/5/16  21:25
     * @desc
     */
    private void bind4Button(final String itemId, final String responseType) {
        // 确认按钮绑定点击事件
        inviteDetailAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showCommonDialog(InviteDetailActivity.this, getString(R.string.invite_detail_confirm_content), new DialogUtil.ChoiceClickListener() {
                    @Override
                    public void onClickSureView(Object data) {
                        mPresenter.responseInvitation(itemId, AppConfig.YES, responseType, null);
                        //判断responseType,通知对应fragment的OnResume需要刷新更新数据
                        setNextPageRefresh(responseType);
                        finish();
                    }

                    @Override
                    public void onClickCancelView(Object data) {
                    }
                });
            }
        });
        // 拒绝按钮绑定点击事件
        inviteDetailRefuseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showInviteDetailRefuseDialog(InviteDetailActivity.this, new DialogUtil.ChoiceClickListener() {
                    @Override
                    public void onClickSureView(Object data) {
                        // 获取拒绝理由
                        String content = (String) data;
                        mPresenter.responseInvitation(itemId, AppConfig.NO, responseType, content);
                        //判断responseType,通知对应fragment的OnResume需要刷新更新数据
                        setNextPageRefresh(responseType);
                        finish();
                    }

                    @Override
                    public void onClickCancelView(Object data) {
                    }
                });

            }
        });
    }

    /**
     * 判断responseType,通知对应fragment的OnResume需要刷新更新数据
     *
     * @param responseType 响应类别
     * @author feibai
     * @time 2018/5/17  11:03
     * @desc
     */
    private void setNextPageRefresh(String responseType) {
        if (responseType.equals(AppConfig.INVITE_RESPONSE_TYPE_TRUCKOWNER)) {
            DriverInviteFragment.isRefresh = true;
        } else if (responseType.equals(AppConfig.INVITE_RESPONSE_TYPE_OWNER)) {
            OwnerInviteFragment.isRefresh = true;
        }
    }
}
