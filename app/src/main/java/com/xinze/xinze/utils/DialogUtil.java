package com.xinze.xinze.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.vondear.rxtools.view.dialog.RxDialogSureCancel;
import com.xinze.xinze.R;
import com.xinze.xinze.module.certification.CertificationActivity;
import com.xinze.xinze.module.login.LoginActivity;

/**
 * @author lxf
 * 未登录、未认证对话框
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
        rxDialogSureCancel.getTitleView().setText(R.string.unIdentification);
        rxDialogSureCancel.getContentView().setText(R.string.isGoIdentification);
        rxDialogSureCancel.getSureView().setText(R.string.goIdentification);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, CertificationActivity.class));
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
}
