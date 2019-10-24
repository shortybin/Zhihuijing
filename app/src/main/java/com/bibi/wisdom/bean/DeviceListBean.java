package com.bibi.wisdom.bean;

import java.io.Serializable;
import java.util.List;

public class DeviceListBean implements Serializable {


    private List<UserproductlistBean> userproductlist;

    public List<UserproductlistBean> getUserproductlist() {
        return userproductlist;
    }

    public void setUserproductlist(List<UserproductlistBean> userproductlist) {
        this.userproductlist = userproductlist;
    }

    public static class UserproductlistBean implements Serializable {
        /**
         * id : f786e964-aa83-11e9-876b-00163e2eda16
         * userId : 256fc289-a127-11e9-876b-00163e2eda16
         * productId : 3291bad8-a51d-11e9-876b-00163e2eda16
         * productCode : G8324956000013
         * productName : 13 井测试绑定的的点点滴滴
         * price : 100
         * timeUnit : null
         * status : 1
         * createTime : 1563595111000
         * updateTime : null
         */

        private String id;
        private String userId;
        private String productId;
        private String productCode;
        private String productName;
        private int price;
        private String timeUnit;
        private int status;
        private long createTime;
        private long updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getTimeUnit() {
            return timeUnit;
        }

        public void setTimeUnit(String timeUnit) {
            this.timeUnit = timeUnit;
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

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
