package com.bibi.wisdom.user.login;

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

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    @Override
    public void login(String username, String password) {

        Observable ob = HttpUtil.getInstance().loginByPwd2(username,password);
        SubscriberOnNextListener<UserLoginBean> listener=new SubscriberOnNextListener<UserLoginBean>() {
            @Override
            public void onNext(UserLoginBean listBaseBean) {
                mView.loginSuccess(listBaseBean);
            }

            @Override
            public void onFail(String err) {
                mView.loginFailed(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, (Context) mView,false));
    }
}
