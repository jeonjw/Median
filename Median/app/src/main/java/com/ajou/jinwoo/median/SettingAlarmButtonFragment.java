package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingAlarmButtonFragment extends Fragment {

    Switch mediaNoticeSwitch;
    Switch studentNoticeSwitch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_alarm, container, false);

        mediaNoticeSwitch = (Switch) view.findViewById(R.id.media_notice_switch);
        studentNoticeSwitch = (Switch) view.findViewById(R.id.student_notice_switch);

        mediaNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getContext(),"학과 공지 알림 설정 : " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        studentNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getContext(),"학생회 공지 알림 설정 : " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
