//package com.ajou.jinwoojeon.median.ui;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;
//
//import com.ajou.jinwoojeon.median.R;
//import com.ajou.jinwoojeon.median.model.UserModel;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.firebase.iid.FirebaseInstanceId;
//
//public class MainActivity extends AppCompatActivity
//        implements GoogleApiClient.OnConnectionFailedListener {
//
//    private Fragment toolbarFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        UserModel userModel = new UserModel();
//        userModel.readUserData();
//
//        System.out.println("Token: " + FirebaseInstanceId.getInstance().getToken());
//
//        FragmentManager fm = getSupportFragmentManager();
//
//        Fragment mainFragment = new MainFragment();
//        toolbarFragment = new ToolbarFragment();
//
//        fm.beginTransaction().add(R.id.toolbar_container, toolbarFragment).commit();
//        fm.beginTransaction().add(R.id.fragment_container, mainFragment).commit();
//
//    }
//
//    public void setToolbarTitle(String title) {
//        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        ((ToolbarFragment) toolbarFragment).setToolbarTitle("Median");
//    }
//}
