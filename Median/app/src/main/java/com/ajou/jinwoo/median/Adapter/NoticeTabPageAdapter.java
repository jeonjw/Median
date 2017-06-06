package com.ajou.jinwoo.median.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ajou.jinwoo.median.MediaNoticeFragment;
import com.ajou.jinwoo.median.StudentNoticeFragment;
import com.ajou.jinwoo.median.valueObject.StudentNotice;

public class NoticeTabPageAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public NoticeTabPageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MediaNoticeFragment();

            case 1:
                return new StudentNoticeFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
