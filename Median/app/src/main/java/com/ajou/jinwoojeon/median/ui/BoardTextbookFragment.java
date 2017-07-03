package com.ajou.jinwoojeon.median.ui;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BoardTextbookFragment extends BaseBoardFragment {

    public BoardTextbookFragment() {
    }

    @Override
    public DatabaseReference getRef() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference.child("markets");
    }

    @Override
    public String getPostType() {
        return "markets";
    }
}
