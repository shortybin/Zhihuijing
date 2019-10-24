package com.bibi.wisdom.main.maintain.maintainlist;

import android.content.Context;

import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.MaintainListBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MaintainListContract {
    interface View extends BaseView {

        void getDeviceSuccess(MaintainListBean bean);


        void getDeviceFail(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void getMaintainList(int type);
    }
}
