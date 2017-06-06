package com.ajou.jinwoo.median.viewholder;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.MediaNotice;
import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPreview;

public class MediaNoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mContentsTextView;
    private ImageView imageView;
    private boolean isClicked;
    private boolean haveImage;
    private String imageUrl;
    private Context context;
    private Elements elements;
    private Document dom;


    public MediaNoticeViewHolder(View itemView) {
        super(itemView);

        this.context = itemView.getContext();
        mTitleTextView = (TextView) itemView.findViewById(R.id.media_notice_title_text_view);
        mContentsTextView = (TextView) itemView.findViewById(R.id.media_notice_contents_text_view);
        imageView = (ImageView) itemView.findViewById(R.id.media_notice_image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(imageUrl);
                PhotoPreview.builder()
                        .setPhotos(temp)
                        .setCurrentItem(0)
                        .start((Activity) context);
            }
        });
        itemView.setOnClickListener(this);
        mTitleTextView.setFocusableInTouchMode(true);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void bindNotice(MediaNotice mediaNotice) {

        isClicked = false;
        haveImage = false;

        bindTitle(mediaNotice.getTitle());

        if (haveImage(mediaNotice.getContents())) {
            haveImage = true;
            imageUrl = "http://media.ajou.ac.kr/" + elements.attr("src");

            String contents = (dom.text().length() < 5) ? "이미지 펼쳐보기" : dom.toString();
            bindContents(contents);

        } else {
            haveImage = false;
            imageView.setVisibility(View.GONE);
            bindContents(mediaNotice.getContents());
        }

        mContentsTextView.setMaxLines(2);
        mContentsTextView.setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public void onClick(final View v) {
        if (!isClicked) {
            isClicked = true;
            imageView.setVisibility(View.VISIBLE);
            mContentsTextView.setMaxLines(Integer.MAX_VALUE);
            mContentsTextView.setEllipsize(null);
            if (haveImage)
                Glide.with(context).load(imageUrl).into(imageView);
        } else {
            isClicked = false;
            mContentsTextView.setMaxLines(2);
            mContentsTextView.setEllipsize(TextUtils.TruncateAt.END);
            imageView.setImageDrawable(null);
        }


    }

    private boolean haveImage(String contents) {
        dom = Jsoup.parse(contents);
        elements = dom.select("img");
        elements.remove();
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
