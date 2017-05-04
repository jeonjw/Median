package com.ajou.jinwoo.median.model;

/**
 * Created by jinwoo on 2017. 5. 4..
 */

public class Comment {

    private String author;
    private String text;

    public String getAuthor() {
        return author;
    }
    public String getText() {
        return text;
    }
    public Comment(){

    }

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }




}
