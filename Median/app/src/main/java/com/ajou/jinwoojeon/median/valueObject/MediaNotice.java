package com.ajou.jinwoojeon.median.valueObject;

public final class MediaNotice {

    private final String title;
    private final String contents;
    private final int boardNum;

    public int getBoardNum() {
        return boardNum;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public MediaNotice(){
        title = "";
        contents = "";
        boardNum = 0;
    }
}
