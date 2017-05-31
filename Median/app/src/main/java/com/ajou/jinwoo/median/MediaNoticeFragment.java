package com.ajou.jinwoo.median;


import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoo.median.valueObject.Notice;
import com.ajou.jinwoo.median.viewholder.MediaNoticeViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MediaNoticeFragment extends Fragment {

    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private NoticeAdapter noticeAdapter;
    private List<Notice> searchedList;
    private List<Notice> dataList;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_notice, container, false);
        searchedList = new ArrayList<>();
        dataList = new ArrayList<>();
        noticeAdapter = new NoticeAdapter();

        showProgressDialog();

        recyclerView = (RecyclerView) view.findViewById(R.id.media_notice_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setHasOptionsMenu(true);

        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
//        mManager.setStackFromEnd(true);
        mManager.scrollToPositionWithOffset(0, 0);
        recyclerView.setLayoutManager(mManager);

        readLimitFirstData();

        return view;
    }

    private void readLimitFirstData() {
        mDatabase.child("media_notice").orderByChild("boardNum").limitToLast(8).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Notice notice = ds.getValue(Notice.class);
                    dataList.add(notice);
                }

                noticeAdapter.setList(dataList);
                progressDialog.dismiss();
                recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount()-1);
                readFullData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        recyclerView.setAdapter(noticeAdapter);


    }

    private void readFullData() {
        mDatabase.child("media_notice").orderByChild("boardNum").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Notice notice = ds.getValue(Notice.class);
                    dataList.add(notice);
                }
                noticeAdapter.setList(dataList);
                recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount()-1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        recyclerView.setAdapter(noticeAdapter);
    }


    private class NoticeAdapter extends RecyclerView.Adapter<MediaNoticeViewHolder> {
        private List<Notice> noticeList;

        public NoticeAdapter() {
            noticeList = new ArrayList<>();
        }

        public void setList(List<Notice> searchedList) {
            noticeList = searchedList;
            notifyDataSetChanged();
        }

        @Override
        public MediaNoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            final View view = layoutInflater.inflate(R.layout.list_item_media_notice, parent, false);

            return new MediaNoticeViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onBindViewHolder(final MediaNoticeViewHolder holder, int position) {
            Notice notice = noticeList.get(position);
            holder.bindNotice(notice, getContext());
        }

        @Override
        public int getItemCount() {
            return noticeList.size();
        }
    }

    public void filter(String text) {
        searchedList.clear();

        if (text.isEmpty()) {
            searchedList.addAll(dataList);
        } else {
            text = text.toLowerCase();
            for (Notice item : dataList) {
                if (item.getContents().toLowerCase().contains(text) || item.getTitle().toLowerCase().contains(text)) {
                    searchedList.add(item);
                }
            }
        }

        noticeAdapter.setList(searchedList);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_write).setVisible(false);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                filter(s);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        noticeAdapter.setList(dataList);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
    }
}
