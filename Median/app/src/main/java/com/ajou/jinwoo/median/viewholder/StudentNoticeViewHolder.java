package com.ajou.jinwoo.median.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.StudentNotice;

public class StudentNoticeViewHolder extends RecyclerView.ViewHolder{
    private TextView mTitleTextView;
    private TextView mContentsTextView;
    private StudentNotice mStudentNotice;


    public StudentNoticeViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.media_notice_title_text_view);
        mContentsTextView = (TextView) itemView.findViewById(R.id.media_notice_contents_text_view);
    }


    public void bindNotice(StudentNotice studentNotice) {
        mStudentNotice = studentNotice;
        mTitleTextView.setText(mStudentNotice.getTitle());
        mContentsTextView.setText(mStudentNotice.getContents());

    }
}
