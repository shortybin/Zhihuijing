package com.bibi.wisdom.bean;

/**
 * Created by shortybin
 * on 2020/5/12
 */
public class CityBean {

    private String city;
    private String second_city;
    private String lat;
    private String lon;

    public String getCity() {
        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSecond_city() {
        return second_city == null ? "" : second_city;
    }

    public void setSecond_city(String second_city) {
        this.second_city = second_city;
    }

    public String getLat() {
        return lat == null ? "" : lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon == null ? "" : lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
