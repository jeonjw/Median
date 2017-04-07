package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jinwoo on 2017. 4. 7..
 */

public class NoticeContainerFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_container, container, false);
        FragmentManager fm = getFragmentManager();
        Fragment noticeFragment = new NoticeFragment();
        fm.beginTransaction().replace(R.id.fram_notice_container, noticeFragment).commit();

        return view;
    }


}
