package com.bibi.wisdom.bing;

import com.bibi.wisdom.bean.BingImageBean;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BingContract {
    interface View extends BaseView {
        void getImageSuccess(List<BingImageBean> list);


        void getImageFailed(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void getImage(int count);
    }
}
