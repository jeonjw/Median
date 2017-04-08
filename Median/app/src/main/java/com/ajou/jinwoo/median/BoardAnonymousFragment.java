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


public class BoardAnonymousFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private AnonymousAdapter anonymousAdapter;
    private Anonymous mAnonymous;
    private List<Anonymous> anonymousList;
    private DatabaseReference mDatabase;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board_anonymous, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.board_anonymous_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        anonymousList = new ArrayList<>();

        loadNoticeData();

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        anonymousAdapter = new AnonymousAdapter(anonymousList);
        mRecyclerView.setAdapter(anonymousAdapter);

        return view;
    }

    private void loadNoticeData(){
        mDatabase.child("익명자유").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                   Anonymous anonymous = ds.getValue(Anonymous.class);
                    anonymousList.add(anonymous);
                }
                anonymousAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private class AnonymousHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mContentsTextView;

        public AnonymousHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.board_anonymous_title_text_view);
            mContentsTextView = (TextView) itemView.findViewById(R.id.board_anonymous_contents_text_view);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }

        public void bindNotice(Anonymous anonymous){
            mAnonymous = anonymous;
            mTitleTextView.setText(mAnonymous.getTitle());
            mContentsTextView.setText(mAnonymous.getContents());
        }
    }

    private class AnonymousAdapter extends RecyclerView.Adapter<AnonymousHolder>{
        private List<Anonymous> mAnonymousList;

        public AnonymousAdapter(List<Anonymous> mAnonymousList) {
            this.mAnonymousList = mAnonymousList;
        }

        @Override
        public AnonymousHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            final View view = layoutInflater.inflate(R.layout.list_item_anonymous, parent, false);

            return  new AnonymousHolder(view);
        }

        @Override
        public void onBindViewHolder(AnonymousHolder holder, int position) {
            Anonymous anonymous = mAnonymousList.get(position);
            holder.bindNotice(anonymous);
        }

        @Override
        public int getItemCount() {
            return mAnonymousList.size();
        }
    }
}
