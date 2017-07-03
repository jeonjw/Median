package com.ajou.jinwoojeon.median.ui;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BoardQAFragment extends BaseBoardFragment {

    @Override
    public DatabaseReference getRef() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference.child("questions");
    }

    @Override
    public String getPostType() {
        return "questions";
    }

    public BoardQAFragment() {
    }
}