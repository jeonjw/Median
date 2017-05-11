package com.ajou.jinwoo.median.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.Comment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CommentViewHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener {
    private TextView authorView;
    private TextView bodyView;
    private TextView dateView;
    private String commentKey;
    private String postKey;
    private String postType;
    private int commentCount;

    public CommentViewHolder(View itemView) {
        super(itemView);
        authorView = (TextView) itemView.findViewById(R.id.comment_author);
        bodyView = (TextView) itemView.findViewById(R.id.comment_body);
        dateView = (TextView) itemView.findViewById(R.id.comment_date);
        itemView.setOnCreateContextMenuListener(this);

    }

    public void bindComment(Comment model, String postType, String postKey, String commentKey, int commentCount) {
        authorView.setText(model.getAuthor());
        bodyView.setText(model.getText());
        dateView.setText(model.getTimeStamp());
        this.commentKey = commentKey;
        this.postKey = postKey;
        this.postType = postType;
        this.commentCount = commentCount;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem edit = menu.add(Menu.NONE, 1, 1, "수정");
        MenuItem delete = menu.add(Menu.NONE, 2, 2, "삭제");

        edit.setOnMenuItemClickListener(menuItemClickListener);
        delete.setOnMenuItemClickListener(menuItemClickListener);
    }

    private final MenuItem.OnMenuItemClickListener menuItemClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    System.out.println("TEST Edit");
                    return true;
                case 2:
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("comments").child(postKey).child(commentKey).removeValue();
//                    commentCount --;
//                    mDatabase.child(postType).child(postKey).child("commentCount").setValue(commentCount);
                    System.out.println("삭제");
                    return true;
            }
            return false;
        }
    };


}
