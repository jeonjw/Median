package com.ajou.jinwoojeon.median.valueObject;

public class Comment {

    private final String author;
    private final String text;
    private final long timeStamp;
    private final String uid;

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getUid() {
        return uid;
    }

    public Comment() {
        this.author="";
        this.text="";
        this.timeStamp=0;
        this.uid="";
    }

    private Comment(String author, String text) {
        this.author = author;
        this.text = text;
        this.timeStamp = timeStamp();
        this.uid = User.getInstance().getUid();
    }

    private long timeStamp() { //static 바꾸기
        return System.currentTimeMillis();
    }

    public static Comment newComment(String userName, String message) {
        return new Comment(userName, message);
    }

}
