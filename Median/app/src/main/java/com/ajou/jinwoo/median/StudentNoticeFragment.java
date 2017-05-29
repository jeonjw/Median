package com.ajou.jinwoo.median;

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

import com.ajou.jinwoo.median.model.OnDataChangedListener;
import com.ajou.jinwoo.median.model.StudentNoticeModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class StudentNoticeFragment extends Fragment {
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_notice, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.student_notice_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        setHasOptionsMenu(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
//        linearLayoutManager.scrollToPositionWithOffset(0, 0);

        recyclerView.setLayoutManager(linearLayoutManager);

        setAdapter(mDatabase.child("student_notice"));

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("제목으로 검색");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Query query = mDatabase.child("student_notice").orderByChild("title").startAt(s).endAt(s + "\uf8ff");
                setAdapter(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() == 0)
                    setAdapter(mDatabase.child("student_notice"));
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        setAdapter(mDatabase.child("student_notice"));
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {

                        return true;
                    }
                });
    }

    public void setAdapter(Query query) {
        StudentNoticeModel studentNoticeModel = new StudentNoticeModel();
        studentNoticeModel.setAdapter(query,getContext());
        studentNoticeModel.setOnDataChangedListener(new OnDataChangedListener() {
            @Override
            public void onDataChanged() {
                recyclerView.getLayoutManager().scrollToPosition(recyclerView.getAdapter().getItemCount()-1);//새글 작성시 스크롤 최상단으로 이동
            }
        });
        recyclerView.setAdapter(studentNoticeModel.setAdapter(query,getContext()));
    }
}
