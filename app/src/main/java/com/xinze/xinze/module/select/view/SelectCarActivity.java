package com.xinze.xinze.module.select.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.select.SelectCarAdapter;
import com.xinze.xinze.module.select.presenter.SelectCarPresenterImp;
import com.xinze.xinze.module.transport.module.Car;
import com.xinze.xinze.utils.DividerItemDecoration;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 选择车辆
 *
 * @author lxf
 */
public class SelectCarActivity extends BaseActivity implements ISelectCarView {


    @BindView(R.id.select_tool_bar)
    SimpleToolbar selectToolBar;
    @BindView(R.id.select_top)
    LinearLayout selectTop;
    @BindView(R.id.select_rv)
    RecyclerView selectRv;
    @BindView(R.id.select_service)
    TextView selectService;
    @BindView(R.id.select_confirm_bill)
    Button selectConfirmBill;
    private SelectCarAdapter sca;

    @Override
    protected int initLayout() {
        return R.layout.select_car_activity;
    }

    @Override
    protected void initView() {
        initToolbar();
        selectService.setText(Html.fromHtml(getResources().getString(R.string.select_read_service)));

        sca = new SelectCarAdapter(this);
        selectRv.setLayoutManager(new LinearLayoutManager(this));
        selectRv.addItemDecoration(new DividerItemDecoration(this));
        selectRv.setAdapter(sca);
        sca.setOnItemClickListener(new SelectCarAdapter.OnItemClickListener() {
            @Override
            public void click(View view, int position) {
                sca.updateState(position);
            }
        });
    }

    private void initToolbar() {

        selectToolBar.setLeftTitleVisible();
        selectToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        SelectCarPresenterImp scp = new SelectCarPresenterImp(this, this);
        scp.getCarryTruckList(App.mUser.getId());
    }

    @Override
    public void getCarryTruckListSuccess(String msg) {

    }

    @Override
    public void getCarryTruckListFailed(String msg) {

    }

    public void setData(List<Car> data) {
        sca.setData(data);
    }

    @OnClick({R.id.select_top, R.id.select_service, R.id.select_confirm_bill})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_top:
                break;
            case R.id.select_service:
                break;
            default:
                break;
        }
    }


}
