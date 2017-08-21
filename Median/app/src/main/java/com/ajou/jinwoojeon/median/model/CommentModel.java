package com.ajou.jinwoojeon.median.model;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.valueObject.Comment;
import com.ajou.jinwoojeon.median.viewholder.CommentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CommentModel {
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    private String dataRefKey;
    private int commentCount;
    private OnDataChangedListener onDataChangedListener;

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }

    public CommentModel(final String dataRef, final String postType) {

        this.dataRefKey = dataRef;
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

    public FirebaseRecyclerAdapter<Comment, CommentViewHolder> loadCommentList() {

        return new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(
                Comment.class,
                R.layout.list_item_comment,
                CommentViewHolder.class,
                databaseReference.child("comments").child(dataRefKey)) {
            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {
                String commentKey = getRef(position).getKey();
                viewHolder.bindComment(model, dataRefKey, commentKey);
            }

        };
    }

    public void writeComment(String author, String message) {
        databaseReference.child("comments").child(dataRefKey).push().setValue(Comment.newComment(author, message));
    }


    public void removeListener() {
        databaseReference.child("comments").child(dataRefKey).removeEventListener(valueEventListener);
    }
}
