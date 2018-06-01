package com.xinze.xinze.module.add.view;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.add.presenter.AddMyCarPresenterImp;
import com.xinze.xinze.widget.BottomProvincePopupMenu;
import com.xinze.xinze.widget.SimpleToolbar;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 添加车辆
 *
 * @author lxf
 */
public class AddMyCarActivity extends BaseActivity implements IAddMyCarView {


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
    @BindView(R.id.add_my_truck_toolbar)
    SimpleToolbar addMyTruckToolbar;
    private AddMyCarPresenterImp amcpi;
    private String imgUrl;
    private BottomProvincePopupMenu bppm;

    @Override
    protected int initLayout() {
        return R.layout.add_my_car_activity;
    }

    @Override
    protected void initView() {
        addMyTruckToolbar.setMainTitle(getString(R.string.add_car));
        addMyTruckToolbar.setLeftTitleVisible();
        addMyTruckToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.add_car_car_number,R.id.add_car_select_car_type, R.id.add_car_upload_license, R.id.add_car_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_car_car_number:
                hideKeyboard();
                if (bppm ==null){
                    bppm = new BottomProvincePopupMenu(this);
                }
                bppm.setOnMenuClickListener(new BottomProvincePopupMenu.MenuClickListener() {
                    @Override
                    public void onMenuItemClick(View itemView, String province) {
                        addCarCarNumber.setText(province);
                        addCarCarNumber.setSelection(1);
                    }
                });
                bppm.showMenu();
                break;
            case R.id.add_car_select_car_type:

                break;
            case R.id.add_car_upload_license:

                break;
            case R.id.add_car_submit:
                String carNumber = addCarCarNumber.getText().toString();
                String carType = addCarSelectCarType.getText().toString();
                String carLoad = addCarCarLoad.getText().toString();

                amcpi = new AddMyCarPresenterImp(this, this);
                amcpi.addTruck(carNumber, carType, carLoad, imgUrl);

                break;
            default:
                break;
        }
    }

    @Override
    public void addTruckSuccess(String msg) {

    }

    @Override
    public void addTruckFailed(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void imageUploadSuccess(String msg) {
        imgUrl = msg;
        //构建要上传的文件
        File file = new File(msg);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("application/otcet-stream"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);
    }

    @Override
    public void imageUploadFailed(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (amcpi != null) {
            amcpi.onDestroy();
        }
    }
}
