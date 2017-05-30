package com.ajou.jinwoo.median.ui;

import com.ajou.jinwoo.median.BaseBoardFragment;
import com.google.firebase.database.DatabaseReference;


public class BoardTextbookFragment extends BaseBoardFragment {

    public BoardTextbookFragment() {
    }

    @Override
    public DatabaseReference getRef(DatabaseReference databaseReference) {
        return databaseReference.child("교재장터");
    }

    @Override
    public String getPostType() {
        return "교재장터";
    }
}
