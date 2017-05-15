package com.ajou.jinwoo.median.valueObject;

public class Anonymous {

    private String title;
    private String contents;

    public Anonymous(){

    }

    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }

    public Anonymous(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
