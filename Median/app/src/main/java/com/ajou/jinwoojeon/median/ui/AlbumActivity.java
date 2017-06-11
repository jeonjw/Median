package com.ajou.jinwoojeon.median.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.model.AlbumModel;


public class AlbumActivity extends AppCompatActivity {

    private Fragment toolbarFragment;
    private AlbumModel albumModel;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);


        albumModel = new AlbumModel();
        FragmentManager fm = getSupportFragmentManager();
        toolbarFragment = new ToolbarFragment();
        fm.beginTransaction().add(R.id.album_toolbar_container, toolbarFragment).commit();

        recyclerView = (RecyclerView) findViewById(R.id.album_recycler_view);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(albumModel.getAdapter());

    }


    @Override
    protected void onResume() {
        super.onResume();
        ((ToolbarFragment) toolbarFragment).setToolbarTitle("Album");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_board, menu);
        menu.findItem(R.id.menu_search).setVisible(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_write) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("앨범 생성");
            alert.setMessage("앨범 이름을 입력해주세요");


            final EditText title = new EditText(this);
            alert.setView(title);

            alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    albumModel.createAlbum(title.getText().toString());
                }
            });

            alert.show();

        }
        return true;

    }
}