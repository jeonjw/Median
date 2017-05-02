package com.ajou.jinwoo.median;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoo.median.model.StudentNotice;
import com.ajou.jinwoo.median.viewholder.StudentNoticeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StudentNoticeFragment extends Fragment {
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_notice, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.student_notice_recycler_view);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mManager.scrollToPositionWithOffset(0,0);
        mRecyclerView.setLayoutManager(mManager);

        FirebaseRecyclerAdapter<StudentNotice, StudentNoticeViewHolder> mAdapter = new FirebaseRecyclerAdapter<StudentNotice, StudentNoticeViewHolder>(StudentNotice.class, R.layout.list_item_student_notice,
                StudentNoticeViewHolder.class, mDatabase.child("student_notice")) {
            @Override
            protected void populateViewHolder(final StudentNoticeViewHolder viewHolder, final StudentNotice model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
//                Log.d("Firebase",postKey);
                viewHolder.bindNotice(model);
                progressDialog.dismiss();

            }
        };

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


}
