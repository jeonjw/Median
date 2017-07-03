package com.ajou.jinwoojeon.median.ui;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentNoticeFragment extends BaseBoardFragment {


    public StudentNoticeFragment() {
    }


    @Override
    public DatabaseReference getRef() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference.child("student_notice");
    }

    @Override
    public String getPostType() {
        return "student_notice";
    }


}
