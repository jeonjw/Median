package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class LectureActivity extends AppCompatActivity {

    private Fragment toolbarFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        toolbarFragment = new ToolbarFragment();

        FragmentManager fm = getSupportFragmentManager();
        Fragment lectureFragment = new LectureFragment();
        fm.beginTransaction().add(R.id.fragment_lecture_toolbar_container, toolbarFragment).commit();
        fm.beginTransaction().add(R.id.fragment_lecture_container, lectureFragment).commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((ToolbarFragment) toolbarFragment).setToolbarTitle("Lecture");
    }
}
