package com.ajou.jinwoo.median.valueObject;

/**
 * Created by samsung on 2017-04-08.
 */

public class QA  {

    private String title;
    private String contents;

    public QA(){

    }

    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }

    public QA(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
