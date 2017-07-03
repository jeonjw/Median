package com.ajou.jinwoojeon.median.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.viewholder.BoardWritePhotoViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.utils.AndroidLifecycleUtils;

public class PhotoAdapter extends RecyclerView.Adapter<BoardWritePhotoViewHolder> {
    private List<String> photoPaths = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;

    public PhotoAdapter(Context mContext, List<String> photoPaths) {
        this.photoPaths = photoPaths;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public BoardWritePhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.__picker_item_photo, parent, false);
        return new BoardWritePhotoViewHolder(itemView, photoPaths, mContext);
    }

    @Override
    public void onBindViewHolder(BoardWritePhotoViewHolder holder, int position) {
        holder.setPosition(position);

        boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(holder.getIvPhoto().getContext());
        if (canLoadImage) {
            Glide.with(mContext)
                    .load(photoPaths.get(position))
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.__picker_ic_photo_black_48dp)
                    .error(R.drawable.__picker_ic_broken_image_black_48dp)
                    .into(holder.getIvPhoto());
        }

    }

    @Override
    public int getItemCount() {
        return photoPaths.size();
    }
}
