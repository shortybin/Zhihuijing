package com.bibi.wisdom.main.device;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibi.wisdom.R;
import com.bibi.wisdom.ScanActivity;
import com.bibi.wisdom.adapter.CommonRecyclerHolder;
import com.bibi.wisdom.adapter.DeviceAdapter;
import com.bibi.wisdom.adapter.ListenerWithPosition;
import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.DeviceRefreshBean;
import com.bibi.wisdom.bean.HomeRefreshBean;
import com.bibi.wisdom.main.add_device.AddDeviceActivity;
import com.bibi.wisdom.mvp.MVPBaseActivity;
import com.bibi.wisdom.utils.IKeys;
import com.bibi.wisdom.utils.RxBus;
import com.bibi.wisdom.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * 设备列表
 */

public class DeviceActivity extends MVPBaseActivity<DeviceContract.View, DevicePresenter> implements DeviceContract.View {
    public static final int TYPE_SHOW = 1;
    public static final int TYPE_MANAGE = 2;
    public static final int REQUEST_DEVICE = 123;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;

    DeviceAdapter adapter;

    List<DeviceListBean.UserproductlistBean> list = new ArrayList<>();
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private int type = 1;

    private Disposable disposable;

    private boolean needRefresh=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        ButterKnife.bind(this);
        if (getIntent() != null)
            type = getIntent().getIntExtra(IKeys.KEY_TYPE, 1);
        initView();
    }

    private void initView() {
        if (type == TYPE_SHOW){
            tvCommit.setVisibility(View.GONE);
        }else {
            tvCommit.setVisibility(View.VISIBLE);
        }
        tvTopTitle.setText("我的设备");
        smartLayout.setEnableRefresh(true);
        smartLayout.setEnableLoadMore(false);
        smartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getDeviceList();
            }
        });


        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNews.setHasFixedSize(true);
        adapter = new DeviceAdapter(this, deviceListener);
        rvNews.setAdapter(adapter);
        mPresenter.getDeviceList();


        disposable= RxBus.get().toObservable(DeviceRefreshBean.class).subscribe(consumer);
    }

    Consumer<DeviceRefreshBean> consumer=new Consumer<DeviceRefreshBean>(){

        @Override
        public void accept(DeviceRefreshBean homeRefreshBean) throws Exception {
            needRefresh=true;
        }
    };


    ListenerWithPosition.OnClickWithPositionListener deviceListener = new ListenerWithPosition.OnClickWithPositionListener<CommonRecyclerHolder>() {


        @Override
        public void onClick(View v, int position, CommonRecyclerHolder holder) {
            if (type == TYPE_SHOW) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(IKeys.KEY_INDEX, position);
                setResult(RESULT_OK, resultIntent);
                finish();
            }else {
                Intent intent=new Intent(DeviceActivity.this, AddDeviceActivity.class);
                intent.putExtra(IKeys.KEY_DATA,list.get(position));
                intent.putExtra(IKeys.KEY_FROM,AddDeviceActivity.FROM_LIST);
                startActivity(intent);
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if(needRefresh){
            mPresenter.getDeviceList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable!=null&&!disposable.isDisposed()){
            disposable.dispose();
            disposable=null;
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void getDeviceSuccess(DeviceListBean bean) {
        smartLayout.finishRefresh();
        if (bean == null)
            return;
        list.clear();
        list.addAll(bean.getUserproductlist());
        if(list.size()==0)
            ToastUtil.showToast(this,"暂无数据");
        adapter.setData(list);

    }

    @Override
    public void getDeviceFail(String message) {
        ToastUtil.showToast(this,message);
        smartLayout.finishRefresh();
    }

    @OnClick(R.id.tv_commit)
    public void onAddClicked() {
        startActivity(new Intent(this, ScanActivity.class));
    }

}
