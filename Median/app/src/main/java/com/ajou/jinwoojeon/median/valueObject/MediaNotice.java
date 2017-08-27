package com.ajou.jinwoojeon.median.valueObject;

public final class MediaNotice {

    private final String title;
    private final String contents;
    private final int boardNum;
    private final String date;

    public int getBoardNum() {
        return boardNum;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getDate() {
        return date;
    }

    public MediaNotice(){
        title = "";
        contents = "";
        boardNum = 0;
        date = "";
    }
}
