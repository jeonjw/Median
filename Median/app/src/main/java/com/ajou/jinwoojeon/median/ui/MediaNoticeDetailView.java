package com.ajou.jinwoojeon.median.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.jinwoojeon.median.R;
import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPreview;

public class MediaNoticeDetailView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_notice_detail_view);

        TextView titleTextView = findViewById(R.id.media_notice_detail_title_text_view);
        TextView contentsTextView = findViewById(R.id.media_notice_detail_contents_text_view);
        contentsTextView.setMovementMethod(new ScrollingMovementMethod());

        ImageView imageView = findViewById(R.id.media_notice_detail_image_view);

        titleTextView.setText(getIntent().getStringExtra("TITLE"));
        String contents = getIntent().getStringExtra("CONTENTS");
        final String imageUrl = getIntent().getStringExtra("IMAGE_URL");

        System.out.println("TEST Detail "+imageUrl);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imageUrlList = new ArrayList<>();
                imageUrlList.add(imageUrl);
                PhotoPreview.builder()
                        .setPhotos(imageUrlList)
                        .setCurrentItem(0)
                        .start(MediaNoticeDetailView.this);
            }
        });


        if (imageUrl != null) {
            System.out.println("TEST imageURL:" + imageUrl);
            Document dom = Jsoup.parse(contents);
            Elements elements = dom.select("img");
            elements.remove();

            contents = (dom.text().length() < 5) ? "이미지를 클릭해서 확대 가능합니다." : dom.toString();
            Glide.with(this).load(imageUrl).into(imageView);
        }

        contentsTextView.setText(Html.fromHtml(contents));
    }


}
