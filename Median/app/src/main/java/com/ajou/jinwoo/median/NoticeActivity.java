package com.ajou.jinwoo.median;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.Query;

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

//        MenuItem searchItem = menu.findItem(R.id.menu_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setQueryHint("제목으로 검색");
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                Query query = getRef(mDatabase).orderByChild("title").startAt(s).endAt(s + "\uf8ff");
//                setAdapter(query);
//
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                if (s.length() == 0)
//                    setAdapter(getRef(mDatabase));
//                return true;
//            }
//        });
//
//        MenuItemCompat.setOnActionExpandListener(searchItem,
//                new MenuItemCompat.OnActionExpandListener() {
//                    @Override
//                    public boolean onMenuItemActionCollapse(MenuItem item) {
//                        setAdapter(getRef(mDatabase));
//                        return true; // Return true to collapse action view
//                    }
//
//                    @Override
//                    public boolean onMenuItemActionExpand(MenuItem item) {
//                        // Do something when expanded
//                        return true; // Return true to expand action view
//                    }
//                });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        System.out.println("notice");
        if (item.getItemId() == R.id.menu_write) {
            Intent intent = new Intent(NoticeActivity.this,StudentNoticeWriteActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.menu_search) {

        }
        return true;

    }


    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }

}
