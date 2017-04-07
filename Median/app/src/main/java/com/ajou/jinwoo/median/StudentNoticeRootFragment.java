package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class StudentNoticeRootFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_root, container, false);
        setHasOptionsMenu(true);
        FragmentManager fm = getFragmentManager();
        Fragment studentNoticeFragment = new StudentNoticeFragment();
        fm.beginTransaction().add(R.id.student_notice_container, studentNoticeFragment).commit();

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_notice_write).setVisible(true);
        menu.findItem(R.id.menu_search).setVisible(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("student");

        if (item.getItemId() == R.id.menu_notice_write) {
            FragmentManager fm = getFragmentManager();
            Fragment writeFragment = new StudentNoticeWriteFragment();
            fm.beginTransaction().replace(R.id.student_notice_container, writeFragment).setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
        } else if (item.getItemId() == R.id.menu_search) {
            Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
