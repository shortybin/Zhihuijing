package com.bibi.wisdom.main.device;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.mvp.BasePresenterImpl;
import com.bibi.wisdom.network.HttpUtil;
import com.bibi.wisdom.network.SubscribeHandler;
import com.bibi.wisdom.network.rxjava.ProgressSubscriber;
import com.bibi.wisdom.network.rxjava.SubscriberOnNextListener;

import io.reactivex.Observable;

/**
 * 设备列表
 */

public class DevicePresenter extends BasePresenterImpl<DeviceContract.View> implements DeviceContract.Presenter{

    @Override
    public void getDeviceList() {
        Observable ob = HttpUtil.getInstance().getDeviceList();
        SubscriberOnNextListener<DeviceListBean> listener=new SubscriberOnNextListener<DeviceListBean>() {
            @Override
            public void onNext(DeviceListBean bean) {
                mView.getDeviceSuccess(bean);
            }

            @Override
            public void onFail(String err) {
                mView.getDeviceFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, (Context) mView,false));
    }
}
