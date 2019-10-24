package com.bibi.wisdom.main.home;

import android.content.Context;

import com.bibi.wisdom.bean.BannerBean;
import com.bibi.wisdom.bean.DeviceInfoBean;
import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeContract {
    interface View extends BaseView {
        void getBannerSuccess(BannerBean bannerBean);

        void getBannerFail(String message);

        void getDeviceSuccess(DeviceListBean bean);

        void getDeviceFail(String message);

        void getDeviceInfoSuccess(DeviceInfoBean bean);

        void getDeviceInfoFail(String message);

        void openDeviceSuccess();

        void openDeviceFail(String message);

        void closeDeviceSuccess();

        void closeDeviceFail(String message);
    }

    interface  Presenter extends BasePresenter<View> {

        void getBanner();
        //获取设备列表
        void getDeviceList();
        //获取设备状态
        void getDeviceInfo(String id);
        //打开设备
        void openDevice(String id);
        //关闭设备
        void closeDevice(String id);


    }
}
