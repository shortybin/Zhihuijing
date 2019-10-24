package com.bibi.wisdom.kotlin

import android.content.Context
import com.bibi.wisdom.bean.base.BaseBean
import com.bibi.wisdom.mvp.BasePresenterImpl
import com.bibi.wisdom.network.HttpUtil
import com.bibi.wisdom.network.SubscribeHandler
import com.bibi.wisdom.network.rxjava.ProgressSubscriber
import com.bibi.wisdom.network.rxjava.SubscriberOnNextListener

class WeatherPresenter : BasePresenterImpl<WeatherContract.View>(),WeatherContract.Presenter{


    override fun getWeather(cityId: String) {
//        Observable ob = HttpUtil.getInstance();
        val ob= HttpUtil.getInstance().getWeather(cityId)
        val listener =object : SubscriberOnNextListener<WeatherinfoModel>{
            override fun onNext(t: WeatherinfoModel?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFail(err: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
        SubscribeHandler.observeOn(ob, ProgressSubscriber<BaseBean<WeatherinfoModel>>(listener, mView as Context, false))

    }
}