package com.ajou.jinwoo.median.valueObject;

public class User {

    private String userName;
    private String userEmail;
    private String uid;

    private User() {

    }

    public String getUserName() {
        return userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private static class Singleton {
        private static final User INSTANCE = new User();
    }

    public static User getInstance() {
        return Singleton.INSTANCE;
    }
}
