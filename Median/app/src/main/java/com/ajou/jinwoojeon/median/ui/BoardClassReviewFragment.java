package com.ajou.jinwoojeon.median.ui;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BoardClassReviewFragment extends BaseBoardFragment {

    public BoardClassReviewFragment() {}

    @Override
    public DatabaseReference getRef() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference.child("수업후기");
    }

    @Override
    public String getPostType() {
        return "수업후기";
    }

}
