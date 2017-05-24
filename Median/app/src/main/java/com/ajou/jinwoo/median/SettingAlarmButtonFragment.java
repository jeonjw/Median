package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SettingAlarmButtonFragment extends Fragment {

    private ImageView mediaOnToggle;
    private ImageView mediaOffToggle;
    private ImageView studentOnToggle;
    private ImageView studentOffToggle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_alarm, container, false);



        return view;
    }
}
