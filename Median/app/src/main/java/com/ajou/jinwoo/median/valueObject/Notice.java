package com.ajou.jinwoo.median.valueObject;

public class Notice {

    private String title;
    private String contents;
    private int boardNum;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public Notice(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
    public Notice(){

    }


}
