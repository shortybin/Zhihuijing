package com.bibi.wisdom.user.register;

import android.content.Context;

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

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter{


    @Override
    public void sendMsg(String phone) {
        Observable ob = HttpUtil.getInstance().sendRegisterMsg(phone);
        SubscriberOnNextListener<Object> listener=new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object status) {
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
    public void register(String phone, String password, String captcha) {
        Observable ob = HttpUtil.getInstance().registerUser(phone,password,captcha);
        SubscriberOnNextListener<UserLoginBean> listener=new SubscriberOnNextListener<UserLoginBean>() {
            @Override
            public void onNext(UserLoginBean user) {
                mView.registerSuccess(user);
            }

            @Override
            public void onFail(String err) {
                mView.sendFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, (Context) mView,true));
    }
}
