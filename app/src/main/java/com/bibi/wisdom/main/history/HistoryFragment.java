package com.bibi.wisdom.main.history;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bibi.wisdom.R;
import com.bibi.wisdom.adapter.HistoryAdapter;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 历史
 */

public class HistoryFragment extends MVPBaseFragment<HistoryContract.View, HistoryPresenter> implements HistoryContract.View {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    Unbinder unbinder;

    HistoryAdapter adapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_history;
    }

    @Override
    protected void init() {
        smartLayout.setEnableRefresh(true);
        smartLayout.setEnableLoadMore(false);
        smartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getHistoryList();
            }
        });


        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNews.setHasFixedSize(true);
        adapter = new HistoryAdapter(getActivity());


        mPresenter.getHistoryList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getHistorySuccess(HistoryBean bean) {
        smartLayout.finishRefresh();
        List<HistoryBean.ListBean> listBeans=bean.getList();
        if(listBeans!=null&&listBeans.size()>0){
            adapter.setData(listBeans);
            rvNews.setAdapter(adapter);
        }else {
            ToastUtil.showToast(getContext(),"暂无数据");
        }
    }

    @Override
    public void getHistoryFail(String message) {
        smartLayout.finishRefresh();
        ToastUtil.showToast(getContext(),message);
    }
}
