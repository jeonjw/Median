package com.ajou.jinwoo.median;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ajou.jinwoo.median.valueObject.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private Fragment toolbarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        User.getInstance().setUserName(firebaseUser.getDisplayName());
        User.getInstance().setUserEmail(firebaseUser.getEmail());

        FragmentManager fm = getSupportFragmentManager();
        FirebaseInstanceId.getInstance().getToken();

        Fragment mainFragment = new MainFragment();
        toolbarFragment = new ToolbarFragment();
        FirebaseMessaging.getInstance().subscribeToTopic("notice");

        fm.beginTransaction().add(R.id.toolbar_container, toolbarFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, mainFragment).commit();

    }

    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
