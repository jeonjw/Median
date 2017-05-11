package com.ajou.jinwoo.median.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ajou.jinwoo.median.CommentListActivity;
import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView titleTextView;
    private TextView contentsTextView;
    private TextView authorTextView;
    private TextView dateTextView;
    private TextView commentCountTextView;
    private ImageButton dropdownButton;
    private String dataRefKey;
    private String postType;
    private Context context;
    private int commentCount;


    public PostViewHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.post_title_text_view);
        contentsTextView = (TextView) itemView.findViewById(R.id.post_contents_text_view);
        authorTextView = (TextView) itemView.findViewById(R.id.post_author_text_view);
        dateTextView = (TextView) itemView.findViewById(R.id.post_date_text_view);
        commentCountTextView = (TextView) itemView.findViewById(R.id.post_comment_number);
        dropdownButton = (ImageButton) itemView.findViewById(R.id.dropdown_button);
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        dropdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, dropdownButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());


                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                context,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        if (item.getItemId() == R.id.popup_delete) {
                            mDatabase.child(postType).child(dataRefKey).removeValue();
                            mDatabase.child("comments").child(dataRefKey).removeValue();
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

        itemView.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, CommentListActivity.class);
        intent.putExtra("POST_KEY", dataRefKey);
        intent.putExtra("POST_TYPE", postType);
        intent.putExtra("COMMENT_COUNT",commentCount);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_up_anim, R.anim.no_change);
    }

    public void bindPost(Post model, Context context, String postKey, String postType) {
        titleTextView.setText(model.getTitle());
        contentsTextView.setText(model.getContents());
        authorTextView.setText(model.getAuthor());
        dateTextView.setText(model.getTimeStamp());
        commentCountTextView.setText(String.valueOf(model.getCommentCount()));
        dataRefKey = postKey;
        this.postType = postType;
        this.context = context;
        this.commentCount = model.getCommentCount();
    }
}
