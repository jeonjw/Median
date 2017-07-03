package com.ajou.jinwoojeon.median.ui;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BoardClassReviewFragment extends BaseBoardFragment {

    public BoardClassReviewFragment() {

    }

    @Override
    public DatabaseReference getRef() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference.child("reviews");
    }

    @Override
    public String getPostType() {
        return "reviews";
    }
}
