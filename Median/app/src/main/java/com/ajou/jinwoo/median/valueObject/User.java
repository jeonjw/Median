package com.ajou.jinwoo.median.valueObject;

public class User {

    private String userName;
    private String uid;
    private boolean admin;
    private boolean subscribeMediaNotice;
    private boolean subscribeStudentNotice;

    private User() {

    }

    public String getUserName() {
        return userName;
    }

    public String getUid() {
        return uid;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isSubscribeMediaNotice() {

        return subscribeMediaNotice;
    }

    public boolean isSubscribeStudentNotice() {
        return subscribeStudentNotice;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setSubscribeMediaNotice(boolean subscribeMediaNotice) {
        this.subscribeMediaNotice = subscribeMediaNotice;
    }

    public void setSubscribeStudentNotice(boolean subscribeStudentNotice) {
        this.subscribeStudentNotice = subscribeStudentNotice;
    }

    private static class Singleton {
        private static final User INSTANCE = new User();
    }

    public static User getInstance() {
        return Singleton.INSTANCE;
    }

}
