package com.ajou.jinwoo.median.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoo.median.PhotoListActivity;
import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.viewholder.PhotoListViewHolder;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.iwf.photopicker.utils.AndroidLifecycleUtils;

public class PhotoListModel {
    private DatabaseReference databaseReference;
    private String dataRefKey;
    private Context context;
    private PhotoListAdapter photoListAdapter;
    private List<String> imageUrlList = new ArrayList<>();
    private List<String> urlList = new ArrayList<>();


    public PhotoListModel(Context context, String dataRefKey) {
        this.context = context;
        this.dataRefKey = dataRefKey;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        photoListAdapter = new PhotoListAdapter(imageUrlList);

        loadPhotoList();
    }

    public void addSelectedPhoto(final List<InputStream> selectedPhotos) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://median-234c4.appspot.com").child("albumImages");

        for (InputStream inputStream : selectedPhotos) {
            UploadTask uploadTask = storageReference.child(generateTempFilename()).putStream(inputStream);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    urlList.add(taskSnapshot.getDownloadUrl().toString());

                    if (urlList.size() == selectedPhotos.size()) {
                        imageUrlList.addAll(urlList);
                        databaseReference.child("photo_list").child(dataRefKey).setValue(imageUrlList);
                        urlList.clear();
                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }

    }

    private String generateTempFilename() {
        return UUID.randomUUID().toString();
    }

    public void loadPhotoList() {
        databaseReference.child("photo_list").child(dataRefKey).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imageUrlList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    imageUrlList.add(ds.getValue().toString());
                }

                photoListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public PhotoListAdapter getPhotoListAdapter() {
        return photoListAdapter;
    }

    private class PhotoListAdapter extends RecyclerView.Adapter<PhotoListViewHolder> {
        private List<String> imageUrlList;

        public PhotoListAdapter(List<String> imageUrlList) {
            this.imageUrlList = imageUrlList;
        }

        @Override
        public PhotoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            final View view = layoutInflater.inflate(R.layout.list_item_photo_list, parent, false);

            return new PhotoListViewHolder(view, context, imageUrlList, dataRefKey);
        }

        @Override
        public void onBindViewHolder(final PhotoListViewHolder holder, int position) {

            holder.setPosition(position);
            boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(holder.getImageView().getContext());
            if (canLoadImage) {
                Glide.with(context)
                        .load(imageUrlList.get(position))
                        .centerCrop()
                        .thumbnail(0.1f)
                        .placeholder(R.drawable.__picker_ic_photo_black_48dp)
                        .error(R.drawable.__picker_ic_broken_image_black_48dp)
                        .into(holder.getImageView());
            }
        }

        @Override
        public int getItemCount() {
            return imageUrlList.size();
        }
    }
}
