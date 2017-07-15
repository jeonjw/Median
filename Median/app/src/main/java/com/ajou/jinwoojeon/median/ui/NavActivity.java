package com.ajou.jinwoojeon.median.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ajou.jinwoojeon.median.BackPressHandler;
import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.model.UserModel;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private BackPressHandler backPressHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserModel userModel = new UserModel();
        userModel.readUserData();
        backPressHandler = new BackPressHandler(this);

        setContentView(R.layout.navigation_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment mediaNoticeFragment = new MediaNoticeFragment();
        fm.beginTransaction().add(R.id.content_fragment, mediaNoticeFragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.student_notice, R.string.media_notice);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backPressHandler.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_notice) {
            fragment = new MediaNoticeFragment();
            toolbar.setTitle("Notice");
        } else if (id == R.id.nav_board) {
            fragment = new BoardTabFragment();
            toolbar.setTitle("Board");
        } else if (id == R.id.nav_timetable) {
            fragment = new LectureFragment();
            toolbar.setTitle("Lecture");
        }
        else if (id == R.id.nav_info) {
            fragment = new InfoFragment();
            toolbar.setTitle("Info");
        }
        else if (id == R.id.nav_setting) {
            fragment = new SettingFragment();
            toolbar.setTitle("Setting");
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fragment, fragment).commit();
        return true;
    }
}

