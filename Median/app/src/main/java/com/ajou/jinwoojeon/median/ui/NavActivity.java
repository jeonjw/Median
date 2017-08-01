package com.ajou.jinwoojeon.median.ui;

import android.graphics.Color;
import android.graphics.Typeface;
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
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.ajou.jinwoojeon.median.BackPressHandler;
import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.model.UserModel;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private BackPressHandler backPressHandler;
    private NavigationView navigationView;
    private TextView toolbarTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserModel userModel = new UserModel();
        userModel.readUserData();
        backPressHandler = new BackPressHandler(this);

        setContentView(R.layout.activity_navigation);
        setToolbarStyle();


        FragmentManager fm = getSupportFragmentManager();
        Fragment mediaNoticeFragment = new MediaNoticeFragment();
        fm.beginTransaction().add(R.id.content_fragment, mediaNoticeFragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.student_notice, R.string.media_notice);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setNavItemTextAlignMent();


    }

    private void setToolbarStyle() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbarTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title);

        toolbarTitleTextView.setText("NOTICE");

        Typeface type = Typeface.createFromAsset(getAssets(), "Womby.ttf");
        toolbarTitleTextView.setTypeface(type);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setNavItemTextAlignMent() {
        Menu menu = navigationView.getMenu();
        String[] menuTitle = getResources().getStringArray(R.array.nav_item);

        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString s = new SpannableString(menuTitle[i]);
            s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, s.length(), 0);
            item.setTitle(s);
        }


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
            toolbarTitleTextView.setText("NOTICE");
        } else if (id == R.id.nav_board) {
            fragment = new BoardTabFragment();
            toolbarTitleTextView.setText("BOARD");
        } else if (id == R.id.nav_timetable) {
            fragment = new LectureFragment();
            toolbarTitleTextView.setText("LECTURE");
        } else if (id == R.id.nav_info) {
            fragment = new InfoFragment();
            toolbarTitleTextView.setText("INFO");
        } else if (id == R.id.nav_setting) {
            fragment = new SettingFragment();
            toolbarTitleTextView.setText("SETTING");
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fragment, fragment).commit();
        return true;
    }
}

