package com.ajou.jinwoo.median.valueObject;

public class PhotoAlbum {
    private String mainImageUrl;
    private String detail;

    public PhotoAlbum(String url, String detail) {
        this.mainImageUrl = url;
        this.detail = detail;
    }

    public PhotoAlbum() {
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public String getDetail() {
        return detail;
    }
}
