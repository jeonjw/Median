package com.ajou.jinwoo.median.model;


import com.google.firebase.database.Exclude;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Post {

    private String title;
    private String contents;
    private String author;
    private String timeStamp;
    private int commentCount;

    public Post(){

    }

    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }
    public String getAuthor() {
        return author;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public Post(String author, String title, String contents) {
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.timeStamp = timeStamp();
        commentCount = 0;
    }

    private static String timeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd.", Locale.KOREA);

        return dateFormat.format(date);
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("author", author);
        result.put("contents", contents);
        result.put("title", title);
        result.put("timeStamp", timeStamp);
        result.put("commentCount", commentCount);
        return result;
    }
}
