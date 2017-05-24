package com.ajou.jinwoo.median.viewholder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ajou.jinwoo.median.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.iwf.photopicker.PhotoPreview;


public class AlbumDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private ImageView imageView;
    private int position;
    private List<String> imageUrlList;
    private String dataRefKey;

    public ImageView getImageView() {
        return imageView;
    }

    public AlbumDetailViewHolder(View itemView, Context context, List<String> imageUrlList, String dataRefKey) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.album_detail_image_view);
        imageView.getLayoutParams().height = getRandomIntInRange(500, 300);
        this.context = context;
        this.imageUrlList = imageUrlList;
        this.dataRefKey = dataRefKey;

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    protected int getRandomIntInRange(int max, int min) {
        Random random = new Random();
        return random.nextInt((max - min) + min) + min;
    }


    @Override
    public void onClick(View v) {
        PhotoPreview.builder()
                .setPhotos((ArrayList<String>) imageUrlList)
                .setShowDeleteButton(false)
                .setCurrentItem(position)
                .start((Activity) context);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean onLongClick(View v) {
        PopupMenu popup = new PopupMenu(context, imageView);
        popup.getMenuInflater()
                .inflate(R.menu.popup_photo, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FirebaseDatabase.getInstance().getReference().child("Photo_Album").child(dataRefKey).child("mainImageUrl").setValue(imageUrlList.get(position));
                return true;
            }
        });

        popup.show();
        return true;


    }
}
