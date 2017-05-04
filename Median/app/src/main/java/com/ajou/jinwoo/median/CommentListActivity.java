package com.ajou.jinwoo.median;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ajou.jinwoo.median.model.Comment;
import com.ajou.jinwoo.median.model.User;
import com.ajou.jinwoo.median.viewholder.CommentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CommentListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private String dataRefKey;
    private View popView;
    private PopupWindow popWindow;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        popView = getLayoutInflater().inflate(R.layout.popup_comment_list, null);

        recyclerView = (RecyclerView) popView.findViewById(R.id.comment_recycler_view);
        final EditText commentEdit = (EditText) popView.findViewById(R.id.comment_edit);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        dataRefKey = getIntent().getExtras().getString("POST_KEY");

        commentEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    User user =User.getInstance();
                    Comment comment = new Comment(user.getUserName(),commentEdit.getText().toString());
                    mDatabase.child("comments").child(dataRefKey).push().setValue(comment);

                    commentEdit.setText("");
                    return true;
                }
                return false;
            }
        });

        setCommentList();
        popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        // set a background drawable with rounders corners
        popWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background, null));
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);

    }


    private void setCommentList() {
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mManager.scrollToPositionWithOffset(0, 0);
        recyclerView.setLayoutManager(mManager);


        FirebaseRecyclerAdapter<Comment, CommentViewHolder> mAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(Comment.class, R.layout.list_item_comment,
                CommentViewHolder.class, mDatabase.child("comments").child(dataRefKey)) {
            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {
                viewHolder.bindComment(model);
            }

        };

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            popWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);
        }
    }
}