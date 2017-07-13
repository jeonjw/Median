//package com.ajou.jinwoojeon.median.ui;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.ajou.jinwoojeon.median.R;
//import com.ajou.jinwoojeon.median.model.InfoModel;
//import com.ajou.jinwoojeon.median.model.OnDataChangedListener;
//
//
//public class InfoActivity extends AppCompatActivity {
//    private Fragment toolbarFragment;
//    private ProgressDialog progressDialog;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_info);
//
//        showProgressDialog();
//
//        FragmentManager fm = getSupportFragmentManager();
//        toolbarFragment = new ToolbarFragment();
//        fm.beginTransaction().add(R.id.info_toolbar_container, toolbarFragment).commit();
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.info_recycler_view);
//
//        InfoModel infoModel = new InfoModel();
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(layoutManager);
//
//        infoModel.setOnDataChangedListener(new OnDataChangedListener() {
//            @Override
//            public void onDataChanged() {
//                hideProgressDialog();
//            }
//        });
//
//        recyclerView.setAdapter(infoModel.getAdapter());
//
//    }
//
//    private void showProgressDialog() {
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading..");
//        progressDialog.show();
//    }
//
//    private void hideProgressDialog() {
//        progressDialog.dismiss();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        ((ToolbarFragment) toolbarFragment).setToolbarTitle("Info");
//    }
//}
