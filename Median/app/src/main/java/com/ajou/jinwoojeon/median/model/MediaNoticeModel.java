package com.ajou.jinwoojeon.median.model;

import com.ajou.jinwoojeon.median.adapter.MediaNoticeAdapter;
import com.ajou.jinwoojeon.median.valueObject.MediaNotice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MediaNoticeModel {
    private DatabaseReference databaseReference;
    private List<MediaNotice> tempList;
    private List<MediaNotice> dataList;
    private OnDataChangedListener onDataChangedListener;
    private MediaNoticeAdapter mediaNoticeAdapter;

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }

    public List<MediaNotice> getDataList() {
        return dataList;
    }

    public MediaNoticeModel() {
        dataList = new ArrayList<>();
        tempList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mediaNoticeAdapter = new MediaNoticeAdapter();
        readNotice(0);
    }

    public void loadFullData(){
        mediaNoticeAdapter.setDataList(dataList);
    }

    public void setSearchList(List<MediaNotice> searchList){
        System.out.println("TESTTT"+searchList.size());
        mediaNoticeAdapter.setSearchList(searchList);
    }

    public MediaNoticeAdapter getMediaNoticeAdapter() {
        return mediaNoticeAdapter;
    }


    public void readNotice(int count){
        tempList.clear();
        databaseReference.child("media_notice").orderByChild("boardNum").startAt(668-count).endAt(674-count).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MediaNotice mediaNotice = ds.getValue(MediaNotice.class);
                    tempList.add(0,mediaNotice);
                }

                dataList.addAll(tempList);
                mediaNoticeAdapter.addMoreData(tempList);
                onDataChangedListener.onDataChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


//    public void readFullData(final OnNoticeChangeListener listener) {
//        databaseReference.child("media_notice").orderByChild("boardNum").addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                dataList.clear();
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    MediaNotice mediaNotice = ds.getValue(MediaNotice.class);
//                    dataList.add(mediaNotice);
//                }
//                listener.onChange(dataList,null);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//
//    }
}
