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

    public MediaNoticeModel() {
        dataList = new ArrayList<>();
        tempList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mediaNoticeAdapter = new MediaNoticeAdapter();
        readNotice();
    }

    public void loadFullData() {
        System.out.println("TEST loadFullData"+dataList.size());
        mediaNoticeAdapter.setDataList(dataList);
    }

    public MediaNoticeAdapter getMediaNoticeAdapter() {
        return mediaNoticeAdapter;
    }


//    public void readNotice(int count) {
//        tempList.clear();
//        databaseReference.child("media_notice").orderByChild("boardNum").startAt(676 - count).endAt(682 - count).addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    MediaNotice mediaNotice = ds.getValue(MediaNotice.class);
//                    tempList.add(0, mediaNotice);
//                }
//
//                dataList.addAll(tempList);
//                mediaNoticeAdapter.addMoreData(tempList);
//                onDataChangedListener.onDataChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//    }

    public void readNotice() {

        databaseReference.child("media_notice").orderByChild("boardNum").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MediaNotice mediaNotice = ds.getValue(MediaNotice.class);
                    dataList.add(mediaNotice);
                }

                mediaNoticeAdapter.setDataList(dataList);
                onDataChangedListener.onDataChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


    public boolean search(String text) {
        System.out.println("TEST SEARCH");
        tempList.clear();

        for(MediaNotice mediaNotice : dataList) {
            if (mediaNotice.getContents().toLowerCase().contains(text)
                    || mediaNotice.getTitle().toLowerCase().contains(text)) {
                tempList.add(mediaNotice);
            }
        }

        if (tempList.size() == 0)
            return false;
        else {
            mediaNoticeAdapter.setDataList(tempList);
            return true;
        }
    }


}
