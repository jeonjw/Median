package com.ajou.jinwoo.median.valueObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Comment {

    private String author;
    private String text;
    private String timeStamp;

    public String getAuthor() {
        return author;
    }
    public String getText() {
        return text;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public Comment(){
    }

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
        this.timeStamp = timeStamp();
    }
    private static String timeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd.", Locale.KOREA);

        return dateFormat.format(date);
    }

    public static Comment newComment(String userName,String message) {
        return new Comment(userName, message);
    }

}
