package com.ajou.jinwoo.median;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ClassTabPageAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public ClassTabPageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ClassMondayFragment();

            case 1:
                return new ClassTuesdayFragment();

            case 2:
                return new ClassWednesdayFragment();

            case 3:
                return new ClassThursdayFragment();

            case 4:
                return new ClassFridayFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
