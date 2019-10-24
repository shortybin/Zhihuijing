package com.bibi.wisdom.bean.base;

import java.io.Serializable;

public class BaseBean<T> implements Serializable {


    private int count;
    private int start;
    private int total;
    //状态码
    private int status;
    //返回信息
    private String msg;

    private String title;


    private T data;

    private T subjects;


    private T images;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }


    public T getImages() {
        return images;
    }

    public void setImages(T images) {
        this.images = images;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("title=" + title + " count=" + count + " start=" + start);
        sb.append("msg=").append(msg);
        if (null != data) {
            sb.append(" subjects:").append(data.toString());
        }
//        if (null != images) {
//            sb.append(" images:" + subjects.toString());
//        }
        return sb.toString();
    }
}
