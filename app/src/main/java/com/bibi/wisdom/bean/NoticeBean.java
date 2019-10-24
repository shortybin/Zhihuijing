package com.bibi.wisdom.bean;

import java.util.List;

public class NoticeBean {

    private List<NoticeListBean> notice_list;

    public List<NoticeListBean> getNotice_list() {
        return notice_list;
    }

    public void setNotice_list(List<NoticeListBean> notice_list) {
        this.notice_list = notice_list;
    }

    public static class NoticeListBean {
        /**
         * id : b058dd0b-8602-11e9-a563-00163e064b82
         * userId : null
         * type : 1
         * title : 公告测试信息标题
         * content : 公告测试信内容公告测试信内容公告测试信内容公告测试信内容公告测试信内容公告测试信内容
         */

        private String id;
        private Object userId;
        private int type;
        private String title;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
