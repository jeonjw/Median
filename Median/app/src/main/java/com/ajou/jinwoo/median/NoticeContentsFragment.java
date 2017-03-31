package com.ajou.jinwoo.median;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NoticeContentsFragment extends Fragment {

    private DatabaseReference mDatabase;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_contents, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        int num = getArguments().getInt("NoticeContentsFragment");

        textView = (TextView) view.findViewById(R.id.contents_text_view);

        readContents(num);


        return view;

    }

    private void readContents(final int num) {
        mDatabase.child("media_notice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.child("contents").child(String.valueOf(num)).getValue().toString());
                if (Build.VERSION.SDK_INT >= 24) {
                    textView.setText(Html.fromHtml(dataSnapshot.child("contents").child(String.valueOf(num)).getValue().toString(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    textView.setText(Html.fromHtml(dataSnapshot.child("contents").child(String.valueOf(num)).getValue().toString()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static Fragment newInstance(int num) {
        Bundle args = new Bundle();
        args.putInt("NoticeContentsFragment", num);
        Fragment fragment = new NoticeContentsFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
