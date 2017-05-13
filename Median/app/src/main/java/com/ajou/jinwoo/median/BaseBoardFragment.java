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
import android.widget.Toast;

import com.ajou.jinwoo.median.valueObject.Post;
import com.ajou.jinwoo.median.viewholder.PostViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public abstract class BaseBoardFragment extends Fragment {
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;

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
        mManager.scrollToPositionWithOffset(0, 0);
        recyclerView.setLayoutManager(mManager);

        setAdapter(getRef(mDatabase));

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
        searchView.setQueryHint("제목으로 검색");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Query query = getRef(mDatabase).orderByChild("title").startAt(s).endAt(s + "\uf8ff");
                setAdapter(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() == 0)
                    setAdapter(getRef(mDatabase));
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        setAdapter(getRef(mDatabase));
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_write) {
            Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
            intent.putExtra("CURRENT_BOARD_TAB", BoardTabFragment.getCurrentTab());
            startActivity(intent);
        }
        return true;
    }

    public void setAdapter(Query query) {
        FirebaseRecyclerAdapter<Post, PostViewHolder> adapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.list_item_post,
                PostViewHolder.class, query) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {
                DatabaseReference postRef = getRef(position);
                String postKey = postRef.getKey();
                viewHolder.bindPost(model, getContext(), postKey, getPostType());
            }
        };

        recyclerView.setAdapter(adapter);
    }
}