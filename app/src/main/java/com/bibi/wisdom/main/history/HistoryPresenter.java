package com.bibi.wisdom.main.history;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bibi.wisdom.bean.BannerBean;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.mvp.BasePresenterImpl;
import com.bibi.wisdom.network.HttpUtil;
import com.bibi.wisdom.network.SubscribeHandler;
import com.bibi.wisdom.network.rxjava.ProgressSubscriber;
import com.bibi.wisdom.network.rxjava.SubscriberOnNextListener;

import io.reactivex.Observable;

/**
 * 历史
 */

public class HistoryPresenter extends BasePresenterImpl<HistoryContract.View> implements HistoryContract.Presenter{

    @Override
    public void getHistoryList() {
        Observable ob = HttpUtil.getInstance().getHistoryList();
        SubscriberOnNextListener<HistoryBean> listener=new SubscriberOnNextListener<HistoryBean>() {
            @Override
            public void onNext(HistoryBean bean) {
                mView.getHistorySuccess(bean);
            }

            @Override
            public void onFail(String err) {
                mView.getHistoryFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, ((Fragment) mView).getActivity(),true));

    }
}
