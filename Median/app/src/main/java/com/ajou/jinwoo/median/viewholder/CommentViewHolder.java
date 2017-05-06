package com.ajou.jinwoo.median.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.Comment;


public class CommentViewHolder extends RecyclerView.ViewHolder{
    private TextView authorView;
    private TextView bodyView;
    private TextView dateView;

    public CommentViewHolder(View itemView) {
        super(itemView);
        authorView = (TextView) itemView.findViewById(R.id.comment_author);
        bodyView = (TextView) itemView.findViewById(R.id.comment_body);
        dateView = (TextView) itemView.findViewById(R.id.comment_date);
    }
    public void bindComment(Comment model) {
        authorView.setText(model.getAuthor());
        bodyView.setText(model.getText());
        dateView.setText(model.getTimeStamp());
    }
}
