package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class BoardFragment extends Fragment {

    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        setHasOptionsMenu(true);
        ((BoardActivity) getActivity()).setToolbarTitle("Board");

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.board_tab);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addTab(tabLayout.newTab().setText("수업후기"));
        tabLayout.addTab(tabLayout.newTab().setText("교재장터"));
        tabLayout.addTab(tabLayout.newTab().setText("익명자유"));
        tabLayout.addTab(tabLayout.newTab().setText("질문답변"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = (ViewPager) view.findViewById(R.id.board_view_pager);
        BoardTabPageAdapter boardTabPageAdapter = new BoardTabPageAdapter(getFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(boardTabPageAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("board");
        if (item.getItemId() == R.id.menu_notice_write) {
//            FragmentManager fm = getFragmentManager();
//            Fragment writeFragment = new BoardWriteFragment();
//            fm.beginTransaction().replace(R.id.student_notice_container, writeFragment).setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
        } else if (item.getItemId() == R.id.menu_search) {
            Toast.makeText(getContext(), "board_search", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
