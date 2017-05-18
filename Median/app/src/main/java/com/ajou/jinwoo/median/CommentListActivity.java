package com.ajou.jinwoo.median;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ajou.jinwoo.median.model.CommentModel;
import com.ajou.jinwoo.median.model.OnDataChangedListener;
import com.ajou.jinwoo.median.valueObject.Comment;
import com.ajou.jinwoo.median.viewholder.CommentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CommentListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private String dataRefKey;
    private String postType;
    private FirebaseRecyclerAdapter<Comment, CommentViewHolder> mAdapter;
    private CommentModel model;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        dataRefKey = getIntent().getExtras().getString("POST_KEY");
        postType = getIntent().getExtras().getString("POST_TYPE");
        model = new CommentModel(dataRefKey, postType);
        recyclerView = (RecyclerView) findViewById(R.id.comment_recycler_view);

        LinearLayoutManager mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);

        final EditText commentEdit = (EditText) findViewById(R.id.comment_edit);
        ImageButton sendButton = (ImageButton) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.writeComment(commentEdit.getText().toString());
                commentEdit.setText("");
            }
        });

        model.setOnDataChangedListener(new OnDataChangedListener() {
            @Override
            public void onDataChanged() {
                recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount()-1);
            }
        });

        setCommentList();

    }

    private void setCommentList() {

        mAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(Comment.class, R.layout.list_item_comment,
                CommentViewHolder.class, mDatabase.child("comments").child(dataRefKey)) {
            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {
                String commentKey = getRef(position).getKey();

                viewHolder.bindComment(model, dataRefKey, commentKey);

            }

        };
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change, R.anim.slide_down_anim);
        model.removeListener();
    }

}