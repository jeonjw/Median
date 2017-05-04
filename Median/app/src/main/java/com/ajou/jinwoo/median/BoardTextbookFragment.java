package com.ajou.jinwoo.median;

import com.google.firebase.database.DatabaseReference;


public class BoardTextbookFragment extends BaseBoardFragment {

    public BoardTextbookFragment() {
    }

    @Override
    public DatabaseReference getRef(DatabaseReference databaseReference) {
        return databaseReference.child("교재장터");
    }
}
