package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajou.jinwoo.median.model.QA;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BoardQAFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private QAAdapter QAAdapter;
    private QA mQA;
    private List<QA> QAList;
    private DatabaseReference mDatabase;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board_qa, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.board_qa_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        QAList = new ArrayList<>();

        loadNoticeData();

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        QAAdapter = new QAAdapter(QAList);
        mRecyclerView.setAdapter(QAAdapter);

        return view;
    }

    private void loadNoticeData(){
        mDatabase.child("질문답변").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    QA qa = ds.getValue(QA.class);
                    QAList.add(qa);
                }
                QAAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private class QAHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mContentsTextView;

        public QAHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.board_qa_title_text_view);
            mContentsTextView = (TextView) itemView.findViewById(R.id.board_qa_contents_text_view);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }

        public void bindNotice(QA qa){
            mQA = qa;
            mTitleTextView.setText(mQA.getTitle());
            mContentsTextView.setText(mQA.getContents());
        }
    }

    private class QAAdapter extends RecyclerView.Adapter<QAHolder>{
        private List<QA> mQAList;

        public QAAdapter(List<QA> mQAList) {
            this.mQAList = mQAList;
        }

        @Override
        public QAHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            final View view = layoutInflater.inflate(R.layout.list_item_qa, parent, false);

            return  new QAHolder(view);
        }

        @Override
        public void onBindViewHolder(QAHolder holder, int position) {
            QA qa = mQAList.get(position);
            holder.bindNotice(qa);
        }

        @Override
        public int getItemCount() {
            return mQAList.size();
        }
    }
}