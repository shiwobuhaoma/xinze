package com.xinze.xinze.http.widget;

import android.content.Context;
import android.content.DialogInterface;

/**
 * @author lxf
 * @date 2017/3/23.
 * 加载网络数据显示的对话框
 */

public class ProgressDialog {
    private static android.app.ProgressDialog progressDialog;

    public static void show(Context cxt, boolean cancelable, String str) {

        try {
            progressDialog = new android.app.ProgressDialog(cxt);
            progressDialog.setCancelable(cancelable);
            progressDialog.setMessage(str);
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    progressDialog.cancel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancle() {
        if (progressDialog == null) {
            return;
        }
        if (progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

}
