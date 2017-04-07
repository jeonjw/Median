package com.ajou.jinwoo.median;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.Collections;
import java.util.List;


public class StudentNoticeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private StudentNoticeAdapter studentNoticeAdapter;
    private List<StudentNotice> studentNoticeList;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_notice, container, false);
//        setHasOptionsMenu(true);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.student_notice_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        studentNoticeList = new ArrayList<>();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        loadNoticeData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        studentNoticeAdapter = new StudentNoticeAdapter(studentNoticeList);
        mRecyclerView.setAdapter(studentNoticeAdapter);


        return view;
    }

    private void loadNoticeData() {
        progressDialog.setMessage("Loading . .");
        progressDialog.show();
        mDatabase.child("student_notice").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    StudentNotice studentNotice = ds.getValue(StudentNotice.class);
                    studentNoticeList.add(studentNotice);
                }

                studentNoticeAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private class StudentNoticeHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mContentsTextView;
        private StudentNotice mStudentNotice;


        public StudentNoticeHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.media_notice_title_text_view);
            mContentsTextView = (TextView) itemView.findViewById(R.id.media_notice_contents_text_view);
        }


        private void bindNotice(StudentNotice studentNotice) {
            mStudentNotice = studentNotice;
            mTitleTextView.setText(mStudentNotice.getTitle());
            mContentsTextView.setText(mStudentNotice.getContents());

        }
    }

    private class StudentNoticeAdapter extends RecyclerView.Adapter<StudentNoticeHolder> {
        private List<StudentNotice> mStudentNoticeList;

        public StudentNoticeAdapter(List<StudentNotice> noticeList) {
            mStudentNoticeList = noticeList;
        }

        @Override
        public StudentNoticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            final View view = layoutInflater.inflate(R.layout.list_item_media_notice, parent, false);

            return new StudentNoticeHolder(view);
        }

        @Override
        public void onBindViewHolder(final StudentNoticeHolder holder, int position) {
            StudentNotice studentNotice = mStudentNoticeList.get(position);
            holder.bindNotice(studentNotice);
        }

        @Override
        public int getItemCount() {
            return mStudentNoticeList.size();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("student");

        if (item.getItemId() == R.id.menu_notice_write) {
            FragmentManager fm = getFragmentManager();
            Fragment writeFragment = new StudentNoticeWriteFragment();
            fm.beginTransaction().replace(R.id.student_notice_container, writeFragment).setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
        } else if (item.getItemId() == R.id.menu_search) {
            Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.menu_notice_write).setVisible(true);
//        menu.findItem(R.id.menu_search).setVisible(true);
//    }


}
