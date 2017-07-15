package com.ajou.jinwoojeon.median.valueObject;

public final class Info {
    private final String name;
    private final String email;
    private final int telNumber;
    private final String location;
    private final String profileImage;

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

    public Info(){
        name = "";
        email = "";
        telNumber = 0;
        location = "";
        profileImage = "";
    }
}
