package com.ajou.jinwoo.median;


import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajou.jinwoo.median.model.Notice;
import com.ajou.jinwoo.median.model.StudentNotice;
import com.ajou.jinwoo.median.viewholder.MediaNoticeViewHolder;
import com.ajou.jinwoo.median.viewholder.StudentNoticeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaNoticeFragment extends Fragment {

    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_notice, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.media_notice_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setHasOptionsMenu(true);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);


        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.scrollToPositionWithOffset(0,0);
        recyclerView.setLayoutManager(mManager);

        FirebaseRecyclerAdapter<Notice, MediaNoticeViewHolder> mAdapter = new FirebaseRecyclerAdapter<Notice, MediaNoticeViewHolder>(Notice.class, R.layout.list_item_media_notice,
                MediaNoticeViewHolder.class, mDatabase.child("media_notice")) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void populateViewHolder(MediaNoticeViewHolder viewHolder, Notice model, int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
//                Log.d("Firebase",postKey);
                viewHolder.bindNotice(model);
                progressDialog.dismiss();
            }


        };

        recyclerView.setAdapter(mAdapter);

        return view;
    }



    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_write).setVisible(false);
        menu.findItem(R.id.menu_search).setVisible(false);
    }
}
