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


public class BoardTextbookFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TextbookAdapter TextbookAdapter;
    private Textbook mTextbook;
    private List<Textbook> TextbookList;
    private DatabaseReference mDatabase;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_textbook, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.board_textbook_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        TextbookList = new ArrayList<>();

        loadNoticeData();

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        TextbookAdapter = new TextbookAdapter(TextbookList);
        mRecyclerView.setAdapter(TextbookAdapter);

        return view;
    }

    private void loadNoticeData(){
        mDatabase.child("교재장터").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Textbook textbook = ds.getValue(Textbook.class);
                    TextbookList.add(textbook);
                }
                TextbookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private class TextbookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mContentsTextView;

        public TextbookHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.board_textbook_title_text_view);
            mContentsTextView = (TextView) itemView.findViewById(R.id.board_textbook_contents_text_view);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }

        public void bindNotice(Textbook textbook){
            mTextbook = textbook;
            mTitleTextView.setText(mTextbook.getTitle());
            mContentsTextView.setText(mTextbook.getContents());
        }
    }

    private class TextbookAdapter extends RecyclerView.Adapter<TextbookHolder>{
        private List<Textbook> mTextbookList;

        public TextbookAdapter(List<Textbook> mTextbookList) {
            this.mTextbookList = mTextbookList;
        }

        @Override
        public TextbookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            final View view = layoutInflater.inflate(R.layout.list_item_textbook, parent, false);

            return  new TextbookHolder(view);
        }

        @Override
        public void onBindViewHolder(TextbookHolder holder, int position) {
            Textbook textbook = mTextbookList.get(position);
            holder.bindNotice(textbook);
        }

        @Override
        public int getItemCount() {
            return mTextbookList.size();
        }
    }
}
