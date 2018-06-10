package com.xinze.xinze.module.line.view;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.line.presenter.LineEditPresenterImp;
import com.xinze.xinze.module.regular.modle.Route;

import java.util.ArrayList;

import butterknife.BindView;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;

/**
 * @author lxf
 */
public class LineEditFragment extends BaseFragment implements View.OnClickListener, ILineEditView,LineListFragment.OnNextPageListener, AddressPicker.OnAddressPickListener {
    @BindView(R.id.line_edit_from)
    EditText lineEditFrom;
    @BindView(R.id.line_edit_to)
    EditText lineEditTo;
    @BindView(R.id.line_edit_confirm)
    Button lineEditConfirm;
    private LineEditPresenterImp lepi;
    private Route line;
    private String id;
    private LineListFragment.OnNextPageListener mOnNextPageListener;
    /**
     * 选择的省份、城市、区
     */
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    /**
     * 是否隐藏省、区列表
     */
    private boolean hideProvince = false;
    private boolean hideCounty = false;
    private ArrayList<Province> provinces;

    private int mCurrentClickViewRes;
    private String fromCityId;
    private String toCityId;
    private Route route;

    public boolean isRequested() {
        return isRequested;
    }

    private boolean isRequested;

    @Override
    protected int initLayout() {
        return R.layout.line_edit_fragment;
    }

    @Override
    protected void initView() {
        provinces = new ArrayList<>();
        lineEditConfirm.setOnClickListener(this);
        lineEditFrom.setOnClickListener(this);
        lineEditFrom.setInputType(InputType.TYPE_NULL);
        lineEditTo.setOnClickListener(this);
        lineEditTo.setInputType(InputType.TYPE_NULL);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_edit_confirm:
                String from = lineEditFrom.getText().toString();
                String to = lineEditTo.getText().toString();
                if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)){
                    if (line == null) {
                        lepi.addRegularRoute(fromCityId, toCityId);
                    } else {
                        lepi.editRegularRoute(id, fromCityId, toCityId);
                    }
                }


                break;
            case R.id.line_edit_from:
                mCurrentClickViewRes = R.id.line_edit_from;
                setAreaList(provinces);
                break;
            case R.id.line_edit_to:
                mCurrentClickViewRes = R.id.line_edit_to;
                setAreaList(provinces);
                break;
            default:
                break;
        }
    }


    @Override
    public void addRegularRouteSuccess(String msg) {
//           shotToast(msg);
        isRequested = true;
        mOnNextPageListener.next(route);
    }

    @Override
    public void addRegularRouteFailed(String msg) {
        shotToast(msg);
    }

    @Override
    public void getAreaListSuccess(String msg) {
//          shotToast(msg);

    }

    @Override
    public void getAreaListFailed(String msg) {
        shotToast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lepi != null){
            lepi.onDestroy();
        }

    }

    public void refresh() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            line = (Route) arguments.get("line");
            if (line != null) {
                String fromAreaName = line.getFrom_area_name();
                lineEditFrom.setText(fromAreaName);
                String toAreaName = line.getTo_area_name();
                lineEditTo.setText(toAreaName);
                id = line.getId();
            } else {
                lineEditFrom.setText("");
                lineEditTo.setText("");
            }

        }
        if (provinces != null && provinces.size()==0){
            lepi = new LineEditPresenterImp(this, mActivity);
            lepi.getAreaList("0");
        }

    }
    public void setOnNextPageListener(LineListFragment.OnNextPageListener onNextPageListener) {
        mOnNextPageListener = onNextPageListener;
    }

    @Override
    public void next(Route route) {
        this.route = route;
        mOnNextPageListener.next(route);
    }

    public void setAreaList(ArrayList<Province> result) {
        this.provinces = result;
        if (result.size() > 0) {
            AddressPicker  picker = new AddressPicker(mActivity, result);
            picker.setHideProvince(hideProvince);
            picker.setHideCounty(hideCounty);
            if (hideCounty) {
                //将屏幕分为3份，省级和地级的比例为1:2
                picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);
            } else {
                //省级、地级和县级的比例为2:3:3
                picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);
            }
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            picker.setOnAddressPickListener(this);
            picker.show();
        }
    }

    @Override
    public void onAddressPicked(Province province, City city, County county) {
        if (mCurrentClickViewRes == R.id.line_edit_from){
            fromCityId = county.getCityId();
            lineEditFrom.setText(province.getAreaName()+city.getAreaName()+county.getAreaName());
        }else{
            toCityId = county.getCityId();
            lineEditTo.setText(province.getAreaName()+city.getAreaName()+county.getAreaName());
        }
    }
}
