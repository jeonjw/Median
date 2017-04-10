package com.ajou.jinwoo.median.model;

/**
 * Created by jinwoo on 2017. 4. 6..
 */

public class Info {
    private String name;
    private String email;
    private int telNumber;
    private String location;
    private String profileImage;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getTelNumber() {
        return telNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Info(){

    }
}
