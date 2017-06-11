package com.ajou.jinwoojeon.median.viewholder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ajou.jinwoojeon.median.R;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPreview;

public class BoardWritePhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Context context;
    private ImageView ivPhoto;
    private List<String> photoPaths;
    private int position;


    public BoardWritePhotoViewHolder(View itemView, List<String> photoPaths, Context context) {
        super(itemView);
        ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
        View vSelected = itemView.findViewById(R.id.v_selected);
        if (vSelected != null) vSelected.setVisibility(View.GONE);
        this.photoPaths = photoPaths;
        this.context = context;

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        PhotoPreview.builder()
                .setPhotos((ArrayList<String>) photoPaths)
                .setCurrentItem(position)
                .start((Activity) context);
    }


    public ImageView getIvPhoto() {
        return ivPhoto;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
