package com.bibi.wisdom.bean;

import java.util.List;

public class MaintainListBean {


    private List<UserContactListBean> user_contact_list;

    public List<UserContactListBean> getUser_contact_list() {
        return user_contact_list;
    }

    public void setUser_contact_list(List<UserContactListBean> user_contact_list) {
        this.user_contact_list = user_contact_list;
    }

    public static class UserContactListBean {
        /**
         * id : 4cc3d531-84e5-11e9-a563-00163e064b82
         * userId : 28c19b5f-8471-11e9-a563-00163e064b82
         * type : 1
         * address : 陕西省宝鸡市岐山县
         * name : 王先生
         * tel : 010-12345678
         * phone : 13700000001
         * status : 1
         */

        private String id;
        private String userId;
        private int type;
        private String address;
        private String name;
        private String tel;
        private String phone;
        private int status;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
