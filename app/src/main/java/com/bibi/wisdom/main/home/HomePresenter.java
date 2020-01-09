package com.bibi.wisdom.main.home;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bibi.wisdom.bean.BannerBean;
import com.bibi.wisdom.bean.DeviceInfoBean;
import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.UserLoginBean;
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

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter{

    @Override
    public void getBanner() {
        Observable ob = HttpUtil.getInstance().getBanner();
        SubscriberOnNextListener<BannerBean> listener=new SubscriberOnNextListener<BannerBean>() {
            @Override
            public void onNext(BannerBean bannerBean) {
                mView.getBannerSuccess(bannerBean);
            }

            @Override
            public void onFail(String err) {
                mView.getBannerFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, ((Fragment) mView).getActivity(),true));
    }

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
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, ((Fragment) mView).getActivity(),false));
    }

    @Override
    public void getDeviceInfo(String id) {
        Observable ob = HttpUtil.getInstance().getDeviceInfo(id);
        SubscriberOnNextListener<DeviceInfoBean> listener=new SubscriberOnNextListener<DeviceInfoBean>() {
            @Override
            public void onNext(DeviceInfoBean bean) {
                mView.getDeviceInfoSuccess(bean);
            }

            @Override
            public void onFail(String err) {
                mView.getDeviceInfoFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, ((Fragment) mView).getActivity(),false));
    }

    @Override
    public void openDevice(String id) {
        Observable ob = HttpUtil.getInstance().openDevice(id);
        SubscriberOnNextListener<Object> listener=new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.openDeviceSuccess();
            }

            @Override
            public void onFail(String err) {
                mView.openDeviceFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, ((Fragment) mView).getActivity(),true));
    }

    @Override
    public void closeDevice(String id) {
        Observable ob = HttpUtil.getInstance().closeDevice(id);
        SubscriberOnNextListener<Object> listener=new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.closeDeviceSuccess();
            }

            @Override
            public void onFail(String err) {
                mView.closeDeviceFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, ((Fragment) mView).getActivity(),true));
    }
}
