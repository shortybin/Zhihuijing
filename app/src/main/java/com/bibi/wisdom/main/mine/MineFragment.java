package com.bibi.wisdom.main.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bibi.wisdom.AboutUsActivity;
import com.bibi.wisdom.R;
import com.bibi.wisdom.WebPageActivity;
import com.bibi.wisdom.main.device.DeviceActivity;
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.user.login.LoginActivity;
import com.bibi.wisdom.utils.IKeys;
import com.bibi.wisdom.utils.UserService;
import com.bibi.wisdom.view.CommonDialog;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 我的
 */

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter> implements MineContract.View {

    @BindView(R.id.tv_logo)
    TextView tvLogo;
    @BindView(R.id.rl_home_top)
    RelativeLayout rlHomeTop;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.ll_devices_manage)
    LinearLayout llDevicesManage;
    @BindView(R.id.ll_version_info)
    LinearLayout llVersionInfo;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.ll_delect_user)
    LinearLayout mDelectUser;
    Unbinder unbinder;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init() {
        if (UserService.getUserInfo() != null)
            tvUsername.setText(UserService.getUserInfo().getAccountInfo().getPhone());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_devices_manage, R.id.ll_version_info, R.id.tv_commit, R.id.ll_privacy_policy, R.id.ll_delect_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_devices_manage:
                startActivity(new Intent(getActivity(), DeviceActivity.class).putExtra(IKeys.KEY_TYPE, DeviceActivity.TYPE_MANAGE));
                break;
            case R.id.ll_version_info:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.ll_privacy_policy:
                startActivity(new Intent(getContext(), WebPageActivity.class).putExtra("url", "http://www.huahuazn.com/yinsi.html"));
                break;
            case R.id.tv_commit:
                confirmDialog();
                break;
            case R.id.ll_delect_user:
                delectDialog();
                break;
        }
    }

    @Override
    public void logoutSuccess() {
        UserService.logout();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void logoutFail(String message) {
        RxToast.showToast(message);
    }

    @Override
    public void delectUserSuccess() {
        RxToast.showToast("注销成功");
        UserService.logout();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void delectUserFail(String message) {
        RxToast.showToast(message);
    }


    public void confirmDialog() {
        CommonDialog commonDialog = new CommonDialog(getActivity(), "提示", "确定退出？", "取消", "确定", null, new CommonDialog.CallBackListener() {
            @Override
            public void callBack() {
                mPresenter.logout();
            }
        });

        commonDialog.show();
    }

    public void delectDialog() {
        CommonDialog commonDialog = new CommonDialog(getActivity(), "提示", "注销用户将清除账号及账号内的所有信息，确认注销吗？", "取消", "确定", null, new CommonDialog.CallBackListener() {
            @Override
            public void callBack() {
                mPresenter.delectUser();
            }
        });

        commonDialog.show();
    }
}
