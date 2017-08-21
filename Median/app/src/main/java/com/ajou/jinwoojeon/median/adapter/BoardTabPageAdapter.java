package com.ajou.jinwoojeon.median.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ajou.jinwoojeon.median.ui.BoardClassReviewFragment;
import com.ajou.jinwoojeon.median.ui.BoardQAFragment;
import com.ajou.jinwoojeon.median.ui.BoardTextbookFragment;
import com.ajou.jinwoojeon.median.ui.StudentNoticeFragment;

public class BoardTabPageAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public BoardTabPageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new StudentNoticeFragment();

            case 1:
                return new BoardClassReviewFragment();

            case 2:
                return new BoardTextbookFragment();

            case 3:
                return new BoardQAFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
