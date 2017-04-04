package com.ajou.jinwoo.median;



public class ClassReview {

    private String title;
    private int boardNumber;
    private int commentNumber;

    public String getTitle() {
        return title;
    }

    public int getBoardNumber() {
        return boardNumber;
    }

    public int getCommentNumber() {return commentNumber;}

    public ClassReview(int boardNumber,String title,int commentNumber) {
        this.boardNumber = boardNumber;
        this.title = title;
        this.commentNumber = commentNumber;
    }
}
