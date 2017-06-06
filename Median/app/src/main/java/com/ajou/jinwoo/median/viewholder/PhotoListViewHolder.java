package com.ajou.jinwoo.median.viewholder;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.PhotoListModel;
import com.ajou.jinwoo.median.valueObject.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.iwf.photopicker.PhotoPreview;


public class PhotoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private ImageView imageView;
    private int position;
    private List<String> imageUrlList;
    private PhotoListModel photoListModel;

    public ImageView getImageView() {
        return imageView;
    }

    public PhotoListViewHolder(View itemView, List<String> imageUrlList, String dataRefKey) {
        super(itemView);
        this.context = itemView.getContext();
        imageView = (ImageView) itemView.findViewById(R.id.photo_list_image_view);
        imageView.getLayoutParams().height = getRandomIntInRange(500, 300);
        photoListModel = new PhotoListModel(dataRefKey);

        this.imageUrlList = imageUrlList;

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
        final PopupMenu popup = new PopupMenu(context, imageView);
        popup.getMenuInflater()
                .inflate(R.menu.popup_photo, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.popup_set_main_image)
                    photoListModel.setMainPhoto(imageUrlList.get(position));

                else if (item.getItemId() == R.id.popup_delete_photo) {

                    if (User.getInstance().isAdmin()) {
                        photoListModel.deletePhoto(position);
                        Snackbar.make(imageView, "" + position, Snackbar.LENGTH_SHORT).show();
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
