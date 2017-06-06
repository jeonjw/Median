package com.ajou.jinwoo.median;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.ajou.jinwoo.median.Adapter.MediaNoticeAdapter;
import com.ajou.jinwoo.median.model.MediaNoticeModel;
import com.ajou.jinwoo.median.model.OnDataLoadListener;
import com.ajou.jinwoo.median.valueObject.MediaNotice;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.scrollToPositionWithOffset(0, 0);

        recyclerView.setAdapter(mediaNoticeAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        showProgressDialog();

        mediaNoticeModel.readLimitFirstData(new OnDataLoadListener() {
            @Override
            public void onLoaded(List<MediaNotice> dataList) {
                mediaNoticeAdapter.setList(dataList);
                recyclerView.getLayoutManager().scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });

        return view;
    }
    

    public void filter(String text) {
        searchedList.clear();

        if (text.isEmpty()) {
            searchedList.addAll(mediaNoticeModel.getDataList());
        } else {
            text = text.toLowerCase();
            for (MediaNotice item : mediaNoticeModel.getDataList()) {
                if (item.getContents().toLowerCase().contains(text) || item.getTitle().toLowerCase().contains(text)) {
                    searchedList.add(item);
                }
            }
        }

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
