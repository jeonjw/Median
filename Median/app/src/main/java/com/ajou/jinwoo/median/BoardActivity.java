package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class BoardActivity extends AppCompatActivity {
    private Fragment toolbarFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        FragmentManager fm = getSupportFragmentManager();

        toolbarFragment = new ToolbarFragment();
        Fragment boardRootFragment = new BoardTabFragment();
        fm.beginTransaction().add(R.id.fragment_board_toolbar_container, toolbarFragment).commit();
        fm.beginTransaction().add(R.id.fragment_board_container, boardRootFragment).commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_board, menu);
////        MenuItem searchItem = menu.findItem(R.id.menu_search);
////        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//////        searchView.setMaxWidth(900);
////
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String s) {
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String s) {
////                System.out.println(s);
////                return true;
////            }
////        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.menu_write) {
//            Intent intent = new Intent(BoardActivity.this, BoardWriteActivity.class);
//            intent.putExtra("CURRENT_BOARD_TAB", BoardTabFragment.getCurrentTab());
//            startActivity(intent);
//        }
//        return true;
//    }

    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }
}
