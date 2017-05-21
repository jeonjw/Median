package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.etsy.android.grid.StaggeredGridView;

/**
 * Created by samsung on 2017-05-21.
 */

public class AlbumActivity extends AppCompatActivity {

    StaggeredGridView staggeredGridView;
    AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        staggeredGridView = (StaggeredGridView) findViewById(R.id.staggeredGridView);

        albumAdapter = new AlbumAdapter(this);

        staggeredGridView.setAdapter(albumAdapter);

    }
}
