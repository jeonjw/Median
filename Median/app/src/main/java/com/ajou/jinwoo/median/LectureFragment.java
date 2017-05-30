package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LectureFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture, container, false);

        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.class_tab);

        tabLayout.addTab(tabLayout.newTab().setText("월요일"));
        tabLayout.addTab(tabLayout.newTab().setText("화요일"));
        tabLayout.addTab(tabLayout.newTab().setText("수요일"));
        tabLayout.addTab(tabLayout.newTab().setText("목요일"));
        tabLayout.addTab(tabLayout.newTab().setText("금요일"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LectureTabPageAdapter lectureTabPageAdapter = new LectureTabPageAdapter(getFragmentManager(), tabLayout.getTabCount());


        final FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.lecture_container, lectureTabPageAdapter.getItem(tabLayout.getSelectedTabPosition())).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fm.beginTransaction().replace(R.id.lecture_container, lectureTabPageAdapter.getItem(tabLayout.getSelectedTabPosition())).commit();
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
}
