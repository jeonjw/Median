package com.ajou.jinwoojeon.median.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.viewholder.PhotoViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.utils.AndroidLifecycleUtils;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {
    private List<String> photoPaths = new ArrayList<>();

    public PhotoAdapter(List<String> photoPaths) {
        this.photoPaths = photoPaths;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.__picker_item_photo, parent, false);
        return new PhotoViewHolder(itemView, photoPaths, parent.getContext());
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.setPosition(position);

        boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(holder.getPhotoImageView().getContext());
        if (canLoadImage) {
            Glide.with(holder.getPhotoImageView().getContext())
                    .load(photoPaths.get(position))
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.__picker_ic_photo_black_48dp)
                    .error(R.drawable.__picker_ic_broken_image_black_48dp)
                    .into(holder.getPhotoImageView());
        }

    }

    @Override
    public int getItemCount() {
        return photoPaths.size();
    }
}
