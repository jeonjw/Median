package com.ajou.jinwoo.median.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.Info;
import com.ajou.jinwoo.median.viewholder.InfoViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoModel {
    private FirebaseRecyclerAdapter<Info, InfoViewHolder> adapter;
    private OnDataChangedListener onDataChangedListener;

    public InfoModel() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new FirebaseRecyclerAdapter<Info, InfoViewHolder>(Info.class, R.layout.list_item_info,
                InfoViewHolder.class, databaseReference.child("info")) {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            protected void populateViewHolder(InfoViewHolder viewHolder, Info model, int position) {
                viewHolder.bindInfo(model);

                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }

            }

        };
    }

    public FirebaseRecyclerAdapter<Info, InfoViewHolder> getAdapter() {
        return adapter;
    }

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }


}