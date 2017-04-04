package com.ajou.jinwoo.median;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


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
                return new LectureMondayFragment();

            case 1:
                return new LectureTuesdayFragment();

            case 2:
                return new LectureWednesdayFragment();

            case 3:
                return new LectureThursdayFragment();

            case 4:
                return new LectureFridayFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
