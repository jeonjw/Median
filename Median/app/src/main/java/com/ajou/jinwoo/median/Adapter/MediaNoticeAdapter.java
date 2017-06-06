package com.ajou.jinwoo.median.Adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.MediaNotice;
import com.ajou.jinwoo.median.viewholder.MediaNoticeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinwoo on 2017. 6. 6..
 */

public class MediaNoticeAdapter extends RecyclerView.Adapter<MediaNoticeViewHolder> {
    private List<MediaNotice> mediaNoticeList;

    public MediaNoticeAdapter() {
        mediaNoticeList = new ArrayList<>();
    }

    public void setList(List<MediaNotice> searchedList) {
        mediaNoticeList = searchedList;
        notifyDataSetChanged();
    }

    @Override
    public MediaNoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.list_item_media_notice, parent, false);

        return new MediaNoticeViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final MediaNoticeViewHolder holder, int position) {
        MediaNotice mediaNotice = mediaNoticeList.get(position);
        holder.bindNotice(mediaNotice);
    }

    @Override
    public int getItemCount() {
        return mediaNoticeList.size();
    }
}