package com.ajou.jinwoo.median;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoo.median.model.Post;
import com.ajou.jinwoo.median.viewholder.PostViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public abstract class BaseBoardFragment extends Fragment {
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_base_board, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.board_anonymous_recycler_view);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setHasOptionsMenu(true);
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mManager.scrollToPositionWithOffset(0,0);
        recyclerView.setLayoutManager(mManager);


        mAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.list_item_post,
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

    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_board, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                Query query = getRef(mDatabase).orderByChild("title").startAt(s);
//
//                FirebaseRecyclerAdapter<Post, PostViewHolder> adapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.list_item_post,
//                        PostViewHolder.class, query) {
//                    @Override
//                    protected void populateViewHolder(final PostViewHolder viewHolder, final Post model, final int position) {
//                        final DatabaseReference postRef = getRef(position);
//
//                        final String postKey = postRef.getKey();
//                        viewHolder.bindPost(model, getContext(), postKey, getPostType());
//                    }
//                };
//
//                recyclerView.setAdapter(adapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println(s);
//                query = getRef(mDatabase).child("title").startAt(s);

                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener()
                {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item)
                    {
                        recyclerView.setAdapter(mAdapter);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item)
                    {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_write) {
            Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
            intent.putExtra("CURRENT_BOARD_TAB", BoardFragment.getCurrentTab());
            startActivity(intent);
        }
        return true;
    }
}
//따로 어댑터를 만들고 http://stackoverflow.com/questions/30398247/how-to-filter-a-recyclerview-with-a-searchview
//참고해서 필터 만들어서 해야겠다