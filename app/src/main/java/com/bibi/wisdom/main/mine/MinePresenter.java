package com.bibi.wisdom.main.mine;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bibi.wisdom.bean.BannerBean;
import com.bibi.wisdom.mvp.BasePresenterImpl;
import com.bibi.wisdom.network.HttpUtil;
import com.bibi.wisdom.network.SubscribeHandler;
import com.bibi.wisdom.network.rxjava.ProgressSubscriber;
import com.bibi.wisdom.network.rxjava.SubscriberOnNextListener;
import com.bibi.wisdom.utils.UserService;

import io.reactivex.Observable;

/**
 * 我的
 */

public class MinePresenter extends BasePresenterImpl<MineContract.View> implements MineContract.Presenter {

    @Override
    public void logout() {
        Observable ob = HttpUtil.getInstance().logout();
        SubscriberOnNextListener<Object> listener = new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.logoutSuccess();
            }

            @Override
            public void onFail(String err) {
                mView.logoutFail(err);
            }
        };
        SubscribeHandler.observeOn(ob, new ProgressSubscriber(listener, ((Fragment) mView).getActivity(), true));
    }

    @Override
    public void delectUser() {
        Observable ob = HttpUtil.getInstance().delectUser(UserService.getUserInfo().getAccountInfo().getPhone());

        SubscriberOnNextListener<Object> listener = new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.delectUserSuccess();
            }

            @Override
            public void onFail(String err) {
                mView.delectUserFail(err);
            }
        };
        SubscribeHandler.observeOn(ob, new ProgressSubscriber(listener, ((Fragment) mView).getActivity(), true));
    }


}
