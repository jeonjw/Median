package com.ajou.jinwoo.median.viewholder;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.DownloadImageTask;
import com.ajou.jinwoo.median.model.Notice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Objects;

public class MediaNoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mContentsTextView;
    private ImageView imageView;
    private boolean isClicked;
    private boolean hasImage;
    private String imageUrl;


    public MediaNoticeViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.media_notice_title_text_view);
        mContentsTextView = (TextView) itemView.findViewById(R.id.media_notice_contents_text_view);
        imageView = (ImageView) itemView.findViewById(R.id.media_notice_image_view);
        isClicked = false;
        hasImage = false;
        itemView.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void bindNotice(Notice notice) {

        mTitleTextView.setText(notice.getTitle());
        Document dom = Jsoup.parse(notice.getContents());
        Elements elements = dom.select("img");


        if (elements.size() != 0) {// 게시글에 이미지가 있다면
            hasImage = true;
            imageUrl = "http://media.ajou.ac.kr/" + elements.attr("src");

            if (Objects.equals(dom.text(), ""))
                mContentsTextView.setText("이미지 펼쳐 보기..");
//            new DownloadImageTask(imageView).execute("http://media.ajou.ac.kr/" + elements.attr("src"));
        } else {
            imageView.setImageDrawable(null);
            if (Build.VERSION.SDK_INT >= 24) {
                mContentsTextView.setText(Html.fromHtml(notice.getContents(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                mContentsTextView.setText(Html.fromHtml(notice.getContents()));
            }
        }

//        if (!Objects.equals(dom.text(), "")) //게시글에 내용이 있으면 ..

//        else { //게시글에 내용이 없으면
//            mContentsTextView.setText("");
//        }

        mContentsTextView.setMaxLines(2);
        mContentsTextView.setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public void onClick(final View v) {
        if (!isClicked) {
            isClicked = true;
            mContentsTextView.setMaxLines(Integer.MAX_VALUE);
            mContentsTextView.setEllipsize(null);
            if (hasImage)
                new DownloadImageTask(imageView).execute(imageUrl);
        } else {
            isClicked = false;
            mContentsTextView.setMaxLines(2);
            mContentsTextView.setEllipsize(TextUtils.TruncateAt.END);
            imageView.setImageDrawable(null);
        }
    }
}
