package com.bibi.wisdom.main.device;

import android.content.Context;

import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DeviceContract {
    interface View extends BaseView {
        void getDeviceSuccess(DeviceListBean bean);


        void getDeviceFail(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void getDeviceList();
    }
}
