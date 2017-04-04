package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class StudentNoticeWriteFragment extends Fragment {
    private ImageButton closeButton;
    private ImageButton writeButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_notice_write, container, false);
        setHasOptionsMenu(true);
        closeButton = (ImageButton) view.findViewById(R.id.write_close_button);
        writeButton = (ImageButton) view.findViewById(R.id.write_finish);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().remove(StudentNoticeWriteFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().remove(StudentNoticeWriteFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.menu_notice_write).setVisible(false);
        menu.findItem(R.id.menu_search).setVisible(false);
    }
}
