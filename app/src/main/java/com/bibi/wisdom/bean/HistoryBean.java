package com.bibi.wisdom.bean;

import java.util.List;

public class HistoryBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : d18d620d-c321-11e9-876b-00163e2eda16
         * productId : 3291bad8-a51d-11e9-876b-00163e2eda16
         * productCode : G8324956000013
         * productName : 13 井测试绑定的的点点滴滴
         * status : 0
         * createTime : 1566288563000
         * creater : 256fc289-a127-11e9-876b-00163e2eda16
         * updateTime : 1566288724000
         * updater : null
         * price : 100
         * timeLong : 3
         * sumPrice : 5
         * sumPriceStr : 5.00
         * timeLongFormat : 0:3
         */

        private String id;
        private String productId;
        private String productCode;
        private String productName;
        private int status;
        private long createTime;
        private String creater;
        private long updateTime;
        private Object updater;
        private int price;
        private int timeLong;
        private float sumPrice;
        private String sumPriceStr;
        private String timeLongFormat;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreater() {
            return creater;
        }

        public void setCreater(String creater) {
            this.creater = creater;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUpdater() {
            return updater;
        }

        public void setUpdater(Object updater) {
            this.updater = updater;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getTimeLong() {
            return timeLong;
        }

        public void setTimeLong(int timeLong) {
            this.timeLong = timeLong;
        }

        public float getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(float sumPrice) {
            this.sumPrice = sumPrice;
        }

        public String getSumPriceStr() {
            return sumPriceStr;
        }

        public void setSumPriceStr(String sumPriceStr) {
            this.sumPriceStr = sumPriceStr;
        }

        public String getTimeLongFormat() {
            return timeLongFormat;
        }

        public void setTimeLongFormat(String timeLongFormat) {
            this.timeLongFormat = timeLongFormat;
        }
    }
}
