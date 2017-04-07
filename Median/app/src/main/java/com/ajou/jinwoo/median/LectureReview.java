package com.ajou.jinwoo.median;



public class LectureReview {

    private String title;
    private String contents;

    public LectureReview(){

    }

    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }

    public LectureReview(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
