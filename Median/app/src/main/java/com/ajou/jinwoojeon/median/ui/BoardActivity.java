//package com.ajou.jinwoojeon.median.ui;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;
//
//import com.ajou.jinwoojeon.median.R;
//
//public class BoardActivity extends AppCompatActivity {
//    private Fragment toolbarFragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_board);
//
//        FragmentManager fm = getSupportFragmentManager();
//
//        toolbarFragment = new ToolbarFragment();
//        fm.beginTransaction().add(R.id.fragment_board_toolbar_container, toolbarFragment).commit();
//        fm.beginTransaction().add(R.id.fragment_board_container, new BoardTabFragment()).commit();
//    }
//
//
//}
