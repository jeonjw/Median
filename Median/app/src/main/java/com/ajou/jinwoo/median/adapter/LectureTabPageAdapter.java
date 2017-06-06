package com.ajou.jinwoo.median.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ajou.jinwoo.median.ui.BaseLectureFragment;


public class LectureTabPageAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public LectureTabPageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new BaseLectureFragment.LectureMondayFragment();

            case 1:
                return new BaseLectureFragment.LectureTuesdayFragment();

            case 2:
                return new BaseLectureFragment.LectureWednesdayFragment();

            case 3:
                return new BaseLectureFragment.LectureThursdayFragment();

            case 4:
                return new BaseLectureFragment.LectureFridayFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
