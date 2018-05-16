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
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.module.invite.presenter.InvitePresenterImp;
import com.xinze.xinze.utils.DialogUtil;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
 * Created by feibai on 2018/5/15.
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
            TruckownerDriverVO data = (TruckownerDriverVO) intent.getSerializableExtra("data");
            if (data != null) {
                String truckOwnerName = data.getTruckOwnerName();
                final String truckOwnerMobile = data.getTruckOwnerMobile();
                String itemId = data.getId();
                //String createDate = data.getCreateDate();
                String inviteFlag = data.getInviteFlag() == null ? AppConfig.INVITE_FLAG_CONTINUE : data.getInviteFlag();
                String content = data.getContent();

                inviteDetailNameTextView.setText(truckOwnerName);
                inviteDetailContentTextView.setText(content);
                // 初始化邀请消息状态
                initInviteFlag(inviteFlag, itemId);

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
        }
    }

    /**
     * 初始化邀请消息状态
     *
     * @param inviteFlag
     * @author feibai
     * @time 2018/5/16  15:49
     * @desc
     */
    private void initInviteFlag(String inviteFlag, final String itemId) {
        switch (inviteFlag) {
            case AppConfig.INVITE_FLAG_CONTINUE:
                // 待确认
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                inviteDetailStatusTextView.setTextColor(getResources().getColor(R.color.hint_color));
                // 如果是待确认相关按钮布局需要显示
                inviteDetailButtonRelativeLayout.setVisibility(View.VISIBLE);
                // 按钮绑定点击事件
                inviteDetailAcceptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.showCommonDialog(InviteDetailActivity.this, getString(R.string.invite_detail_confirm_content), new DialogUtil.ChoiceClickListener() {
                            @Override
                            public void onClickSureView(Object data) {
                                mPresenter.responseInvitation(itemId,AppConfig.YES,AppConfig.INVITE_RESPONSE_TYPE_TRUCKOWNER,null);
                                //结束页面调回列表需要刷新数据
                                DriverInviteFragment.isRefresh=true;
                                finish();
                            }

                            @Override
                            public void onClickCancelView(Object data) {
                            }
                        });
                    }
                });
                inviteDetailRefuseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.showInviteDetailRefuseDialog(InviteDetailActivity.this, new DialogUtil.ChoiceClickListener() {
                            @Override
                            public void onClickSureView(Object data) {
                                // 获取拒绝理由
                                String content=(String)data;
                                mPresenter.responseInvitation(itemId,AppConfig.NO,AppConfig.INVITE_RESPONSE_TYPE_TRUCKOWNER,content);
                                //结束页面调回列表需要刷新数据
                                DriverInviteFragment.isRefresh=true;
                                finish();
                            }

                            @Override
                            public void onClickCancelView(Object data) {
                            }
                        });

                    }
                });

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
}
