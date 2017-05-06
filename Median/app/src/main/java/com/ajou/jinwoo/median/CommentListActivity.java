package com.ajou.jinwoo.median;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.ajou.jinwoo.median.model.Comment;
import com.ajou.jinwoo.median.model.User;
import com.ajou.jinwoo.median.viewholder.CommentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CommentListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private String dataRefKey;
    private int commentCount;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_comment_list);

        recyclerView = (RecyclerView) findViewById(R.id.comment_recycler_view);

        LinearLayoutManager mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);

        final EditText commentEdit = (EditText) findViewById(R.id.comment_edit);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        dataRefKey = getIntent().getExtras().getString("POST_KEY");

        commentEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    User user = User.getInstance();
                    Comment comment = new Comment(user.getUserName(), commentEdit.getText().toString());
                    mDatabase.child("comments").child(dataRefKey).push().setValue(comment);
                    mDatabase.child("student_notice").child(dataRefKey).child("commentCount").setValue(commentCount + 1);

                    commentEdit.setText("");
                    return true;
                }
                return false;
            }
        });

        setCommentList();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change, R.anim.slide_down_anim);
    }

    private void setCommentList() {

        FirebaseRecyclerAdapter<Comment, CommentViewHolder> mAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(Comment.class, R.layout.list_item_comment,
                CommentViewHolder.class, mDatabase.child("comments").child(dataRefKey)) {
            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {
                viewHolder.bindComment(model);
            }

        };

        recyclerView.setAdapter(mAdapter);

        mDatabase.child("comments").child(dataRefKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                commentCount = (int) dataSnapshot.getChildrenCount();
                recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


}
//댓글 리스트 제일 아래로 스크롤하기.
//꾸미기