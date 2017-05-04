package com.ajou.jinwoo.median.model;



public class Post {

    private String title;
    private String contents;

    public Post(){

    }

    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }

    public Post(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
