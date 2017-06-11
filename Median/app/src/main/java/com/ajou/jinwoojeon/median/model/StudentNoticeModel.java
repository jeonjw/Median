package com.ajou.jinwoojeon.median.model;

import android.support.annotation.NonNull;

import com.ajou.jinwoojeon.median.valueObject.StudentNotice;
import com.ajou.jinwoojeon.median.valueObject.User;
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

public class StudentNoticeModel {
    private DatabaseReference databaseReference;
    private OnNoticeChangeListener<StudentNotice> onNoticeChangeListener;
    private List<String> urlList;
    private List<StudentNotice> dataList;
    private List<String> keyList;


    public List<StudentNotice> getDataList() {
        return dataList;
    }

    public void setOnDataChangedListener(OnNoticeChangeListener<StudentNotice> listener) {
        this.onNoticeChangeListener = listener;
    }

    public StudentNoticeModel() {
        urlList = new ArrayList<>();
        dataList = new ArrayList<>();
        keyList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("student_notice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataList.clear();
                keyList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    StudentNotice studentNotice = ds.getValue(StudentNotice.class);
                    dataList.add(studentNotice);
                    keyList.add(ds.getKey());
                }
                if (onNoticeChangeListener != null)
                    onNoticeChangeListener.onChange(dataList, keyList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void removeNotice(String dataRefKey) {
        databaseReference.child("comments").child(dataRefKey).removeValue();
        databaseReference.child("student_notice").child(dataRefKey).removeValue();
    }

    public void writeNotice(String title, String contents) {
        System.out.println("작성");
        databaseReference.child("student_notice").push().setValue(StudentNotice.newNotice(User.getInstance().getUserName(), title, contents, 0));
        new NotificationPostModel("학생회 공지가 등록됬습니다", title).execute();
    }

    public void writeNoticeWithImage(String title, String contents, List<String> urlList) {
        System.out.println("이미지 작성");
        databaseReference.child("student_notice").push().setValue(StudentNotice.newNoticeWithImages(User.getInstance().getUserName(), title, contents, 0, urlList));
        new NotificationPostModel("학생회 공지가 등록됬습니다", title).execute();
    }

    public void correctNotice(String title, String contents, String postKey, int commentCount) {
        System.out.println("수정");
        databaseReference.child("student_notice").
                child(postKey).setValue(StudentNotice.newNotice(User.getInstance().getUserName(), title, contents, commentCount));
    }

    public void correctNoticeWithImages(String title, String contents, String postKey, int commentCount, List<String> urlList) {
        System.out.println("이미지 수정");
        databaseReference.child("student_notice").
                child(postKey).setValue(StudentNotice.newNoticeWithImages(User.getInstance().getUserName(), title, contents, commentCount, urlList));
    }

    public void uploadImages(final ArrayList<InputStream> selectedPhotos, final OnUploadImageListener listener) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://median-234c4.appspot.com").child("noticeImages");

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


//    public FirebaseRecyclerAdapter setAdapter(Query query) {
//        FirebaseRecyclerAdapter<StudentNotice, StudentNoticeViewHolder> adapter = new FirebaseRecyclerAdapter<StudentNotice, StudentNoticeViewHolder>(StudentNotice.class, R.layout.list_item_student_notice,
//                StudentNoticeViewHolder.class, query) {
//            @Override
//            protected void populateViewHolder(StudentNoticeViewHolder viewHolder, StudentNotice model, int position) {
//                DatabaseReference postRef = getRef(position);
//                String postKey = postRef.getKey();
//                viewHolder.bindNotice(model, postKey);
//
//            }
//        };
//
//
//        return adapter;
//    }

    private String generateTempFilename() {
        return UUID.randomUUID().toString();
    }

}
