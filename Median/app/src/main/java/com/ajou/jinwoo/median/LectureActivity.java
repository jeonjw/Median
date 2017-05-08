package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by samsung on 2017-05-06.
 */

public class LectureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        FragmentManager fm = getSupportFragmentManager();
        Fragment lectureFragment = new LectureFragment();
        fm.beginTransaction().add(R.id.fragment_lecture_container, lectureFragment).commit();
    }
}
