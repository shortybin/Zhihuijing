package com.bibi.wisdom.main.maintain;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bibi.wisdom.bean.BannerBean;
import com.bibi.wisdom.bean.NoticeBean;
import com.bibi.wisdom.mvp.BasePresenterImpl;
import com.bibi.wisdom.network.HttpUtil;
import com.bibi.wisdom.network.SubscribeHandler;
import com.bibi.wisdom.network.rxjava.ProgressSubscriber;
import com.bibi.wisdom.network.rxjava.SubscriberOnNextListener;

import io.reactivex.Observable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MaintainPresenter extends BasePresenterImpl<MaintainContract.View> implements MaintainContract.Presenter{

    @Override
    public void getNoticeList(int type) {
        Observable ob = HttpUtil.getInstance().getNoticeList(type);
        SubscriberOnNextListener<NoticeBean> listener=new SubscriberOnNextListener<NoticeBean>() {
            @Override
            public void onNext(NoticeBean bean) {
                mView.getNoticeSuccess(bean);
            }

            @Override
            public void onFail(String err) {
                mView.getNoticeFail(err);
            }
        };
        SubscribeHandler.observeOn(ob,new ProgressSubscriber(listener, mView.getContext(),true));
    }
}
