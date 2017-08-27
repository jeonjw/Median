package com.ajou.jinwoojeon.median.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.ui.MediaNoticeDetailView;
import com.ajou.jinwoojeon.median.valueObject.MediaNotice;
import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPreview;

public class MediaNoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView titleTextView;
    private TextView contentsTextView;
    private TextView dateTextView;
    private ImageView imageView;
    private String imageUrl;
    private Context context;
    private Elements elements;
    private Document dom;
    private MediaNotice mediaNotice;


    public MediaNoticeViewHolder(View itemView) {
        super(itemView);

        this.context = itemView.getContext();
        titleTextView = itemView.findViewById(R.id.media_notice_title_text_view);
        contentsTextView = itemView.findViewById(R.id.media_notice_contents_text_view);
        dateTextView = itemView.findViewById(R.id.media_notice_date_text_view);
        imageView = itemView.findViewById(R.id.media_notice_image_view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imageUrlList = new ArrayList<>();
                imageUrlList.add(imageUrl);
                PhotoPreview.builder()
                        .setPhotos(imageUrlList)
                        .setCurrentItem(0)
                        .start((Activity) context);
            }
        });

        itemView.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void bindNotice(MediaNotice mediaNotice) {


        this.mediaNotice = mediaNotice;
        bindTitle(mediaNotice.getTitle());

        if (haveImage(mediaNotice.getContents())) {
            imageView.setVisibility(View.VISIBLE);
            imageUrl = "http://media.ajou.ac.kr/" + elements.attr("src");
            Glide.with(context).load(imageUrl).centerCrop().into(imageView);
            String contents = (dom.text().length() < 5) ? "이미지 펼쳐보기" : dom.toString();
            bindContents(contents);

        } else {
            imageView.setVisibility(View.GONE);
            bindContents(mediaNotice.getContents());
        }

        contentsTextView.setMaxLines(2);
        contentsTextView.setEllipsize(TextUtils.TruncateAt.END);
        dateTextView.setText(mediaNotice.getDate());
    }

    @Override
    public void onClick(final View v) {

        Intent intent = new Intent(context, MediaNoticeDetailView.class);
        intent.putExtra("TITLE", mediaNotice.getTitle());
        intent.putExtra("CONTENTS", mediaNotice.getContents());
        intent.putExtra("DATE", mediaNotice.getDate());
        if (imageUrl != null)
            intent.putExtra("IMAGE_URL", imageUrl);
        context.startActivity(intent);

    }

    private boolean haveImage(String contents) {
        dom = Jsoup.parse(contents);
        elements = dom.select("img");
        return elements.size() != 0;
    }

    private void bindTitle(String title) {
        titleTextView.setText(title);
    }

    private void bindContents(String contents) {
        contentsTextView.setText(Jsoup.parse(contents).text());
    }
}
