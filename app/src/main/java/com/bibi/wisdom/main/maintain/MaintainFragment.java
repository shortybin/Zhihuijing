package com.bibi.wisdom.main.maintain;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.NoticeBean;
import com.bibi.wisdom.main.maintain.maintainlist.MaintainListActivity;
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.utils.IKeys;
import com.bibi.wisdom.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 维护
 */

public class MaintainFragment extends MVPBaseFragment<MaintainContract.View, MaintainPresenter> implements MaintainContract.View {

    @BindView(R.id.tv_logo)
    TextView tvLogo;
    @BindView(R.id.rl_home_top)
    RelativeLayout rlHomeTop;
    @BindView(R.id.ll_remote_switch)
    LinearLayout llRemoteSwitch;
    @BindView(R.id.ll_circuit_reform)
    LinearLayout llCircuitReform;
    @BindView(R.id.ll_machine_maintain)
    LinearLayout llMachineMaintain;
    Unbinder unbinder;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.tv_notice_title)
    TextView tvNoticeTitle;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_maintain;
    }

    @Override
    protected void init() {

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getNoticeList(1);
    }

    @OnClick({R.id.ll_remote_switch, R.id.ll_circuit_reform, R.id.ll_machine_maintain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_remote_switch:
                startActivity(new Intent(getActivity(), MaintainListActivity.class).putExtra(IKeys.KEY_TYPE, MaintainListActivity.TYPE_SWITCH));
                break;
            case R.id.ll_circuit_reform:
                startActivity(new Intent(getActivity(), MaintainListActivity.class).putExtra(IKeys.KEY_TYPE, MaintainListActivity.TYPE_REFORM));
                break;
            case R.id.ll_machine_maintain:
                startActivity(new Intent(getActivity(), MaintainListActivity.class).putExtra(IKeys.KEY_TYPE, MaintainListActivity.TYPE_MAINTAIN));
                break;
        }
    }

    @Override
    public void getNoticeSuccess(NoticeBean bean) {
        if (bean != null) {
            List<NoticeBean.NoticeListBean> notice_list = bean.getNotice_list();
            if (notice_list != null && notice_list.size() > 0) {
                NoticeBean.NoticeListBean noticeListBean = notice_list.get(0);
                tvNoticeTitle.setText(noticeListBean.getTitle());
                tvNotice.setText(noticeListBean.getContent());
            }
        }
    }

    @Override
    public void getNoticeFail(String message) {
        ToastUtil.showToast(getContext(), message);
    }
}
