package com.bibi.wisdom.user.findpwd;

import com.bibi.wisdom.bean.UserLoginBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FindPwdContract {
    interface View extends BaseView {

        void sendSuccess();


        void sendFail(String message);


        void resetSuccess();


        void resetFailed(String message);
    }

    interface  Presenter extends BasePresenter<View> {

        void sendMsg(String phone);


        void resetPassword(String phone, String password, String captcha);
    }
}
