package com.ajou.jinwoo.median;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ajou.jinwoo.median.model.InfoModel;
import com.ajou.jinwoo.median.model.OnDataChangedListener;


public class InfoActivity extends AppCompatActivity {
    private Fragment toolbarFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        FragmentManager fm = getSupportFragmentManager();
        toolbarFragment = new ToolbarFragment();
        fm.beginTransaction().add(R.id.info_toolbar_container, toolbarFragment).commit();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.info_recycler_view);

        InfoModel infoModel = new InfoModel(getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        infoModel.setOnDataChangedListener(new OnDataChangedListener() {
            @Override
            public void onDataChanged() {
                progressDialog.dismiss();
            }
        });

        recyclerView.setAdapter(infoModel.getAdapter());

    }

    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle("Info");
    }
}
//홀더 어댑터 다른거와 같이 처리하기
