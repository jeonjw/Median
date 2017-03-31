package com.ajou.jinwoo.median;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MediaNoticeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NoticeAdapter noticeAdapter;
    private List<Notice> noticeList;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_notice, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.media_notice_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        loadNoticeData();

        noticeList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        updateUI();

        return view;
    }

    private void updateUI() {
        noticeAdapter = new NoticeAdapter(noticeList);
        mRecyclerView.setAdapter(noticeAdapter);
    }

    private void loadNoticeData() {
        mDatabase.child("media_notice").addListenerForSingleValueEvent(new ValueEventListener() {

            List<String> titleList = new ArrayList<>();
            List<String> contentsList = new ArrayList<>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child("title").getChildren()) {
                    titleList.add(ds.getValue().toString());
                }
                for (DataSnapshot ds : dataSnapshot.child("contents").getChildren()) {
                    contentsList.add(ds.getValue().toString());
                }
                for (int i = 0; i < dataSnapshot.child("title").getChildrenCount(); i++) {
                    Notice notice = new Notice(titleList.get(i), contentsList.get(i));
                    noticeList.add(notice);
                }

                noticeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


    private class NoticeHolder extends RecyclerView.ViewHolder  {
        private TextView mTitleTextView;
        private TextView mContentsTextView;
        private Notice mNotice;


        public NoticeHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.media_notice_title_text_view);
            mContentsTextView = (TextView) itemView.findViewById(R.id.media_notice_contents_text_view);

        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        private void bindNotice(Notice notice) {
            mNotice = notice;
            mTitleTextView.setText(mNotice.getTitle());
            if (Build.VERSION.SDK_INT >= 24) {
                mContentsTextView.setText(Html.fromHtml(mNotice.getContents(),Html.FROM_HTML_MODE_COMPACT));
            } else {
                mContentsTextView.setText(Html.fromHtml(mNotice.getContents()));
            }
        }


    }

    private class NoticeAdapter extends RecyclerView.Adapter<NoticeHolder> {
        private List<Notice> mNoticeList;

        public NoticeAdapter(List<Notice> noticeList) {
            mNoticeList = noticeList;
        }

        @Override
        public NoticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_media_notice, parent, false);

            return new NoticeHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onBindViewHolder(NoticeHolder holder, int position) {
            Notice notice = mNoticeList.get(position);
            holder.bindNotice(notice);
        }

        @Override
        public int getItemCount() {
            return mNoticeList.size();
        }
    }
}
