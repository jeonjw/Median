package com.ajou.jinwoo.median.model;

import android.content.Context;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.PhotoAlbum;
import com.ajou.jinwoo.median.viewholder.AlbumViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlbumModel {

    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<PhotoAlbum, AlbumViewHolder> adapter;

    public AlbumModel(final Context context) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        adapter = new FirebaseRecyclerAdapter<PhotoAlbum, AlbumViewHolder>(PhotoAlbum.class, R.layout.list_item_album,
                AlbumViewHolder.class, databaseReference.child("Photo_Album")) {
            @Override
            protected void populateViewHolder(AlbumViewHolder viewHolder, PhotoAlbum model, int position) {
                viewHolder.bindData(model, getRef(position).getKey(), context);
            }

        };
    }

    public void createAlbum(String title) {
        databaseReference.child("Photo_Album").
                push().setValue(new PhotoAlbum("https://firebasestorage.googleapis.com/v0/b/median-234c4.appspot.com/o/profileImages%2Fdefault.png?alt=media&token=45a00f70-1078-431b-86c5-005e19e6026c",
                title));
    }

    public FirebaseRecyclerAdapter<PhotoAlbum, AlbumViewHolder> getAdapter() {
        return adapter;
    }


}
