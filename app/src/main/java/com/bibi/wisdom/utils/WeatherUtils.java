package com.bibi.wisdom.utils;

import android.widget.ImageView;

import com.bibi.wisdom.R;

public class WeatherUtils {

    public static void setTodayWeatherIcon(ImageView imageView,String conditionId){
        int id=Integer.valueOf(conditionId);
        if (id>=1&&id<=7){
            imageView.setImageResource(R.drawable.weather0);
            return;
        }
        if (id>=8&&id<=12){
            imageView.setImageResource(R.drawable.weather1);
            return;
        }
        if (id==13||id==14||id==36||id==85){
            imageView.setImageResource(R.drawable.weather2);
            return;
        }
        if (id>=15&&id<=23||id==86){
            imageView.setImageResource(R.drawable.weather3);
            return;
        }
        if (id==24||id==25){
            imageView.setImageResource(R.drawable.weather13);
            return;
        }
        if (id==26||id==27||id==28){
            imageView.setImageResource(R.drawable.weather18);
            return;
        }
        if (id==29||id==33){
            imageView.setImageResource(R.drawable.weather20);
            return;
        }
        if (id==30||id==31||id==32){
            imageView.setImageResource(R.drawable.weather29);
            return;
        }
        if (id==34||id==35){
            imageView.setImageResource(R.drawable.weather45);
            return;
        }
        if (id>=37&&id<=43){
            imageView.setImageResource(R.drawable.weather4);
            return;
        }
        if (id>=44&&id<=48){
            imageView.setImageResource(R.drawable.weather5);
            return;
        }
        if (id==49||id==50){
            imageView.setImageResource(R.drawable.weather6);
            return;
        }
        if (id==51||id==52||id==66||id==91){
            imageView.setImageResource(R.drawable.weather7);
            return;
        }
        if (id==53||id==67||id==78){
            imageView.setImageResource(R.drawable.weather8);
            return;
        }
        if (id==54||id==68||id==92){
            imageView.setImageResource(R.drawable.weather9);
            return;
        }
        if (id==55||id==56||id==57||id==69||id==70||id==93){
            imageView.setImageResource(R.drawable.weather10);
            return;
        }
        if (id==58||id==59||id==71||id==72||id==73){
            imageView.setImageResource(R.drawable.weather14);
            return;
        }
        if (id==60||id==61||id==74||id==75||id==77||id==94){
            imageView.setImageResource(R.drawable.weather15);
            return;
        }
        if (id==62||id==76){
            imageView.setImageResource(R.drawable.weather16);
            return;
        }
        if (id==63){
            imageView.setImageResource(R.drawable.weather17);
            return;
        }
        if (id==64||id==65){
            imageView.setImageResource(R.drawable.weather19);
            return;
        }
        if (id==79){
            imageView.setImageResource(R.drawable.weather45);
            return;
        }
        if (id==80||id==81||id==82){
            imageView.setImageResource(R.drawable.weather1);
            return;
        }
        if (id==83||id==84){
            imageView.setImageResource(R.drawable.weather18);
            return;
        }
        if (id>=87&&id<=90){
            imageView.setImageResource(R.drawable.weather4);
            return;
        }
    }

    public static void setTommorwWeatherIcon(ImageView imageView,String conditionId){
        int id=Integer.valueOf(conditionId);
        if (id==0){
            imageView.setImageResource(R.drawable.weather0);
            return;
        }
        if (id==1){
            imageView.setImageResource(R.drawable.weather1);
            return;
        }
        if (id==2){
            imageView.setImageResource(R.drawable.weather2);
            return;
        }
        if (id==3){
            imageView.setImageResource(R.drawable.weather3);
            return;
        }
        if (id==4){
            imageView.setImageResource(R.drawable.weather4);
            return;
        }
        if (id==5){
            imageView.setImageResource(R.drawable.weather5);
            return;
        }
        if (id==7){
            imageView.setImageResource(R.drawable.weather7);
            return;
        }
        if (id==8){
            imageView.setImageResource(R.drawable.weather8);
            return;
        }
        if (id==9){
            imageView.setImageResource(R.drawable.weather9);
            return;
        }
        if (id==10){
            imageView.setImageResource(R.drawable.weather10);
            return;
        }
        if (id==13){
            imageView.setImageResource(R.drawable.weather13);
            return;
        }
        if (id==14){
            imageView.setImageResource(R.drawable.weather14);
            return;
        }
        if (id==15){
            imageView.setImageResource(R.drawable.weather15);
            return;
        }
        if (id==16){
            imageView.setImageResource(R.drawable.weather16);
            return;
        }
        if (id==17){
            imageView.setImageResource(R.drawable.weather17);
            return;
        }
        if (id==18){
            imageView.setImageResource(R.drawable.weather18);
            return;
        }
        if (id==19){
            imageView.setImageResource(R.drawable.weather19);
            return;
        }
        if (id==20){
            imageView.setImageResource(R.drawable.weather20);
            return;
        }
        if (id==29){
            imageView.setImageResource(R.drawable.weather29);
            return;
        }
        if (id==45){
            imageView.setImageResource(R.drawable.weather45);
            return;
        }
    }
}
