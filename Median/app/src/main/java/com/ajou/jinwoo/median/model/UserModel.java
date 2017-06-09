package com.ajou.jinwoo.median.model;

import com.ajou.jinwoo.median.valueObject.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class UserModel {
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    public UserModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();



    }
    public void readUserData(){
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


    private void setUserInfoToDatabase() {
        databaseReference.child("User").child(firebaseUser.getUid()).child("name").setValue(firebaseUser.getDisplayName());
        databaseReference.child("User").child(firebaseUser.getUid()).child("uid").setValue(firebaseUser.getUid());
        databaseReference.child("User").child(firebaseUser.getUid()).child("admin").setValue(false);
        databaseReference.child("User").child(firebaseUser.getUid()).child("subscribeMediaNotice").setValue(true);
        databaseReference.child("User").child(firebaseUser.getUid()).child("subscribeStudentNotice").setValue(true);
        setSubscribeToTopic();
    }
}
