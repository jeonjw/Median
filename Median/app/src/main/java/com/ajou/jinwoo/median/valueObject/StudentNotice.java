package com.ajou.jinwoo.median.valueObject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StudentNotice {

    private String author;
    private String title;
    private String contents;
    private String timeStamp;
    private String authorUid;
    private int commentCount;
    private List<String> urlList;

    public List<String> getUrlList() {
        return urlList;
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

    public String getAuthorUid() {
        return authorUid;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public StudentNotice() {
        this("","","",0,new ArrayList<String>());

    }

    public StudentNotice(String author, String title, String contents,int commentCount) {
        this(author,title,contents,commentCount,null);
    }

    public StudentNotice(String author, String title, String contents,int commentCount,List<String> urlList) {
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.timeStamp = timeStamp();
        this.authorUid = User.getInstance().getUid();
        this.commentCount = commentCount;
        this.urlList = urlList;
    }

    private static String timeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd.", Locale.KOREA);

        return dateFormat.format(date);
    }

    public static StudentNotice newNotice(String userName, String title, String contents,int commentCount) {
        return new StudentNotice(userName, title, contents, commentCount);
    }


}
