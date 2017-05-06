package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoo.median.model.Post;
import com.ajou.jinwoo.median.viewholder.PostViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public abstract class BaseBoardFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board_anonymous, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.board_anonymous_recycler_view);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mManager.scrollToPositionWithOffset(0,0);
        recyclerView.setLayoutManager(mManager);

        FirebaseRecyclerAdapter<Post, PostViewHolder> mAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.list_item_post,
                PostViewHolder.class, getRef(mDatabase)) {
            @Override
            protected void populateViewHolder(final PostViewHolder viewHolder, final Post model, final int position) {
                final DatabaseReference postRef = getRef(position);

                final String postKey = postRef.getKey();
                viewHolder.bindPost(model, getContext(), postKey, getPostType());

            }
        };

        recyclerView.setAdapter(mAdapter);

        return view;
    }

    public abstract DatabaseReference getRef(DatabaseReference databaseReference);
    public abstract String getPostType();

}
