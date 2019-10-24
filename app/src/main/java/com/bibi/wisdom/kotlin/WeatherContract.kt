package com.bibi.wisdom.kotlin

import com.bibi.wisdom.mvp.BasePresenter
import com.bibi.wisdom.mvp.BaseView

class WeatherContract  {
    interface View : BaseView{
        fun getWeatherSucdess(weatherinfoModel: WeatherinfoModel)

        fun getWeatherFailed(msg:String)
    }

    interface Presenter:BasePresenter<View>{
        fun getWeather(cityId:String)
    }
}