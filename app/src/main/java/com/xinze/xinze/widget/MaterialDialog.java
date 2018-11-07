package com.xinze.xinze.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinze.xinze.R;

import butterknife.BindView;
import butterknife.OnClick;

public class MaterialDialog extends Dialog {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.content)
    TextView mMessage;
    @BindView(R.id.s_cancel)
    Button sCancel;
    @BindView(R.id.s_ok)
    Button sOk;
    private String title;
    private String message;
    private Context mContext;
    private boolean cancelable;

    public MaterialDialog(@NonNull Context context) {
        this(context, 0);
        mContext = context;
    }

    public MaterialDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        initView();
    }


    protected MaterialDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        this.cancelable = cancelable;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.material_dialog, null);

    }

    public void setTitle(String title) {
        this.title = title;

    }

    public void setMessage(String mMessage) {
        this.message = mMessage;
    }

    @OnClick({R.id.s_cancel, R.id.s_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.s_cancel:
                break;
            case R.id.s_ok:
                break;
            default:
                break;
        }
    }


    public interface OnClickListener{
        void cancle();
        void confim();
    }
}
