package com.ajou.jinwoo.median;

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

    public int getBoardNum() {
        return boardNum;
    }

    public Notice(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


}
