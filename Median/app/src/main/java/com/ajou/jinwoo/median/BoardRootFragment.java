package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class BoardRootFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_root, container, false);
        FragmentManager fm = getFragmentManager();
        Fragment boardFragment = new BoardFragment();
        fm.beginTransaction().replace(R.id.board_root_container, boardFragment).commit();

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_write).setVisible(true);
        menu.findItem(R.id.menu_search).setVisible(true);
    }
}
