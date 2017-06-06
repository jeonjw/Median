package com.ajou.jinwoo.median.model;

import com.ajou.jinwoo.median.valueObject.MediaNotice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MediaNoticeModel {
    private DatabaseReference databaseReference;
    private List<MediaNotice> searchedList;

    public List<MediaNotice> getDataList() {
        return dataList;
    }

    private List<MediaNotice> dataList;

    public MediaNoticeModel() {
        dataList = new ArrayList<>();
        searchedList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }



    public void readLimitFirstData(final OnDataLoadListener listener) {
        databaseReference.child("media_notice").orderByChild("boardNum").limitToLast(8).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MediaNotice mediaNotice = ds.getValue(MediaNotice.class);
                    dataList.add(mediaNotice);
                }

                listener.onLoaded(dataList);
                readFullData(listener);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void readFullData(final OnDataLoadListener listener) {
        databaseReference.child("media_notice").orderByChild("boardNum").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MediaNotice mediaNotice = ds.getValue(MediaNotice.class);
                    dataList.add(mediaNotice);
                }
                listener.onLoaded(dataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
