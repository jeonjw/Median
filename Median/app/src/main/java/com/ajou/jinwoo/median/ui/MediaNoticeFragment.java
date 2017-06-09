package com.ajou.jinwoo.median.ui;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.adapter.MediaNoticeAdapter;
import com.ajou.jinwoo.median.model.MediaNoticeModel;
import com.ajou.jinwoo.median.model.OnNoticeChangeListener;
import com.ajou.jinwoo.median.valueObject.MediaNotice;

import java.util.ArrayList;
import java.util.List;

public class MediaNoticeFragment extends Fragment {

    private ProgressDialog progressDialog;
    private MediaNoticeAdapter mediaNoticeAdapter;
    private List<MediaNotice> searchedList;
    private RecyclerView recyclerView;
    private MediaNoticeModel mediaNoticeModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_notice, container, false);
        searchedList = new ArrayList<>();
        mediaNoticeAdapter = new MediaNoticeAdapter();

        recyclerView = (RecyclerView) view.findViewById(R.id.media_notice_recycler_view);
        mediaNoticeModel = new MediaNoticeModel();

        setHasOptionsMenu(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.scrollToPositionWithOffset(0, 0);

        recyclerView.setAdapter(mediaNoticeAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        showProgressDialog();

        mediaNoticeModel.readLimitFirstData(new OnNoticeChangeListener<MediaNotice>() {

            @Override
            public void onChange(List noticeList, List keyList) {
                mediaNoticeAdapter.setList(noticeList);
                recyclerView.getLayoutManager().scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }

        });


        return view;
    }


    public void filter(String text) {
        searchedList.clear();

        text = text.toLowerCase();
        for (MediaNotice item : mediaNoticeModel.getDataList()) {
            if (item.getContents().toLowerCase().contains(text) || item.getTitle().toLowerCase().contains(text)) {
                searchedList.add(item);
            }
        }

        if (searchedList.size() == 0) {
            Snackbar.make(getView(), "검색 결과가 없습니다.", Snackbar.LENGTH_SHORT).show();
            mediaNoticeAdapter.setList(mediaNoticeModel.getDataList());
        } else
            mediaNoticeAdapter.setList(searchedList);
    }


    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        menu.findItem(R.id.menu_write).setVisible(false);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        mediaNoticeAdapter.setList(mediaNoticeModel.getDataList());
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
