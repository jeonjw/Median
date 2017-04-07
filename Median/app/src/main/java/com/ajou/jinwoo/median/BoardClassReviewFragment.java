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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BoardClassReviewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LectureReviewAdapter lectureReviewAdapter;
    private LectureReview mLectureReview;
    private List<LectureReview> lectureReviewList;
    private DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_notice, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.board_lecture_review_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        lectureReviewList = new ArrayList<>();

        loadNoticeData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        lectureReviewAdapter = new LectureReviewAdapter(lectureReviewList);
        mRecyclerView.setAdapter(lectureReviewAdapter);

        return view;
    }

    private void loadNoticeData(){
        mDatabase.child("수업후기").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LectureReview lectureReview = ds.getValue(LectureReview.class);
                    lectureReviewList.add(lectureReview);
                }
                lectureReviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private class LectureReviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mContentsView;

        public LectureReviewHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.board_lecture_review_title_text_view);
            mContentsView = (TextView) itemView.findViewById(R.id.board_lecture_review_contents_text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindNotice(LectureReview lectureReview) {
            mLectureReview = lectureReview;
            mTitleTextView.setText(mLectureReview.getTitle());
            mContentsView.setText(mLectureReview.getContents());
        }
    }

    private class LectureReviewAdapter extends RecyclerView.Adapter<LectureReviewHolder>{
        private List<LectureReview> mLectureReviewList;

        public LectureReviewAdapter(List<LectureReview> mLectureReviewList) {
            this.mLectureReviewList = mLectureReviewList;
        }

        @Override
        public LectureReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            final View view = layoutInflater.inflate(R.layout.list_item_lecture_review, parent, false);

            return  new LectureReviewHolder(view);
        }

        @Override
        public void onBindViewHolder(LectureReviewHolder holder, int position) {
            LectureReview lectureReview = mLectureReviewList.get(position);
            holder.bindNotice(lectureReview);
        }

        @Override
        public int getItemCount() {
            return mLectureReviewList.size();
        }
    }
}
