package com.xinze.xinze.module.add;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加车辆
 *
 * @author lxf
 */
public class AddMyCarActivity extends BaseActivity {


    @BindView(R.id.add_car_car_number)
    EditText addCarCarNumber;
    @BindView(R.id.add_car_select_car_type)
    TextView addCarSelectCarType;
    @BindView(R.id.add_car_car_load)
    EditText addCarCarLoad;
    @BindView(R.id.add_car_upload_license)
    ImageView addCarUploadLicense;
    @BindView(R.id.add_car_submit)
    Button addCarSubmit;

    @Override
    protected int initLayout() {
        return R.layout.add_my_car_activity;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.add_car_select_car_type, R.id.add_car_upload_license, R.id.add_car_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_car_select_car_type:
                break;
            case R.id.add_car_upload_license:
                break;
            case R.id.add_car_submit:


                break;
            default:
                break;
        }
    }
}
