package com.bibi.wisdom.main.home;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bibi.wisdom.R;
import com.bibi.wisdom.WebPageActivity;
import com.bibi.wisdom.bean.BannerBean;
import com.bibi.wisdom.bean.DeviceInfoBean;
import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.main.device.DeviceActivity;
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.utils.GlideBannerImageLoader;
import com.bibi.wisdom.utils.IKeys;
import com.bibi.wisdom.utils.ToastUtil;
import com.bibi.wisdom.view.CommonDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;


/**
 * 开关
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View {
    public static final int REQUEST_DEVICE = 111;

    @BindView(R.id.content)
    LinearLayout mLinearLayout;
    @BindView(R.id.roll_view_pager)
    Banner banner;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_list)
    ImageView ivList;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.iv_open)
    ImageView ivOpen;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    Unbinder unbinder;
    //banner列表
    List<BannerBean.BannersBean> home_logos = new ArrayList<>();
    //设备列表
    List<DeviceListBean.UserproductlistBean> list = new ArrayList<>();
    @BindView(R.id.tv_open)
    TextView tvOpen;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.rl_controller)
    RelativeLayout rlController;
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;

    private int deviceIndex = 0;
    private DeviceInfoBean deviceInfoBean; //当前设备状态

    private Disposable disposable;

    private long mLastClickTime = 0;
    public static final int TIME_INTERVAL = 3000;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_switch;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {
        initBanner();
        mPresenter.getBanner();
        mPresenter.getDeviceList();

        mLinearLayout.setOnTouchListener((v, event) -> {
            if (System.currentTimeMillis() - mLastClickTime >= TIME_INTERVAL) {
                mLastClickTime = System.currentTimeMillis();
                refreshDeviceInfo();
            }
            return false;
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            mPresenter.getDeviceList();
        }
        super.onHiddenChanged(hidden);
    }

    private void initBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideBannerImageLoader());
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (home_logos.size() > position && !TextUtils.isEmpty(home_logos.get(position).getHrefUrl())) {
                    startActivity(new Intent(getContext(), WebPageActivity.class).putExtra("url", home_logos.get(position).getHrefUrl()));
                }

            }
        });
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

    @Override
    public void onDetach() {
        super.onDetach();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    @OnClick({R.id.iv_setting, R.id.iv_list, R.id.iv_open, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                break;
            case R.id.iv_list:
                startActivityForResult(new Intent(getContext(), DeviceActivity.class)
                        .putExtra(IKeys.KEY_TYPE, DeviceActivity.TYPE_SHOW), REQUEST_DEVICE);
                break;
            case R.id.iv_open:
                confirmOpenDialog();
                break;
            case R.id.iv_close:
                confirmCloseDialog();
                break;
        }
    }

    //确认开启
    public void confirmOpenDialog() {
        CommonDialog commonDialog = new CommonDialog(getActivity(), "提示", "确定打开设备？"
                , "取消", "确定", null, new CommonDialog.CallBackListener() {
            @Override
            public void callBack() {
                mPresenter.openDevice(list.get(deviceIndex).getId());
            }
        });

        commonDialog.show();
    }

    //确认关闭
    public void confirmCloseDialog() {
        CommonDialog commonDialog = new CommonDialog(getActivity(), "提示", "确定关闭设备？"
                , "取消", "确定", null, new CommonDialog.CallBackListener() {
            @Override
            public void callBack() {
                mPresenter.closeDevice(list.get(deviceIndex).getId());
            }
        });

        commonDialog.show();
    }


    private void setDeviceInfo(int index) {
        rlController.setVisibility(View.VISIBLE);
        DeviceListBean.UserproductlistBean bean = list.get(index);
        String timeUnit = bean.getTimeUnit();
        if (TextUtils.isEmpty(timeUnit))
            timeUnit = "小时";
        tvPrice.setText(bean.getPrice() + "元/" + timeUnit);
        tvDeviceName.setText("当前设备：" + bean.getProductName());
        if (deviceInfoBean.getOnline().equals("0")) {
            tvStatus.setText("状态:离线");
            ivOpen.setImageResource(R.drawable.ic_close_inactive);
            ivClose.setImageResource(R.drawable.ic_close_inactive);
            ivOpen.setClickable(false);
            ivClose.setClickable(false);
        } else if (deviceInfoBean.getOnline().equals("1")) {
            int status = deviceInfoBean.getEqstatus();
            if (status == 0) {
                tvStatus.setText("状态：关闭");
                ivOpen.setImageResource(R.drawable.ic_close_inactive);
                ivClose.setImageResource(R.drawable.ic_open_active);
                ivOpen.setClickable(true);
                ivClose.setClickable(false);
            } else {
                ivOpen.setImageResource(R.drawable.ic_close_active);
                ivClose.setImageResource(R.drawable.ic_close_inactive);
                tvStatus.setText("状态：开启");
                ivOpen.setClickable(false);
                ivClose.setClickable(true);
            }
        }


    }

    private void resetDevice() {
        rlController.setVisibility(View.INVISIBLE);
        tvDeviceName.setText("当前设备：--");
        tvPrice.setText("--元/小时");
        tvStatus.setText("状态：--");
    }

    @Override
    public void getBannerSuccess(BannerBean bannerBean) {
        home_logos.clear();
        home_logos.addAll(bannerBean.getBanners());
        List<String> images = new ArrayList<>();
        for (BannerBean.BannersBean dataBean : bannerBean.getBanners()) {
            images.add(dataBean.getUrl());
            home_logos.add(dataBean);
        }
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void getBannerFail(String message) {
        ToastUtil.showToast(getContext(), message);
    }

    @Override
    public void getDeviceSuccess(DeviceListBean bean) {
        list.clear();
        list.addAll(bean.getUserproductlist());
        refreshDeviceInfo();
    }

    @Override
    public void getDeviceFail(String message) {
        ToastUtil.showToast(getContext(), message);
        resetDevice();
    }

    @Override
    public void getDeviceInfoSuccess(DeviceInfoBean bean) {
        deviceInfoBean = bean;
        setDeviceInfo(deviceIndex);
    }

    @Override
    public void getDeviceInfoFail(String message) {
        ToastUtil.showToast(getContext(), message);
        resetDevice();
    }

    @Override
    public void openDeviceSuccess() {
        ToastUtil.showToast(getContext(), "打开成功");
        ivOpen.setImageResource(R.drawable.ic_close_active);
        ivClose.setImageResource(R.drawable.ic_close_inactive);
        tvStatus.setText("状态：开启");
        ivOpen.setClickable(false);
        ivClose.setClickable(true);
    }

    @Override
    public void openDeviceFail(String message) {
        ToastUtil.showToast(getContext(), message);
    }

    @Override
    public void closeDeviceSuccess() {
        ToastUtil.showToast(getContext(), "关闭成功");
        tvStatus.setText("状态：关闭");
        ivOpen.setImageResource(R.drawable.ic_close_inactive);
        ivClose.setImageResource(R.drawable.ic_open_active);
        ivOpen.setClickable(true);
        ivClose.setClickable(false);
    }

    @Override
    public void closeDeviceFail(String message) {
        ToastUtil.showToast(getContext(), message);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_DEVICE:
                if (resultCode != Activity.RESULT_OK)
                    return;
                if (data != null) {
                    deviceIndex = data.getIntExtra(IKeys.KEY_INDEX, 0);
                    if (list.size() > deviceIndex) {
                        mPresenter.getDeviceInfo(list.get(deviceIndex).getId());
                    }
                }
                break;
        }
    }

    public void refreshDeviceInfo() {
        if (list.size() > deviceIndex) {
            mPresenter.getDeviceInfo(list.get(deviceIndex).getId());
        } else {
            resetDevice();
        }
    }
}
