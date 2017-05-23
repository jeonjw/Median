package com.ajou.jinwoo.median.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.jinwoo.median.AlbumDetailActivity;
import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.PhotoAlbum;
import com.bumptech.glide.Glide;

import java.util.Random;

public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView;
    private TextView detailTextView;
    private Random mRandom = new Random();
    private String dataRefKey;
    private Context context;

    AlbumViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageView = (ImageView) itemView.findViewById(R.id.album_title_text_view);
        detailTextView = (TextView) itemView.findViewById(R.id.album_detail_text_view);
        imageView.getLayoutParams().height = getRandomIntInRange(570, 350);

    }

    public void bindData(PhotoAlbum photoAlbum, String dataRefKey, Context context) {
        Glide.with(context)
                .load(photoAlbum.getMainImageUrl())
                .centerCrop()
                .into(imageView);

        detailTextView.setText(photoAlbum.getDetail());
        this.dataRefKey = dataRefKey;
        this.context = context;
    }

    protected int getRandomIntInRange(int max, int min) {
        return mRandom.nextInt((max - min) + min) + min;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, AlbumDetailActivity.class);
        intent.putExtra("POST_KEY", dataRefKey);

        context.startActivity(intent);
    }
}
