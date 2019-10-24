package com.bibi.wisdom.bean;

public class UserLoginBean {

    /**
     * accountInfo : {"id":"257182d9-a127-11e9-876b-00163e2eda16","userId":"256fc289-a127-11e9-876b-00163e2eda16","code":"W190708102151153","phone":"13810231938","email":null,"pwd":null,"status":1,"token":"b6a4bcefd8fc08d23354a20cd89c3569","createTime":1562552512000,"updateTime":1567180750000}
     * userInfo : {"id":"256fc289-a127-11e9-876b-00163e2eda16","name":null,"icon":null,"type":1,"status":1,"createTime":1562552512000,"updateTime":null}
     */

    private AccountInfoBean accountInfo;
    private UserInfoBean userInfo;

    public AccountInfoBean getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoBean accountInfo) {
        this.accountInfo = accountInfo;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class AccountInfoBean {
        /**
         * id : 257182d9-a127-11e9-876b-00163e2eda16
         * userId : 256fc289-a127-11e9-876b-00163e2eda16
         * code : W190708102151153
         * phone : 13810231938
         * email : null
         * pwd : null
         * status : 1
         * token : b6a4bcefd8fc08d23354a20cd89c3569
         * createTime : 1562552512000
         * updateTime : 1567180750000
         */

        private String id;
        private String userId;
        private String code;
        private String phone;
        private String email;
        private String pwd;
        private int status;
        private String token;
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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
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

    public static class UserInfoBean {
        /**
         * id : 256fc289-a127-11e9-876b-00163e2eda16
         * name : null
         * icon : null
         * type : 1
         * status : 1
         * createTime : 1562552512000
         * updateTime : null
         */

        private String id;
        private String name;
        private String icon;
        private int type;
        private int status;
        private long createTime;
        private long updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
