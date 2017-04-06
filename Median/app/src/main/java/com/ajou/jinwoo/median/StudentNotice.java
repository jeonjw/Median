package com.ajou.jinwoo.median;


public class StudentNotice {

    private String title;
    private String contents;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }



    public StudentNotice(){

    }
    public StudentNotice(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
