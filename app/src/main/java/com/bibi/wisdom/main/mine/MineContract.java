package com.bibi.wisdom.main.mine;

import android.content.Context;

import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineContract {
    interface View extends BaseView {
        void logoutSuccess();


        void logoutFail(String message);

        void delectUserSuccess();

        void delectUserFail(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void logout();

        void delectUser();
    }
}
