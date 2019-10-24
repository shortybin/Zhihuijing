package com.bibi.wisdom.main.tool;

import android.content.Context;

import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ToolContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
