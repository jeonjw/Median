package com.ajou.jinwoo.median;


public class StudentNotice {

    private String title;
    private String contents;
    private String date;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getDate() {
        return date;
    }

    public StudentNotice(){

    }
    public StudentNotice(String title, String contents,String date) {
        this.title = title;
        this.contents = contents;
        this.date=date;
    }
}
