package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class StudentNoticeRootFrame extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_notice_root_frame, container, false);
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.root_frame, new StudentNoticeFragment()).commit();

        return view;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("check");

        if (item.getItemId() == R.id.menu_notice_write) {
            FragmentManager fm = getFragmentManager();
            Fragment writeFragment = new StudentNoticeWriteFragment();
            fm.beginTransaction().replace(R.id.root_frame, writeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
        } else if (item.getItemId() == R.id.menu_search) {
            Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_notice_write).setVisible(true);
        menu.findItem(R.id.menu_search).setVisible(true);
    }
}
