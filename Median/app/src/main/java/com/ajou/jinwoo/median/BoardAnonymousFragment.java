package com.ajou.jinwoo.median;

import com.google.firebase.database.DatabaseReference;

public class BoardAnonymousFragment extends BaseBoardFragment {

    public BoardAnonymousFragment() {
    }

    @Override
    public DatabaseReference getRef(DatabaseReference databaseReference) {
        return databaseReference.child("익명자유");
    }

    @Override
    public String getPostType() {
        return "익명자유";
    }
}
