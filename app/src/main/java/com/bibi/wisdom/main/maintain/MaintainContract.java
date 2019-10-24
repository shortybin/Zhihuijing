package com.bibi.wisdom.main.maintain;

import android.content.Context;

import com.bibi.wisdom.bean.NoticeBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MaintainContract {
    interface View extends BaseView {
        void getNoticeSuccess(NoticeBean bean);

        void getNoticeFail(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void getNoticeList(int type);
    }
}
