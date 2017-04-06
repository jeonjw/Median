package com.ajou.jinwoo.median;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class InfoActivity extends AppCompatActivity {
    private Fragment toolbarFragment;
    private RecyclerView mRecyclerView;
    private InfoAdapter infoAdapter;
    private List<Info> infoList;
    private DatabaseReference mDatabase;
    private FirebaseStorage storage;
    StorageReference storageRef;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);
        storage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FragmentManager fm = getSupportFragmentManager();

        toolbarFragment = new ToolbarFragment();
        fm.beginTransaction().add(R.id.info_toolbar_container, toolbarFragment).commit();


        mRecyclerView = (RecyclerView) findViewById(R.id.info_recycler_view);

        storageRef = storage.getReferenceFromUrl("gs://median-234c4.appspot.com/profileImages");
        infoList = new ArrayList<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        loadNoticeData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        infoAdapter = new InfoAdapter(infoList);
        mRecyclerView.setAdapter(infoAdapter);

    }

    private void loadNoticeData() {
        progressDialog.setMessage("Loading . .");
        progressDialog.show();
        mDatabase.child("info").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Info info = ds.getValue(Info.class);
                    infoList.add(info);
                }


                infoAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


    private class InfoHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mEmailTextView;
        private TextView mTelNumberTextView;
        private TextView mLocationTextView;
        private ImageView mImageView;
        private Info mInfo;


        public InfoHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.profile_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.info_name_text_view);
            mEmailTextView = (TextView) itemView.findViewById(R.id.info_email_text_view);
            mTelNumberTextView = (TextView) itemView.findViewById(R.id.info_telnumber_text_view);
            mLocationTextView = (TextView) itemView.findViewById(R.id.info_location_text_view);


        }


        private void bindNotice(Info info) {
            mInfo = info;

            StorageReference pathReference = storageRef.child(mInfo.getProfileImage());

            Glide.with(InfoActivity.this)
                    .using(new FirebaseImageLoader())
                    .load(pathReference)
                    .into(mImageView);

            mNameTextView.setText(mInfo.getName());
            mEmailTextView.setText(mInfo.getEmail());
            mLocationTextView.setText(mInfo.getLocation());
            mTelNumberTextView.setText(String.valueOf(mInfo.getTelNumber()));


        }
    }

    private class InfoAdapter extends RecyclerView.Adapter<InfoHolder> {
        private List<Info> mInfoList;

        public InfoAdapter(List<Info> infoList) {
            mInfoList = infoList;
        }

        @Override
        public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(InfoActivity.this);
            final View view = layoutInflater.inflate(R.layout.list_item_info, parent, false);

            return new InfoHolder(view);
        }

        @Override
        public void onBindViewHolder(final InfoHolder holder, int position) {
            Info info = mInfoList.get(position);
            holder.bindNotice(info);
        }

        @Override
        public int getItemCount() {
            return mInfoList.size();
        }
    }


    public void setToolbarTitle(String title) {
        ((ToolbarFragment) toolbarFragment).setToolbarTitle(title);
    }
}
