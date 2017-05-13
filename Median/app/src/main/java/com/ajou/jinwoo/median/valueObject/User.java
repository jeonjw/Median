package com.ajou.jinwoo.median.valueObject;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private String userName;
    private String userEmail;


    public String getUserName() {
        return userName;
    }
    private User() {

    }


    private static class Singleton {
        private static final User INSTANCE = new User();
    }

    public static User getInstance() {
        return Singleton.INSTANCE;
    }
}
