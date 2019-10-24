package com.bibi.wisdom.bean;

public class DeviceInfoBean {


    /**
     * response : {"success":"1","id":"G8324956000013","online":"1","status":"0,0","shutmemory":"0,0","delayoff":"0,0","customdata":""}
     * eqstatus : 0
     * online : 1
     */

    private String response;
    private int eqstatus;
    private String online;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getEqstatus() {
        return eqstatus;
    }

    public void setEqstatus(int eqstatus) {
        this.eqstatus = eqstatus;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}
