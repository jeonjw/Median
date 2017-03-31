package com.ajou.jinwoo.median;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ToolbarFragment extends Fragment {
    private TextView toolbarTitleTextView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbarTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitle(null);

        Typeface type = Typeface
                .createFromAsset(getActivity().getAssets(), "Impact.ttf");

        toolbarTitleTextView.setText(R.string.app_title);
        toolbarTitleTextView.setTypeface(type);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        return view;
    }
    public void setToolbarTitle(String title){
        toolbarTitleTextView.setText(title);
    }
}
