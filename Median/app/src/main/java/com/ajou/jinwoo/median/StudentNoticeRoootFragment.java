package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jinwoo on 2017. 4. 6..
 */

public class StudentNoticeRoootFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_root,container,false);

        FragmentManager fm = getFragmentManager();
        Fragment studentNoticeFragment = new StudentNoticeFragment();
        fm.beginTransaction().add(R.id.student_notice_container, studentNoticeFragment).addToBackStack(null).commit();

        return view;
    }
}
