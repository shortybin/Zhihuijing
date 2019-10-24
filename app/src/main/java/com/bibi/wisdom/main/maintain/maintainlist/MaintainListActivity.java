package com.bibi.wisdom.main.maintain.maintainlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibi.wisdom.R;
import com.bibi.wisdom.adapter.MaintainListAdapter;
import com.bibi.wisdom.bean.MaintainListBean;
import com.bibi.wisdom.mvp.MVPBaseActivity;
import com.bibi.wisdom.utils.IKeys;
import com.bibi.wisdom.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 维护联系人
 */

public class MaintainListActivity extends MVPBaseActivity<MaintainListContract.View, MaintainListPresenter> implements MaintainListContract.View {
    public static final int TYPE_SWITCH=1;
    public static final int TYPE_REFORM=2;
    public static final int TYPE_MAINTAIN=3;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;

    private MaintainListAdapter adapter;

    private int type=-1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_list);
        ButterKnife.bind(this);
        if(getIntent()!=null){
            type=getIntent().getIntExtra(IKeys.KEY_TYPE,-1);
        }
        if(type==-1){
            throw new IllegalArgumentException("type 参数不能为空");
        }

        initView();
    }


    private void initView(){
        switch (type){
            case TYPE_SWITCH:
                tvTopTitle.setText("远程开关");
                break;
            case TYPE_REFORM:
                tvTopTitle.setText("电路改造");
                break;
            case TYPE_MAINTAIN:
                tvTopTitle.setText("机井维护");
                break;
        }

        smartLayout.setEnableRefresh(true);
        smartLayout.setEnableLoadMore(false);
        smartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getMaintainList(type);
            }
        });


        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNews.setHasFixedSize(true);
        adapter = new MaintainListAdapter(this);
        rvNews.setAdapter(adapter);
        mPresenter.getMaintainList(type);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getDeviceSuccess(MaintainListBean bean) {
        smartLayout.finishRefresh();
        if(bean.getUser_contact_list()!=null&&bean.getUser_contact_list().size()>0)
            adapter.setData(bean.getUser_contact_list());
        else
            ToastUtil.showToast(this,"暂无数据");
    }

    @Override
    public void getDeviceFail(String message) {
        ToastUtil.showToast(this,message);
        smartLayout.finishRefresh();
    }
}
