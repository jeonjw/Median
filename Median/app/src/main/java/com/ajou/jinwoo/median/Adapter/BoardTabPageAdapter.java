package com.ajou.jinwoo.median.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ajou.jinwoo.median.ui.BoardAnonymousFragment;
import com.ajou.jinwoo.median.ui.BoardClassReviewFragment;
import com.ajou.jinwoo.median.ui.BoardQAFragment;
import com.ajou.jinwoo.median.ui.BoardTextbookFragment;


public class BoardTabPageAdapter extends FragmentStatePagerAdapter{

    private int tabCount;

    public BoardTabPageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new BoardClassReviewFragment();

            case 1:
                return new BoardTextbookFragment();

            case 2:
                return new BoardAnonymousFragment();

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
