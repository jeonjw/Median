package com.ajou.jinwoojeon.median.model;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.valueObject.PhotoAlbum;
import com.ajou.jinwoojeon.median.viewholder.AlbumViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlbumModel {

    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<PhotoAlbum, AlbumViewHolder> adapter;

    public AlbumModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        adapter = new FirebaseRecyclerAdapter<PhotoAlbum, AlbumViewHolder>(PhotoAlbum.class, R.layout.list_item_album,
                AlbumViewHolder.class, databaseReference.child("Photo_Album")) {
            @Override
            protected void populateViewHolder(AlbumViewHolder viewHolder, PhotoAlbum model, int position) {
                viewHolder.bindData(model, getRef(position).getKey());
            }

        };
    }

    public void createAlbum(String title) {
        databaseReference.child("Photo_Album").
                push().setValue(new PhotoAlbum("https://firebasestorage.googleapis.com/v0/b/median-234c4.appspot.com/o/profileImages%2Fdefault.png?alt=media&token=45a00f70-1078-431b-86c5-005e19e6026c",
                title));
    }

    public void deleteAlbum(String dataRefKey) {
        databaseReference.child("Photo_Album").child(dataRefKey).removeValue();
        databaseReference.child("photo_list").child(dataRefKey).removeValue();
    }

    public void renameAlbum(String dataRefKey){
//        databaseReference.child("Photo_Album").child(dataRefKey).child("detail").setValue();
    }

    public FirebaseRecyclerAdapter<PhotoAlbum, AlbumViewHolder> getAdapter() {
        return adapter;
    }


}