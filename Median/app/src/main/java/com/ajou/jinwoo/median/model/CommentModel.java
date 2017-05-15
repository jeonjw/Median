package com.ajou.jinwoo.median.model;

import com.ajou.jinwoo.median.valueObject.Comment;
import com.ajou.jinwoo.median.valueObject.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CommentModel {
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    private String dataRefKey;
    private String postType;
    private int commentCount;
    private OnDataChangedListener onDataChangedListener;

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }


    public CommentModel(String dataRef, String post) {

        this.dataRefKey = dataRef;
        this.postType = post;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        valueEventListener = databaseReference.child("comments").child(dataRefKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                commentCount = (int) dataSnapshot.getChildrenCount();
                databaseReference.child(postType).child(dataRefKey).child("commentCount").setValue(commentCount);

                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public void writeComment(String message) {
        User user = User.getInstance();
        databaseReference.child("comments").child(dataRefKey).push().setValue(Comment.newComment(user.getUserName(), message));
    }
    public void removeListener(){
        databaseReference.child("comments").child(dataRefKey).removeEventListener(valueEventListener);
    }

    public int getCommentCount() {
        return commentCount;
    }

}
