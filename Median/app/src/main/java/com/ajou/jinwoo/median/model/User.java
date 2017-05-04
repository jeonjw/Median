package com.ajou.jinwoo.median.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {
    private String userName;
    private String userEmail;

    public String getUserName() {
        return userName;
    }
    private User() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference.child("User").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.getValue()==null) {
                    userName = firebaseUser.getDisplayName();
                    userEmail = firebaseUser.getEmail();
                    databaseReference.child("User").child(firebaseUser.getUid()).child("name").setValue(userName);
                    databaseReference.child("User").child(firebaseUser.getUid()).child("email").setValue(userEmail);
                }else{
                    userName = snapshot.child("name").getValue().toString();
                    userEmail = snapshot.child("email").getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


    private static class Singleton {
        private static final User INSTANCE = new User();
    }

    public static User getInstance() {
        return Singleton.INSTANCE;
    }
}
