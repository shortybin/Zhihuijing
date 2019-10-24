package com.bibi.wisdom.main.maintain.maintainlist;

import android.content.Context;

import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.MaintainListBean;
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

public class MaintainListPresenter extends BasePresenterImpl<MaintainListContract.View> implements MaintainListContract.Presenter{

    @Override
    public void getMaintainList(int type) {
        Observable ob = HttpUtil.getInstance().getMaintainList(type);
        SubscriberOnNextListener<MaintainListBean> listener=new SubscriberOnNextListener<MaintainListBean>() {
            @Override
            public void onNext(MaintainListBean bean) {
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
