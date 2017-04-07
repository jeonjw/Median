package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BoardActivity extends AppCompatActivity {
    private Fragment toolbarFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        FragmentManager fm = getSupportFragmentManager();

        toolbarFragment = new ToolbarFragment();
        Fragment boardRootFragment = new BoardRootFragment();
        fm.beginTransaction().add(R.id.fragment_board_toolbar_container, toolbarFragment).commit();
        fm.beginTransaction().add(R.id.fragment_board_container, boardRootFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_board, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        System.out.println("board");
        if (item.getItemId() == R.id.menu_write) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment writeFragment = new BoardWriteFragment();
            fm.beginTransaction().replace(R.id.fragment_board_container, writeFragment).addToBackStack(null).commit();
        } else if (item.getItemId() == R.id.menu_search) {
            Toast.makeText(BoardActivity.this, "board_search", Toast.LENGTH_SHORT).show();
        }
        return true;

    }

    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }
}
