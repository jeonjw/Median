package com.ajou.jinwoo.median.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.Post;

/**
 * Created by jinwoo on 2017. 5. 4..
 */

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public PostViewHolder(View itemView) {
        super(itemView);
        mTitleTextView = (TextView) itemView.findViewById(R.id.board_lecture_review_title_text_view);
        mContentsView = (TextView) itemView.findViewById(R.id.board_lecture_review_contents_text_view);

        itemView.setOnClickListener(this);
    }
    private TextView mTitleTextView;
    private TextView mContentsView;



    @Override
    public void onClick(View v) {

    }

    public void bindPost(Post model) {
        mTitleTextView.setText(model.getTitle());
        mContentsView.setText(model.getContents());
    }
}
