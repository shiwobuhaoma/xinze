package com.xinze.xinze.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.vondear.rxtools.view.dialog.RxDialogEditSureCancel;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;
import com.xinze.xinze.R;
import com.xinze.xinze.module.certification.view.CertificationActivity2;
import com.xinze.xinze.module.login.view.LoginActivity;

/**
 * @author lxf
 *         未登录、未认证对话框
 */
public class DialogUtil {
    /**
     * 提示未登录对话框
     */
    public static void showUnloginDialog(final Activity mActivity) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        rxDialogSureCancel.getTitleView().setText(R.string.unLogin);
        rxDialogSureCancel.getContentView().setText(R.string.isGoLogin);
        rxDialogSureCancel.getSureView().setText(R.string.goLogin);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }

    /**
     * 提示没有认证对话框
     */
    public static void showUnIdentificationDialog(final Activity mActivity) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
//        rxDialogSureCancel.getTitleView().setText(R.string.unIdentification);
        rxDialogSureCancel.getTitleView().setVisibility(View.GONE);
        rxDialogSureCancel.getContentView().setText(R.string.isGoIdentification);
        rxDialogSureCancel.getSureView().setText(R.string.goIdentification);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, CertificationActivity2.class));
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }

    /**
     * 显示系统消息详情的对话框
     *
     * @param mContext
     * @param content
     */
    public static void showSystemMsgContentDialog(final Context mContext, String content) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mContext);
        rxDialogSureCancel.getContentView().setText(content);
        rxDialogSureCancel.getTitleView().setVisibility(View.GONE);
        rxDialogSureCancel.getSureView().setVisibility(View.GONE);
        rxDialogSureCancel.getCancelView().setText("已读");
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();
    }

    /**
     * 共用对话框
     */
    public static void showCommonDialog(final Activity mActivity, int content, int confirm, final Class clazz) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        rxDialogSureCancel.getTitleView().setVisibility(View.GONE);
        rxDialogSureCancel.getContentView().setText(content);
        rxDialogSureCancel.getSureView().setText(confirm);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, clazz));
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }

    /**
     * 邀请详情拒绝对话框
     *
     * @param mActivity
     * @param listener  确认和取消按钮执行的监听回调
     * @author feibai
     * @time 2018/5/16  16:25
     * @desc
     */
    public static void showInviteDetailRefuseDialog(final Activity mActivity, final ChoiceClickListener listener) {
        final RxDialogEditSureCancel dialog = new RxDialogEditSureCancel(mActivity);
        //final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        dialog.getTitleView().setVisibility(View.GONE);
        //dialog.getContentView().setText(content);
        dialog.getEditText().setHint(R.string.invite_detail_refuse_hint);
        dialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String refuseContent = dialog.getEditText().getText().toString().trim();
                listener.onClickSureView(refuseContent);
                dialog.cancel();
            }
        });
        dialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCancelView(null);
                dialog.cancel();
            }
        });
        dialog.show();

    }

    public static void showCommonDialog(final Activity mActivity, String content, final ChoiceClickListener listener) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        rxDialogSureCancel.getTitleView().setVisibility(View.GONE);
        rxDialogSureCancel.getContentView().setText(content);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSureView(null);
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCancelView(null);
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }
    public static void showCommonDialog(final Activity mActivity, String content, final Intent intent, final int mRequestCode) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        rxDialogSureCancel.getTitleView().setVisibility(View.GONE);
        rxDialogSureCancel.getContentView().setText(content);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivityForResult(intent, mRequestCode);
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }
    public static void showCommonDialog(final Activity mActivity, String content, final Intent intent,String sureText) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        rxDialogSureCancel.getTitleView().setVisibility(View.GONE);
        rxDialogSureCancel.getContentView().setText(content);
        rxDialogSureCancel.getSureView().setText(sureText);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(intent);
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }
    public static void showCommonDialog(final Activity mActivity, String content,String cancelText) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        rxDialogSureCancel.getTitleView().setVisibility(View.GONE);
        rxDialogSureCancel.getContentView().setText(content);
        rxDialogSureCancel.getSureView().setVisibility(View.GONE);

        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }
    /**
    * 按钮点击监听器
    * @author feibai
    * desc:
    */
    public  interface ChoiceClickListener {
        /**
         * 当点击确定需执行的方法
         *
         * @param data 需要传传递的数据
         * @author feibai
         */
        void onClickSureView(Object data);

        /**
         * 当点击取消需执行的方法
         *
         * @param data 需要传传递的数据
         * @author feibai
         */
        void onClickCancelView(Object data);

    }
}
