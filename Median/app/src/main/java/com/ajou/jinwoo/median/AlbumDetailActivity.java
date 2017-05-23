package com.ajou.jinwoo.median;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoo.median.viewholder.AlbumDetailViewHolder;
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

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import me.iwf.photopicker.utils.AndroidLifecycleUtils;

public class AlbumDetailActivity extends AppCompatActivity {

    private String dataRefKey;
    private DatabaseReference databaseReference;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private List<String> urlList = new ArrayList<>();
    private List<String> imageUrlList = new ArrayList<>();
    private AlbumDetailAdapter albumDetailAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        dataRefKey = getIntent().getExtras().getString("POST_KEY");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.album_detail_recycler_view);
        FloatingActionButton addPhoto = (FloatingActionButton) findViewById(R.id.add_photo_button);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setPreviewEnabled(false)
                        .start(AlbumDetailActivity.this, PhotoPicker.REQUEST_CODE);

            }
        });
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        loadAlbumImages();
        albumDetailAdapter = new AlbumDetailAdapter(imageUrlList);
        recyclerView.setAdapter(albumDetailAdapter);
    }

    private class AlbumDetailAdapter extends RecyclerView.Adapter<AlbumDetailViewHolder> {
        private List<String> imageUrlList;

        public AlbumDetailAdapter(List<String> imageUrlList) {
            this.imageUrlList = imageUrlList;
        }

        @Override
        public AlbumDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(AlbumDetailActivity.this);
            final View view = layoutInflater.inflate(R.layout.list_item_album_detail, parent, false);

            return new AlbumDetailViewHolder(view, AlbumDetailActivity.this,imageUrlList,dataRefKey);
        }

        @Override
        public void onBindViewHolder(final AlbumDetailViewHolder holder, int position) {

            holder.setPosition(position);
            boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(holder.getImageView().getContext());
            if (canLoadImage) {
                Glide.with(AlbumDetailActivity.this)
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

    private void loadAlbumImages() {
        databaseReference.child("photo_list").child(dataRefKey).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imageUrlList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    imageUrlList.add(ds.getValue().toString());
                }

                albumDetailAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }

            selectedPhotos.clear();

            if (photos != null)
                selectedPhotos.addAll(photos);

            addPhotoToAlbum(getInputStreamFromUri(selectedPhotos));

        }
    }

    private ArrayList<InputStream> getInputStreamFromUri(ArrayList<String> photoInputStreamList) {
        ArrayList<InputStream> inputStreamList = new ArrayList<>();
        InputStream is;
        ContentResolver resolver;
        try {

            for (String str : photoInputStreamList) {
                resolver = getContentResolver();
                is = resolver.openInputStream(Uri.fromFile(new File(str)));
                inputStreamList.add(is);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStreamList;
    }

    public void addPhotoToAlbum(final ArrayList<InputStream> selectedPhotos) {

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
}
