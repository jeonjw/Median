package com.ajou.jinwoo.median.viewholder;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.Notice;

public class MediaNoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mContentsTextView;
    private Notice mNotice;
    private boolean isClicked;


    public MediaNoticeViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.media_notice_title_text_view);
        mContentsTextView = (TextView) itemView.findViewById(R.id.media_notice_contents_text_view);
        isClicked = false;
        itemView.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void bindNotice(Notice notice) {
        mNotice = notice;
        mTitleTextView.setText(mNotice.getTitle());

        if (Build.VERSION.SDK_INT >= 24) {
            mContentsTextView.setText(Html.fromHtml(mNotice.getContents(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            mContentsTextView.setText(Html.fromHtml(mNotice.getContents()));
        }

        mContentsTextView.setMaxLines(2);
        mContentsTextView.setEllipsize(TextUtils.TruncateAt.END);


    }

    @Override
    public void onClick(final View v) {
        if (!isClicked) {
            isClicked = true;
            mContentsTextView.setMaxLines(Integer.MAX_VALUE);
            mContentsTextView.setEllipsize(null);
        } else {
            isClicked = false;
            mContentsTextView.setMaxLines(2);
            mContentsTextView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }
}
