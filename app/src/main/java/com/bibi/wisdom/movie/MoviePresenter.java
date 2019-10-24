package com.bibi.wisdom.movie;

import android.content.Context;

import com.bibi.wisdom.bean.Subject;
import com.bibi.wisdom.mvp.BasePresenterImpl;
import com.bibi.wisdom.network.HttpUtil;
import com.bibi.wisdom.network.SubscribeHandler;
import com.bibi.wisdom.network.rxjava.ProgressSubscriber;
import com.bibi.wisdom.network.rxjava.SubscriberOnNextListener;

import java.util.List;

import io.reactivex.Observable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MoviePresenter extends BasePresenterImpl<MovieContract.View> implements MovieContract.Presenter{


    @Override
    public void getMovie(int start, int count) {
        Observable ob = HttpUtil.getInstance().getTopMovies(0, 100);
        SubscriberOnNextListener<List<Subject>> listener=new SubscriberOnNextListener<List<Subject>>() {


            @Override
            public void onNext(List<Subject> listBaseBean) {
                mView.getMovieSuccess(listBaseBean);
            }

            @Override
            public void onFail(String err) {
                mView.getMovieFailed(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, (Context) mView,false));

    }
}
