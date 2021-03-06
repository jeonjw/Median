package com.ajou.jinwoojeon.median.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoojeon.median.APIExample;
import com.ajou.jinwoojeon.median.BackPressHandler;
import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.model.UserModel;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private BackPressHandler backPressHandler;
    private TextView toolbarTitleTextView;
    private TextView profileNameTextView;
    private TextView profileEmailTextView;
    private CircleImageView profileImageView;

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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.student_notice, R.string.media_notice);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView =  navigationView.getHeaderView(0);
        profileImageView = headerView.findViewById(R.id.nav_header_profile_image_view);
        profileEmailTextView = headerView.findViewById(R.id.nav_header_profile_email_text_view);
        profileNameTextView = headerView.findViewById(R.id.nav_header_profile_name_text_view);

        setNavigationHeader();


        navigationView.setNavigationItemSelectedListener(this);
//        setNavItemTextAlignment();


    }

    private void setToolbarStyle() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbarTitleTextView = toolbar.findViewById(R.id.toolbar_title);

        toolbarTitleTextView.setText(R.string.nav_item_title_notice);

        Typeface type = Typeface.createFromAsset(getAssets(), "Womby.ttf");
        toolbarTitleTextView.setTypeface(type);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

//    private void setNavItemTextAlignment() {
//        Menu menu = navigationView.getMenu();
//        String[] menuTitle = getResources().getStringArray(R.array.nav_item);
//        Typeface type = Typeface.createFromAsset(getAssets(), "Womby.ttf");
//
//        for (int i = 0; i < menu.size(); i++) {
//            MenuItem item = menu.getItem(i);
//            SpannableString s = new SpannableString(menuTitle[i]);
//            s.setSpan(new CustomTypefaceSpan("", type), 0, s.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
////            s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, s.length(), 0);
//            item.setTitle(s);
//        }
//    }

    private void setNavigationHeader() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Glide.with(this)
                .load(firebaseUser.getPhotoUrl()).centerCrop()
                .into(profileImageView);

        profileNameTextView.setText(firebaseUser.getDisplayName());
        profileEmailTextView.setText(firebaseUser.getEmail());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
            toolbarTitleTextView.setText(R.string.nav_item_title_notice);
            getSupportActionBar().setElevation(30.0f);
        } else if (id == R.id.nav_board) {
            fragment = new BoardTabFragment();
            toolbarTitleTextView.setText(R.string.nav_item_title_board);
            getSupportActionBar().setElevation(0.0f);
        } else if (id == R.id.nav_lecture) {
            fragment = new LectureFragment();
            toolbarTitleTextView.setText(R.string.nav_item_title_lecture);
            getSupportActionBar().setElevation(0.0f);
        } else if (id == R.id.nav_info) {
            fragment = new InfoFragment();
            toolbarTitleTextView.setText(R.string.nav_item_title_info);
            getSupportActionBar().setElevation(30.0f);
        } else if (id == R.id.nav_setting) {
            fragment = new SettingFragment();
            toolbarTitleTextView.setText(R.string.nav_item_title_setting);
            getSupportActionBar().setElevation(30.0f);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fragment, fragment).commit();
        return true;
    }
}

