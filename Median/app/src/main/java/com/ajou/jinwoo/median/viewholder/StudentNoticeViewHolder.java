package com.ajou.jinwoo.median.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoo.median.CommentListActivity;
import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.StudentNotice;

public class StudentNoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView titleTextView;
    private TextView contentsTextView;
    private TextView authorTextView;
    private TextView dateTextView;
    private TextView commentCountTextView;
    private String dataRefKey;
    private Context context;


    public StudentNoticeViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.student_notice_title_text_view);
        contentsTextView = (TextView) itemView.findViewById(R.id.student_notice_contents_text_view);
        authorTextView = (TextView) itemView.findViewById(R.id.student_notice_author_text_view);
        dateTextView = (TextView) itemView.findViewById(R.id.student_notice_date_text_view);
        commentCountTextView = (TextView) itemView.findViewById(R.id.comment_number);

        itemView.setOnClickListener(this);
    }


    public void bindNotice(StudentNotice studentNotice, Context context, String postKey) {
        titleTextView.setText(studentNotice.getTitle());
        contentsTextView.setText(studentNotice.getContents());
        authorTextView.setText(studentNotice.getAuthor());
        dateTextView.setText(studentNotice.getTimeStamp());
        commentCountTextView.setText(String.valueOf(studentNotice.getCommentCount()));
        dataRefKey = postKey;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, CommentListActivity.class);
        intent.putExtra("POST_KEY", dataRefKey);
        intent.putExtra("POST_TYPE","student_notice");
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_up_anim, R.anim.no_change);
    }
}
//댓글 아이콘과 글자 레이아웃 다시 잡기.