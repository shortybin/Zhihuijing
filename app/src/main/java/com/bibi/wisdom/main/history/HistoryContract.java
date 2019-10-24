package com.bibi.wisdom.main.history;

import android.content.Context;

import com.bibi.wisdom.bean.BannerBean;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HistoryContract {
    interface View extends BaseView {
        void getHistorySuccess(HistoryBean bean);


        void getHistoryFail(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void getHistoryList();
    }
}
