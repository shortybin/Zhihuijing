package com.bibi.wisdom.kotlin

import android.os.Bundle
import com.bibi.wisdom.R
import com.bibi.wisdom.mvp.MVPBaseActivity
import com.bibi.wisdom.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : MVPBaseActivity<WeatherContract.View, WeatherPresenter>(), WeatherContract.View{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        button.setOnClickListener {
            mPresenter.getWeather("101190201")
        };
    }


    override fun getWeatherSucdess(weatherinfoModel: WeatherinfoModel) {
        ToastUtil.showToast(this,"成功")
    }

    override fun getWeatherFailed(msg: String) {

    }

}