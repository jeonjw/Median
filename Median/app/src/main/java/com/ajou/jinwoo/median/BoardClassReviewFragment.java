package com.ajou.jinwoo.median;

import com.google.firebase.database.DatabaseReference;


public class BoardClassReviewFragment extends BaseBoardFragment {

    public BoardClassReviewFragment() {}

    @Override
    public DatabaseReference getRef(DatabaseReference databaseReference) {
        return databaseReference.child("수업후기");
    }

    @Override
    public String getPostType() {
        return "수업후기";
    }

}
