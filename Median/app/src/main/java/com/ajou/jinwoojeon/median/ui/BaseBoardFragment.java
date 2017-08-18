package com.ajou.jinwoojeon.median.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.model.PostModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


public abstract class BaseBoardFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_base_board, container, false);
        recyclerView = view.findViewById(R.id.board_recycler_view);
        setHasOptionsMenu(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.scrollToPositionWithOffset(0, 0);
        recyclerView.setLayoutManager(linearLayoutManager);

        setAdapter(getRef());

        return view;
    }

    public abstract DatabaseReference getRef();

    public abstract String getPostType();

    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                Query query = getRef().orderByChild("title").startAt(text).endAt(text + "\uf8ff");
                setAdapter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        setAdapter(getRef());
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });
    }

    public void setAdapter(Query query) {
        PostModel postModel = new PostModel(getPostType());
        recyclerView.setAdapter(postModel.setAdapter(query, getPostType()));
    }


}