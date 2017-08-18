package com.ajou.jinwoojeon.median.viewholder;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.adapter.PhotoAdapter;
import com.ajou.jinwoojeon.median.ui.BoardTabFragment;
import com.ajou.jinwoojeon.median.ui.BoardWriteActivity;
import com.ajou.jinwoojeon.median.ui.CommentDialogFragment;
import com.ajou.jinwoojeon.median.valueObject.Post;
import com.ajou.jinwoojeon.median.valueObject.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private TextView titleTextView;
    private TextView contentsTextView;
    private TextView authorTextView;
    private TextView dateTextView;
    private TextView commentCountTextView;

    private ImageButton dropdownButton;
    private String dataRefKey;
    private String postType;
    private Context context;
    private DatabaseReference mDatabase;
    private Post post;
    private RecyclerView photoRecyclerView;

    public PostViewHolder(final View itemView) {
        super(itemView);

        context = itemView.getContext();
        titleTextView = itemView.findViewById(R.id.post_title_text_view);
        contentsTextView = itemView.findViewById(R.id.post_contents_text_view);
        authorTextView = itemView.findViewById(R.id.post_author_text_view);
        dateTextView = itemView.findViewById(R.id.post_date_text_view);
        commentCountTextView = itemView.findViewById(R.id.post_comment_number);
        dropdownButton = itemView.findViewById(R.id.dropdown_button);
        photoRecyclerView = itemView.findViewById(R.id.post_image_recycler_view);
        FrameLayout commentImageLayout = itemView.findViewById(R.id.comment_image_layout);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        titleTextView.setFocusableInTouchMode(true);
        photoRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));


        dropdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, dropdownButton);
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.popup_delete) {
                            if (Objects.equals(post.getAuthorUid(), User.getInstance().getUid())) {
                                mDatabase.child("comments").child(dataRefKey).removeValue();
                                mDatabase.child(postType).child(dataRefKey).removeValue();
                            } else {
                                Snackbar.make(dateTextView, "권한이 없습니다", Snackbar.LENGTH_SHORT).show();
                            }
                        } else if (item.getItemId() == R.id.popup_rewrite) {
                            Intent intent = new Intent(context, BoardWriteActivity.class);
                            intent.putExtra("CURRENT_BOARD_TAB", BoardTabFragment.getCurrentTab());
                            intent.putExtra("BOARD_TITLE", post.getTitle());
                            intent.putExtra("BOARD_CONTENTS", post.getContents());
                            intent.putExtra("CORRECT_POST_KEY", dataRefKey);
                            intent.putExtra("COMMENT_COUNT", post.getCommentCount());
                            intent.putExtra("PHOTO_URL_LIST", (Serializable) post.getUrlList());
                            context.startActivity(intent);
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

        commentImageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =
                        ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();

                CommentDialogFragment dialog = CommentDialogFragment.newInstance(dataRefKey, postType, post.getTitle());
                dialog.show(fragmentManager, "commentDialog");
            }
        });

    }

    public void bindPost(final Post model, String postKey, String postType) {
        post = model;


        titleTextView.setText(model.getTitle());
        contentsTextView.setText(model.getContents());
        authorTextView.setText(model.getAuthor());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd.", Locale.KOREA);
        dateTextView.setText(dateFormat.format(model.getTimeStamp()));
        commentCountTextView.setText(String.valueOf(model.getCommentCount()));
        this.dataRefKey = postKey;
        this.postType = postType;

        int visibility = Objects.equals(model.getAuthorUid(), User.getInstance().getUid()) ?
                View.VISIBLE : View.GONE;

        dropdownButton.setVisibility(visibility);

        if (model.getUrlList() == null || model.getUrlList().size() == 0)
            photoRecyclerView.setVisibility(View.GONE);
        else {
            photoRecyclerView.setVisibility(View.VISIBLE);
            PhotoAdapter photoAdapter = new PhotoAdapter(model.getUrlList());
            photoRecyclerView.setAdapter(photoAdapter);
        }

        titleTextView.requestFocus();

    }

}
