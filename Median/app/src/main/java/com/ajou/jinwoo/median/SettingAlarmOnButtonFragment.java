package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by samsung on 2017-05-24.
 */

public class SettingAlarmOnButtonFragment extends Fragment {

    ImageView mediaOnToggle;
    ImageView mediaOffToggle;
    ImageView studentOnToggle;
    ImageView studentOffToggle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_alarm_on, container, false);

        mediaOnToggle = (ImageView) view.findViewById(R.id.mediaToggleOnButton);
        mediaOffToggle = (ImageView) view.findViewById(R.id.mediaToggleOffButton);
        studentOffToggle = (ImageView) view.findViewById(R.id.studentToggleOffButton);
        studentOnToggle = (ImageView) view.findViewById(R.id.studentToggleOnButton);

        mediaOnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaOnToggle.setVisibility(View.GONE);
                mediaOffToggle.setVisibility(View.VISIBLE);
            }
        });

        mediaOffToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaOnToggle.setVisibility(View.VISIBLE);
                mediaOffToggle.setVisibility(View.GONE);
            }
        });

        studentOnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentOnToggle.setVisibility(View.GONE);
                studentOffToggle.setVisibility(View.VISIBLE);
            }
        });

        studentOffToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentOnToggle.setVisibility(View.VISIBLE);
                studentOffToggle.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
