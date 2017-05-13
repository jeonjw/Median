package com.ajou.jinwoo.median.valueObject;

/**
 * Created by samsung on 2017-04-08.
 */

public class Textbook {

    private String title;
    private String contents;

    public Textbook(){

    }

    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }

    public Textbook(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
