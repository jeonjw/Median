package com.ajou.jinwoo.median.model;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class StudentNotice {

    private String title;
    private String contents;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }



    public StudentNotice(){

    }
    public StudentNotice(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("contents", contents);
        return result;
    }
}
