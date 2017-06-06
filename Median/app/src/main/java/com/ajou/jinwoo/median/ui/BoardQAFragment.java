package com.ajou.jinwoo.median.ui;

import com.google.firebase.database.DatabaseReference;


public class BoardQAFragment extends BaseBoardFragment {

    @Override
    public DatabaseReference getRef(DatabaseReference databaseReference) {
        return databaseReference.child("질문답변");
    }

    @Override
    public String getPostType() {
        return "질문답변";
    }

    public BoardQAFragment() {
    }
}