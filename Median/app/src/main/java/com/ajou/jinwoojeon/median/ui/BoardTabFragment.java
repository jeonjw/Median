package com.ajou.jinwoojeon.median.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.adapter.BoardTabPageAdapter;

public class BoardTabFragment extends Fragment {

    private ViewPager mViewPager;

    public static int getCurrentTab() {
        return currentTab;
    }

    private static int currentTab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentTab = 0;
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        TabLayout tabLayout = view.findViewById(R.id.board_tab);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addTab(tabLayout.newTab().setText("학과공지"));
        tabLayout.addTab(tabLayout.newTab().setText("수업후기"));
        tabLayout.addTab(tabLayout.newTab().setText("교재장터"));
        tabLayout.addTab(tabLayout.newTab().setText("질문답변"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = view.findViewById(R.id.board_view_pager);
        BoardTabPageAdapter boardTabPageAdapter = new BoardTabPageAdapter(getFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(boardTabPageAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                currentTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton floatingActionButton = view.findViewById(R.id.post_write_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                intent.putExtra("CURRENT_BOARD_TAB", BoardTabFragment.getCurrentTab());
                startActivity(intent);
            }
        });
        return view;
    }
}
