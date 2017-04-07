package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class NoticeActivity extends AppCompatActivity {
    private Fragment toolbarFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        FragmentManager fm = getSupportFragmentManager();

        toolbarFragment = new ToolbarFragment();
        Fragment noticeFragment = new NoticeContainerFragment();
        fm.beginTransaction().add(R.id.fragment_notice_toolbar_container, toolbarFragment).commit();
        fm.beginTransaction().add(R.id.fragment_notice_container, noticeFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_board, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        System.out.println("notice");
        if (item.getItemId() == R.id.menu_write) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment writeFragment = new StudentNoticeWriteFragment();
            fm.beginTransaction().replace(R.id.fragment_notice_container, writeFragment).addToBackStack(null).commit();
        } else if (item.getItemId() == R.id.menu_search) {

        }
        return true;

    }


    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }

}
