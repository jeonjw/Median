package com.ajou.jinwoo.median.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.Post;
import com.ajou.jinwoo.median.valueObject.StudentNotice;
import com.ajou.jinwoo.median.valueObject.User;
import com.ajou.jinwoo.median.viewholder.StudentNoticeViewHolder;
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

public class StudentNoticeModel {
    private DatabaseReference databaseReference;
    private OnDataChangedListener onDataChangedListener;
    private OnStorageSucceedListener onStorageSucceedListener;
    private List<String> urlList = new ArrayList<>();
    private DatabaseReference ref;


    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }

    public void setOnStorageSucceedListener(OnStorageSucceedListener listener) {
        this.onStorageSucceedListener = listener;
    }

    public StudentNoticeModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("student_notice").addValueEventListener(new ValueEventListener() {
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

        setOnStorageSucceedListener(new OnStorageSucceedListener() {
            @Override
            public void onTransferSucceed() {
                ref.child("urlList").setValue(urlList);
            }
        });
    }


    public void writeNotice(String title, String contents) {

        ref = databaseReference.child("student_notice").push();
        ref.setValue(StudentNotice.newNotice(User.getInstance().getUserName(), title, contents, 0));
        new NotificationPostModel("학생회 공지가 등록됬습니다", title).execute();
    }

    public void correctNotice(String title, String contents, String postKey, int commentCount) {
        databaseReference.child("student_notice").
                child(postKey).setValue(Post.newPost(User.getInstance().getUserName(), title, contents, commentCount));

    }

    public void writeNoticeWithImage(String title, String contents, final ArrayList<InputStream> selectedPhotos) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://median-234c4.appspot.com").child("noticeImages");

        for (InputStream inputStream : selectedPhotos) {
            UploadTask uploadTask = storageReference.child(generateTempFilename()).putStream(inputStream);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    urlList.add(taskSnapshot.getDownloadUrl().toString());

                    if (urlList.size() == selectedPhotos.size())
                        onStorageSucceedListener.onTransferSucceed();


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }
        writeNotice(title, contents);


    }

    public FirebaseRecyclerAdapter setAdapter(Query query, final Context context) {
        FirebaseRecyclerAdapter<StudentNotice, StudentNoticeViewHolder> adapter = new FirebaseRecyclerAdapter<StudentNotice, StudentNoticeViewHolder>(StudentNotice.class, R.layout.list_item_student_notice,
                StudentNoticeViewHolder.class, query) {
            @Override
            protected void populateViewHolder(StudentNoticeViewHolder viewHolder, StudentNotice model, int position) {
                DatabaseReference postRef = getRef(position);
                String postKey = postRef.getKey();
                viewHolder.bindNotice(model, context, postKey);
            }
        };

        return adapter;
    }

    private String generateTempFilename() {
        return UUID.randomUUID().toString();
    }


}
