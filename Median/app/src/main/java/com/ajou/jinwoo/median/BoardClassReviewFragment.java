package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BoardClassReviewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ClassReviewAdapter classReviewAdapter;
    private LectureReview mLectureReview;
    private List<Integer> boardNumberList;
    private List<String> titleList;
    private List<Integer> commentNumberList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_notice, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.board_class_review_recycler_view);
//        mRecyclerView.setAdapter(classReviewAdapter);

        boardNumberList = new ArrayList<>();
        titleList = new ArrayList<>();
        commentNumberList = new ArrayList<>();

        return view;
    }

    public void Data(){


    }

    private class ClassReviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mBoardNumberTextView;
        private TextView mTitleTextView;
        private TextView mCommentNumberTextView;
        private boolean isClicked;

        public ClassReviewHolder(View itemView) {
            super(itemView);

            mBoardNumberTextView = (TextView) itemView.findViewById(R.id.board_class_review_boardNumber_text_view);
            mTitleTextView = (TextView) itemView.findViewById(R.id.board_class_review_title_text_view);
            mCommentNumberTextView = (TextView) itemView.findViewById(R.id.board_class_review_comment_number_text_view);

            isClicked = false;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindNotice(LectureReview lectureReview) {
            mLectureReview = lectureReview;
            mBoardNumberTextView.setText(mLectureReview.getBoardNumber());
            mTitleTextView.setText(mLectureReview.getTitle());
            mCommentNumberTextView.setText(mLectureReview.getCommentNumber());
        }
    }

    private class ClassReviewAdapter extends RecyclerView.Adapter<ClassReviewHolder>{
        private List<LectureReview> mLectureReviewList;

        public ClassReviewAdapter(List<LectureReview> mLectureReviewList) {
            this.mLectureReviewList = mLectureReviewList;
        }

        @Override
        public ClassReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            final View view = layoutInflater.inflate(R.layout.list_item_lecture_review, parent, false);

            return  new ClassReviewHolder(view);
        }

        @Override
        public void onBindViewHolder(ClassReviewHolder holder, int position) {
            LectureReview lectureReview = mLectureReviewList.get(position);
            holder.bindNotice(lectureReview);
        }

        @Override
        public int getItemCount() {
            return mLectureReviewList.size();
        }
    }
}
