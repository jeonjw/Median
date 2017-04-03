package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private Fragment toolbarFragment;
    private MenuItem write;
    private MenuItem search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fm = getSupportFragmentManager();

        Fragment mainFragment = new MainFragment();
        toolbarFragment = new ToolbarFragment();

        fm.beginTransaction().add(R.id.toolbar_container, toolbarFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, mainFragment).commit();

    }

    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_board, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        write = menu.findItem(R.id.menu_notice_write);
        search = menu.findItem(R.id.menu_search);
        write.setVisible(false);
        search.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return false;
    }
}
