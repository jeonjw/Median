package com.ajou.jinwoo.median;

import com.google.firebase.database.DatabaseReference;


public class BoardQAFragment extends BaseBoardFragment {

    @Override
    public DatabaseReference getRef(DatabaseReference databaseReference) {
        return databaseReference.child("질문답변");
    }

    public BoardQAFragment() {
    }
}