package com.bibi.wisdom.user.register;

import com.bibi.wisdom.bean.UserLoginBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterContract {
    interface View extends BaseView {

        void sendSuccess();

        void sendFail(String message);


        void registerSuccess(UserLoginBean user);


        void registerFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void sendMsg(String phone);


        void register(String phone, String password, String captcha);
    }
}
