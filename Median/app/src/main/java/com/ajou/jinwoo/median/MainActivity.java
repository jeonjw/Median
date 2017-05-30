package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ajou.jinwoo.median.ui.ToolbarFragment;
import com.ajou.jinwoo.median.valueObject.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private Fragment toolbarFragment;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    setUserInfoToDatabase();
                } else {
                    User.getInstance().setUserName(firebaseUser.getDisplayName());
                    User.getInstance().setUid(firebaseUser.getUid());
                    User.getInstance().setAdmin(dataSnapshot.getValue(User.class).isAdmin());
                    User.getInstance().setSubscribeMediaNotice(dataSnapshot.getValue(User.class).isSubscribeMediaNotice());
                    User.getInstance().setSubscribeStudentNotice(dataSnapshot.getValue(User.class).isSubscribeStudentNotice());
                    setSubscribeToTopic();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        System.out.println("Token: " + FirebaseInstanceId.getInstance().getToken());

        FragmentManager fm = getSupportFragmentManager();

        Fragment mainFragment = new MainFragment();
        toolbarFragment = new ToolbarFragment();

        fm.beginTransaction().add(R.id.toolbar_container, toolbarFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, mainFragment).commit();

    }

    private void setSubscribeToTopic() {
        if (User.getInstance().isSubscribeMediaNotice())
            FirebaseMessaging.getInstance().subscribeToTopic("mediaNotice");
        else
            FirebaseMessaging.getInstance().unsubscribeFromTopic("mediaNotice");

        if (User.getInstance().isSubscribeStudentNotice())
            FirebaseMessaging.getInstance().subscribeToTopic("studentNotice");
        else
            FirebaseMessaging.getInstance().unsubscribeFromTopic("studentNotice");
    }

    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void setUserInfoToDatabase() {
        databaseReference.child("User").child(firebaseUser.getUid()).child("name").setValue(firebaseUser.getDisplayName());
        databaseReference.child("User").child(firebaseUser.getUid()).child("uid").setValue(firebaseUser.getUid());
        databaseReference.child("User").child(firebaseUser.getUid()).child("admin").setValue(false);
        databaseReference.child("User").child(firebaseUser.getUid()).child("subscribeMediaNotice").setValue(true);
        databaseReference.child("User").child(firebaseUser.getUid()).child("subscribeStudentNotice").setValue(true);
        setSubscribeToTopic();
    }
}
