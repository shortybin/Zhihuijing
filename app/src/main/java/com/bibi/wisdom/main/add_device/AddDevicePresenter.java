package com.bibi.wisdom.main.add_device;


import com.bibi.wisdom.mvp.BasePresenterImpl;
import com.bibi.wisdom.network.HttpUtil;
import com.bibi.wisdom.network.SubscribeHandler;
import com.bibi.wisdom.network.rxjava.ProgressSubscriber;
import com.bibi.wisdom.network.rxjava.SubscriberOnNextListener;

import io.reactivex.Observable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddDevicePresenter extends BasePresenterImpl<AddDeviceContract.View> implements AddDeviceContract.Presenter{

    @Override
    public void unbindDevice(String userId,String id) {
        Observable ob = HttpUtil.getInstance().unbindDevice(id);
        SubscriberOnNextListener<Object> listener=new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.unbindSuccess();
            }

            @Override
            public void onFail(String err) {
                mView.unbindFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, mView.getContext(),true));

    }

    @Override
    public void modifyDevice(String userId, String id, String deviceName, String price) {
        Observable ob = HttpUtil.getInstance().modifyDevice(id,deviceName,price);
        SubscriberOnNextListener<Object> listener=new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.modifySuccess();
            }

            @Override
            public void onFail(String err) {
                mView.modifyFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, mView.getContext(),true));

    }

    @Override
    public void addDevice(String code, String deviceName, String price) {
        Observable ob = HttpUtil.getInstance().bindDevice(code,deviceName,price);
        SubscriberOnNextListener<Object> listener=new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.modifySuccess();
            }

            @Override
            public void onFail(String err) {
                mView.modifyFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, mView.getContext(),true));
    }
}
