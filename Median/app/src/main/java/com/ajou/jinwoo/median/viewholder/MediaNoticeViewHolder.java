package com.ajou.jinwoo.median.viewholder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.Notice;
import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import uk.co.senab.photoview.PhotoView;

public class MediaNoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mContentsTextView;
    private PhotoView photoView;
    private boolean isClicked;
    private boolean haveImage;
    private String imageUrl;
    private Context context;
    private Elements elements;
    private Document dom;


    public MediaNoticeViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.media_notice_title_text_view);
        mContentsTextView = (TextView) itemView.findViewById(R.id.media_notice_contents_text_view);
        photoView = (PhotoView) itemView.findViewById(R.id.media_notice_image_view);
        itemView.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void bindNotice(Notice notice, Context context) {

        isClicked = false;
        haveImage = false;
        this.context = context;

        bindTitle(notice.getTitle());
        bindContents(notice.getContents());

        if (haveImage(notice.getContents())) {
            haveImage = true;
            imageUrl = "http://media.ajou.ac.kr/" + elements.attr("src");
            if (dom.text().length() < 5)
                mContentsTextView.setText("이미지 펼쳐 보기..");
            photoView.setVisibility(View.VISIBLE);
        } else {
            haveImage = false;
            photoView.setVisibility(View.GONE);
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
            if (haveImage)
                Glide.with(context).load(imageUrl).into(photoView);
        } else {
            isClicked = false;
            mContentsTextView.setMaxLines(2);
            mContentsTextView.setEllipsize(TextUtils.TruncateAt.END);
            photoView.setImageDrawable(null);
        }
    }

    private boolean haveImage(String contetns) {
        dom = Jsoup.parse(contetns);
        elements = dom.select("img");
        return elements.size() != 0;
    }

    private void bindTitle(String title) {
        mTitleTextView.setText(title);
    }

    private void bindContents(String contents) {
        if (Build.VERSION.SDK_INT >= 24) {
            mContentsTextView.setText(Html.fromHtml(contents, Html.FROM_HTML_MODE_COMPACT));
        } else {
            mContentsTextView.setText(Html.fromHtml(contents));
        }
    }
}
