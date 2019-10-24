package com.bibi.wisdom.bean;

import java.util.List;

public class BannerBean {


    private List<BannersBean> banners;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean {
        /**
         * id : null
         * name : 首页banner
         * url : http://39.97.179.137/file/banners/home/banner01.jpg
         * hrefUrl :
         */

        private String id;
        private String name;
        private String url;
        private String hrefUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHrefUrl() {
            return hrefUrl;
        }

        public void setHrefUrl(String hrefUrl) {
            this.hrefUrl = hrefUrl;
        }
    }
}
