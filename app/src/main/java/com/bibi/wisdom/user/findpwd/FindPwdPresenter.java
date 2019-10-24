package com.bibi.wisdom.user.findpwd;

import android.content.Context;

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

public class FindPwdPresenter extends BasePresenterImpl<FindPwdContract.View> implements FindPwdContract.Presenter{

    @Override
    public void sendMsg(String phone) {
        Observable ob = HttpUtil.getInstance().sendResetMsg(phone);
        SubscriberOnNextListener<Object> listener=new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.sendSuccess();
            }

            @Override
            public void onFail(String err) {
                mView.sendFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, (Context) mView,true));
    }

    @Override
    public void resetPassword(String phone, String password, String captcha) {
        Observable ob = HttpUtil.getInstance().resetPassword(phone,password,captcha);
        SubscriberOnNextListener<Object> listener=new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object bean) {
                mView.resetSuccess();
            }

            @Override
            public void onFail(String err) {
                mView.resetFailed(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, (Context) mView,true));
    }
}
