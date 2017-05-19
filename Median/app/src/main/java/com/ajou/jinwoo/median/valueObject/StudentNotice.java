package com.ajou.jinwoo.median.valueObject;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentNotice {

    private String author;
    private String title;
    private String contents;
    private String timeStamp;
    private String authorUid;
    private int commentCount;

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

    public String getAuthorUid() {
        return authorUid;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public StudentNotice() {

    }

    public StudentNotice(String author, String title, String contents,int commentCount) {
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.timeStamp = timeStamp();
        this.authorUid = User.getInstance().getUid();
        this.commentCount = commentCount;
    }

    private static String timeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd.", Locale.KOREA);

        return dateFormat.format(date);
    }

    public static StudentNotice newNotice(String userName, String title, String contents,int commentCount) {
        return new StudentNotice(userName, title, contents, commentCount);
    }

//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("author", author);
//        result.put("contents", contents);
//        result.put("title", title);
//        result.put("timeStamp", timeStamp);
//        result.put("commentCount", commentCount);
//        return result;
//    }
}
