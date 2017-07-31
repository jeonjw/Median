package com.ajou.jinwoojeon.median.model;

import android.support.annotation.NonNull;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.valueObject.Post;
import com.ajou.jinwoojeon.median.viewholder.PostViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostModel {
    private DatabaseReference databaseReference;
    private OnDataChangedListener onDataChangedListener;
    private List<String> urlList;

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }


    public PostModel(String postType) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        urlList = new ArrayList<>();

        databaseReference.child(postType).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public void writePost(String postType, String userName, String title, String contents) {
        databaseReference.child(postType).
                push().setValue(Post.newPost(userName, title, contents, 0));
    }

    public void writePostWithImage(String postType, String userName, String title, String contents, List<String> urlList) {
        databaseReference.child(postType).push().setValue(Post.newPostWithImages(userName, title, contents, 0, urlList));
//        new NotificationPostModel("학생회 공지가 등록됬습니다", title).execute();
    }

    public void correctPost(String postType, String userName, String title, String contents, String postKey, int commentCount) {
        databaseReference.child(postType).
                child(postKey).setValue(Post.newPost(userName, title, contents, commentCount));

    }

    public void correctPostWithImages(String postType, String userName, String title, String contents, String postKey, int commentCount, List<String> urlList) {
        databaseReference.child(postType).
                child(postKey).setValue(Post.newPostWithImages(userName, title, contents, commentCount, urlList));
    }

    public FirebaseRecyclerAdapter setAdapter(final Query query, final String postType) {

        FirebaseRecyclerAdapter<Post, PostViewHolder> adapter =
                new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                        Post.class,
                        R.layout.list_item_post,
                        PostViewHolder.class, query) {
                    @Override
                    protected void populateViewHolder(PostViewHolder viewHolder, Post post, int position) {
                        DatabaseReference postRef = getRef(position);
                        String postKey = postRef.getKey();
                        viewHolder.bindPost(post, postKey, postType);
                    }
                };
        return adapter;
    }


    public void uploadImages(final List<InputStream> selectedPhotos, final OnUploadImageListener listener) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://median-234c4.appspot.com").child("postImages");

        for (InputStream inputStream : selectedPhotos) {
            UploadTask uploadTask = storageReference.child(generateTempFilename()).putStream(inputStream);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    urlList.add(taskSnapshot.getDownloadUrl().toString());

                    if (urlList.size() == selectedPhotos.size())
                        listener.onSuccess(urlList);

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
