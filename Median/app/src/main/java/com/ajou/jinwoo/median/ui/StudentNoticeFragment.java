package com.ajou.jinwoo.median.ui;

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
import com.ajou.jinwoo.median.adapter.StudentNoticeAdapter;
import com.ajou.jinwoo.median.model.OnNoticeChangeListener;
import com.ajou.jinwoo.median.model.StudentNoticeModel;
import com.ajou.jinwoo.median.valueObject.StudentNotice;

import java.util.ArrayList;
import java.util.List;


public class StudentNoticeFragment extends Fragment {
    private RecyclerView recyclerView;
    private StudentNoticeAdapter studentNoticeAdapter;
    private List<StudentNotice> searchedList;
    private StudentNoticeModel studentNoticeModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_notice, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.student_notice_recycler_view);
        studentNoticeModel = new StudentNoticeModel();

        studentNoticeModel.setOnDataChangedListener(new OnNoticeChangeListener<StudentNotice>() {
            @Override
            public void onChange(List noticeList, List keyList) {
                studentNoticeAdapter.setDataList(noticeList);
                studentNoticeAdapter.setKeyList(keyList);
                recyclerView.getLayoutManager().scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
            }


//

        });

        studentNoticeAdapter = new StudentNoticeAdapter();
        searchedList = new ArrayList<>();

        setHasOptionsMenu(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.scrollToPositionWithOffset(0, 0);
        

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(studentNoticeAdapter);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("제목으로 검색");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);

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
                        studentNoticeAdapter.setDataList(studentNoticeModel.getDataList());
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {

                        return true;
                    }
                });
    }

    public void filter(String text) {
        searchedList.clear();

        text = text.toLowerCase();
        for (StudentNotice item : studentNoticeModel.getDataList()) {
            if (item.getContents().toLowerCase().contains(text) || item.getTitle().toLowerCase().contains(text)) {
                searchedList.add(item);
            }
        }

        if (searchedList.size() == 0) {
            Snackbar.make(getView(), "검색 결과가 없습니다.", Snackbar.LENGTH_SHORT).show();
            studentNoticeAdapter.setDataList(studentNoticeModel.getDataList());
        } else
            studentNoticeAdapter.setDataList(searchedList);
    }


}
