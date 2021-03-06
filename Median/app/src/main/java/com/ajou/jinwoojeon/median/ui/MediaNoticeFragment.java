package com.ajou.jinwoojeon.median.ui;


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
import android.widget.Toast;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.model.MediaNoticeModel;
import com.ajou.jinwoojeon.median.model.OnDataChangedListener;
import com.ajou.jinwoojeon.median.valueObject.MediaNotice;

import java.util.ArrayList;
import java.util.List;

public class MediaNoticeFragment extends Fragment {

    private ProgressDialog progressDialog;
    private MediaNoticeModel mediaNoticeModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_notice, container, false);
        setHasOptionsMenu(true);
        progressDialog = new ProgressDialog(getActivity());
        showProgressDialog();

        final RecyclerView recyclerView = view.findViewById(R.id.media_notice_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.scrollToPositionWithOffset(0, 0);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        mediaNoticeModel = new MediaNoticeModel();
        recyclerView.setAdapter(mediaNoticeModel.getMediaNoticeAdapter());
        recyclerView.setLayoutManager(linearLayoutManager);

        mediaNoticeModel.setOnDataChangedListener(new OnDataChangedListener() {
            @Override
            public void onDataChanged() {
                recyclerView.getLayoutManager().scrollToPosition(recyclerView.getAdapter().getItemCount()-1);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });


        return view;
    }


    public void filter(String text) {
        text = text.toLowerCase();
        if (!mediaNoticeModel.search(text)) {
            Toast.makeText(getContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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

       searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        showProgressDialog();
                        mediaNoticeModel.readNotice();
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });
    }

    private void showProgressDialog() {
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
    }


}
