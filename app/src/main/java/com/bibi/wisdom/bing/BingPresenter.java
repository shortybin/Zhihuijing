package com.bibi.wisdom.bing;

import android.content.Context;

import com.bibi.wisdom.bean.BingImageBean;
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

public class BingPresenter extends BasePresenterImpl<BingContract.View> implements BingContract.Presenter{

    @Override
    public void getImage(int count) {
        Observable ob = HttpUtil.getInstance().getBingImage( count);
        SubscriberOnNextListener<List<BingImageBean>> listener=new SubscriberOnNextListener<List<BingImageBean>>() {


            @Override
            public void onNext(List<BingImageBean> listBaseBean) {
                mView.getImageSuccess(listBaseBean);
            }

            @Override
            public void onFail(String err) {
                mView.getImageFailed(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, (Context) mView,false));
    }
}
