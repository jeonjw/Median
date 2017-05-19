package com.ajou.jinwoo.median.model;

import android.content.Context;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.Post;
import com.ajou.jinwoo.median.valueObject.StudentNotice;
import com.ajou.jinwoo.median.valueObject.User;
import com.ajou.jinwoo.median.viewholder.StudentNoticeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentNoticeModel {
    private DatabaseReference databaseReference;
    private OnDataChangedListener onDataChangedListener;

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }


    public StudentNoticeModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("student_notice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public void writeNotice(String title, String contents) {
        databaseReference.child("student_notice").
                push().setValue(StudentNotice.newNotice(User.getInstance().getUserName(), title, contents, 0));
        new NotificationPostModel("학생회 공지가 등록됬습니다", title).execute();
    }

    public void correctNotice(String title, String contents, String postKey, int commentCount) {
        databaseReference.child("student_notice").
                child(postKey).setValue(Post.newPost(User.getInstance().getUserName(), title, contents, commentCount));

    }

    public FirebaseRecyclerAdapter setAdapter(Query query, final Context context) {
//        progressDialog.show();

        FirebaseRecyclerAdapter<StudentNotice, StudentNoticeViewHolder> adapter = new FirebaseRecyclerAdapter<StudentNotice, StudentNoticeViewHolder>(StudentNotice.class, R.layout.list_item_student_notice,
                StudentNoticeViewHolder.class, query) {
            @Override
            protected void populateViewHolder(StudentNoticeViewHolder viewHolder, StudentNotice model, int position) {
                DatabaseReference postRef = getRef(position);
                String postKey = postRef.getKey();
                viewHolder.bindNotice(model, context, postKey);
//                progressDialog.dismiss();
            }
        };

        return adapter;
    }
}
