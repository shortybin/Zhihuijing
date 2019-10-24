package com.bibi.wisdom.main.add_device;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.DeviceRefreshBean;
import com.bibi.wisdom.bean.HomeRefreshBean;
import com.bibi.wisdom.mvp.MVPBaseActivity;
import com.bibi.wisdom.utils.IKeys;
import com.bibi.wisdom.utils.RxBus;
import com.bibi.wisdom.utils.ToastUtil;
import com.bibi.wisdom.utils.UserService;
import com.bibi.wisdom.view.CommonDialog;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 添加，修改设备
 */

public class AddDeviceActivity extends MVPBaseActivity<AddDeviceContract.View, AddDevicePresenter> implements AddDeviceContract.View {
    public static final int FROM_LIST = 11;
    public static final int FROM_ADD = 12;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    DeviceListBean.UserproductlistBean bean;
    @BindView(R.id.ll_modify)
    LinearLayout llModify;
    @BindView(R.id.tv_add_device)
    TextView tvAddDevice;
    private int from = 11;
    private String deviceCode;

    private String name;
    private String price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            from = getIntent().getIntExtra(IKeys.KEY_FROM, 11);
            if (from == FROM_LIST) {
                llModify.setVisibility(View.VISIBLE);
                bean = (DeviceListBean.UserproductlistBean) getIntent().getSerializableExtra(IKeys.KEY_DATA);
                if (bean == null) {
                    ToastUtil.showToast(this, "获取设备失败");
                    finish();
                }
            } else {
                tvAddDevice.setVisibility(View.VISIBLE);
                deviceCode = getIntent().getStringExtra(IKeys.KEY_ID);
                if (deviceCode == null) {
                    ToastUtil.showToast(this, "获取设备信息失败");
                    finish();
                }
            }

        }

        RxBus.get().post(new HomeRefreshBean());

        initView();

    }


    private void initView() {
        if (from == FROM_LIST) {
            tvCode.setText("设备编号： " + bean.getProductCode());
            etName.setText(bean.getProductName());
            etPrice.setText(bean.getPrice() + "");
        } else {
            tvCode.setText("设备编号： " + deviceCode);
        }

    }

    @OnClick({R.id.iv_back, R.id.tv_commit, R.id.tv_delete,R.id.tv_add_device})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_commit:
                if (checkData()) {
                    confirmModifyDialog();
                }
                break;
            case R.id.tv_delete:
                confirmDeleteDialog();
                break;
            case R.id.tv_add_device:
                if (checkData()) {
                    mPresenter.addDevice(deviceCode,name,price);
                }
                break;
        }
    }

    /**
     * 检查
     */
    private boolean checkData() {
        name = etName.getText().toString().trim();
        price = etPrice.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            RxToast.normal(this, "请输入设备名称").show();
            return false;
        }

        if (TextUtils.isEmpty(price)) {
            RxToast.normal(this, "请输入价格").show();
            return false;
        }
        return true;
    }


    //确认开启
    public void confirmDeleteDialog() {
        CommonDialog commonDialog = new CommonDialog(this, "提示", "确定删除设备？"
                , "取消", "确定", null, new CommonDialog.CallBackListener() {
            @Override
            public void callBack() {
                mPresenter.unbindDevice(bean.getUserId(), bean.getProductId());
            }
        });

        commonDialog.show();
    }

    //确认修改
    public void confirmModifyDialog() {
        CommonDialog commonDialog = new CommonDialog(this, "提示", "确定修改设备？"
                , "取消", "确定", null, new CommonDialog.CallBackListener() {
            @Override
            public void callBack() {
                mPresenter.modifyDevice(bean.getUserId(), bean.getId(), name, price);
            }
        });

        commonDialog.show();
    }


    @Override
    public void unbindSuccess() {
        RxBus.get().post(new DeviceRefreshBean());
        ToastUtil.showToast(this, "删除成功");
        finish();
    }

    @Override
    public void unbindFail(String message) {
        ToastUtil.showToast(this, message);
    }

    @Override
    public void modifySuccess() {
        RxBus.get().post(new DeviceRefreshBean());
        ToastUtil.showToast(this, "修改成功");
        finish();
    }

    @Override
    public void modifyFail(String message) {
        ToastUtil.showToast(this, message);
    }

    @Override
    public void addSuccess() {
        RxBus.get().post(new DeviceRefreshBean());
        ToastUtil.showToast(this, "添加成功");
        finish();
    }

    @Override
    public void addFail(String message) {
        ToastUtil.showToast(this, message);
    }
}
