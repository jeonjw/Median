package com.ajou.jinwoo.median;

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
import android.widget.LinearLayout;

import com.ajou.jinwoo.median.model.PhotoAlbum;
import com.ajou.jinwoo.median.viewholder.AlbumViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AlbumActivity extends AppCompatActivity {

    private Fragment toolbarFragment;

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FragmentManager fm = getSupportFragmentManager();
        toolbarFragment = new ToolbarFragment();
        fm.beginTransaction().add(R.id.album_toolbar_container, toolbarFragment).commit();

        recyclerView = (RecyclerView) findViewById(R.id.album_recycler_view);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerAdapter<PhotoAlbum, AlbumViewHolder> adapter = new FirebaseRecyclerAdapter<PhotoAlbum, AlbumViewHolder>(PhotoAlbum.class, R.layout.list_item_album,
                AlbumViewHolder.class, databaseReference.child("Photo_Album")) {
            @Override
            protected void populateViewHolder(AlbumViewHolder viewHolder, PhotoAlbum model, int position) {
                viewHolder.bindData(model, getRef(position).getKey(),AlbumActivity.this);
            }

        };

        recyclerView.setAdapter(adapter);

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


            final EditText detail = new EditText(this);


            alert.setView(detail);

            alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    databaseReference.child("Photo_Album").
                            push().setValue(new PhotoAlbum("https://firebasestorage.googleapis.com/v0/b/median-234c4.appspot.com/o/profileImages%2Fdefault.png?alt=media&token=45a00f70-1078-431b-86c5-005e19e6026c",
                            detail.getText().toString()));
                }
            });

            alert.show();

        }


        return true;

    }
}