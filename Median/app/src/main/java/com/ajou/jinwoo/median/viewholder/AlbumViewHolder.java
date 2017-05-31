package com.ajou.jinwoo.median.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.jinwoo.median.ui.PhotoListActivity;
import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.AlbumModel;
import com.ajou.jinwoo.median.valueObject.PhotoAlbum;
import com.ajou.jinwoo.median.valueObject.User;
import com.bumptech.glide.Glide;

import java.util.Random;

public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private ImageView imageView;
    private TextView detailTextView;
    private Random mRandom = new Random();
    private String dataRefKey;
    private Context context;
    private AlbumModel albumModel;

    AlbumViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        imageView = (ImageView) itemView.findViewById(R.id.album_main_image_view);
        detailTextView = (TextView) itemView.findViewById(R.id.album_title_text_view);

        imageView.getLayoutParams().height = getRandomIntInRange(550, 400);
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
        Intent intent = new Intent(context, PhotoListActivity.class);
        intent.putExtra("ALBUM_TITLE", detailTextView.getText().toString());
        intent.putExtra("POST_KEY", dataRefKey);

        context.startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {

        final PopupMenu popup = new PopupMenu(context, imageView);
        popup.getMenuInflater()
                .inflate(R.menu.popup_album, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.popup_delete_album) {
                    albumModel = new AlbumModel(context);
                    if (User.getInstance().isAdmin()) {
                       albumModel.deleteAlbum(dataRefKey);
                    } else
                        Snackbar.make(imageView, "삭제 권한이 없습니다", Snackbar.LENGTH_SHORT).show();
                }


                return true;
            }
        });

        popup.show();
        return true;


    }
}
