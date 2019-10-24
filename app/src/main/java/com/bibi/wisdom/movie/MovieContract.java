package com.bibi.wisdom.movie;

import com.bibi.wisdom.bean.Subject;
import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MovieContract {
    interface View extends BaseView {
        void getMovieSuccess(List<Subject> listBaseBean);


        void getMovieFailed(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void getMovie(int start,int count);
    }
}
