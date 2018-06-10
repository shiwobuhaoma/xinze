package com.xinze.xinze.module.line.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.line.adapter.LineListAdapter;
import com.xinze.xinze.module.line.presenter.LineListPresenterImp;
import com.xinze.xinze.module.regular.modle.Route;
import com.xinze.xinze.utils.DividerItemDecoration;
import com.xinze.xinze.widget.BottomPopupMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author lxf
 */
public class LineListFragment extends BaseFragment implements View.OnClickListener, LineListAdapter.OnClickListener, ILineListView {
    @BindView(R.id.line_rv_list)
    RecyclerView lineRvList;
    @BindView(R.id.line_bt_add)
    Button lineBtAdd;
    @BindView(R.id.lines_srl)
    SmartRefreshLayout linesSrl;

//    private int pageNo = 1;
//    private int pageSize = 10;

    private BottomPopupMenu mBottomMenu;

    private OnNextPageListener mOnNextPageListener;

    private List<Route> mRoutes = new ArrayList<>();
    private Route route;
    private LineListPresenterImp llpi;
    private LineListAdapter adapter;
    private int mCurrentPosition;

    @Override
    protected int initLayout() {
        return R.layout.line_list_fragent;
    }

    @Override
    protected void initView() {
        lineBtAdd.setOnClickListener(this);
        lineRvList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        lineRvList.addItemDecoration(new DividerItemDecoration(mActivity));
        adapter = new LineListAdapter(mActivity);
        adapter.setOnItemClickListener(this);
        lineRvList.setAdapter(adapter);
        linesSrl.setEnableLoadMore(false);
        linesSrl.setEnableRefresh(false);
//        linesSrl.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                pageNo = 1;
//                llpi.getRegularRouteList();
//            }
//        });
//        linesSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                pageNo++;
//                llpi.getRegularRouteList();
//            }
//        });
    }

    @Override
    protected void initData() {
        super.initData();
        llpi = new LineListPresenterImp(this, mActivity);
        llpi.getRegularRouteList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_bt_add:
                mOnNextPageListener.next(null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view, int position) {
        mCurrentPosition = position;
        route = mRoutes.get(position);
        showBottomMenu();
    }

    private void showBottomMenu() {
        if (mBottomMenu == null) {
            initBottom();
        } else {
            mBottomMenu.showMenu();
        }
    }

    private void initBottom() {
        mBottomMenu = new BottomPopupMenu(mActivity);
        mBottomMenu.addItem(1, "编辑", getResources().getColor(R.color.themeBlack));
        mBottomMenu.addItem(2, "删除", getResources().getColor(R.color.themeOrange));
        mBottomMenu.setOnMenuClickListener(new BottomPopupMenu.MenuClickListener() {
            @Override
            public void onMenuItemClick(View itemView, int itemId) {
                switch (itemId) {
                    case 1:
                        mOnNextPageListener.next(route);
                        break;
                    case 2:
                        llpi.delRegularRoute(route.getId());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void getRegularRouteListSuccess(String msg) {
//          shotToast(msg);
    }

    @Override
    public void getRegularRouteListFailed(String msg) {
        shotToast(msg);
    }

    @Override
    public void delRegularRouteSuccess(String msg) {
        mRoutes.remove(mCurrentPosition);
        adapter.setData(mRoutes);
//           shotToast(msg);
    }

    @Override
    public void delRegularRouteFailed(String msg) {
        shotToast(msg);
    }

    public void setRouteData(List<Route> routes) {
        mRoutes = routes;
        adapter.setData(mRoutes);
    }

    public interface OnNextPageListener {
        void next(Route route);
    }

    public void setOnNextPageListener(OnNextPageListener onNextPageListener) {
        mOnNextPageListener = onNextPageListener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (llpi != null) {
            llpi.onDestroy();
        }

    }
}
