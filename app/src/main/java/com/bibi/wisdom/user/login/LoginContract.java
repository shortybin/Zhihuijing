package com.bibi.wisdom.user.login;

import com.bibi.wisdom.bean.UserLoginBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void loginSuccess(UserLoginBean user);


        void loginFailed(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void login(String username,String password);
    }
}
