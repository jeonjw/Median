package com.ajou.jinwoojeon.median.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.valueObject.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingAlarmButtonFragment extends Fragment {

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_alarm, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        SwitchCompat mediaNoticeSwitch = view.findViewById(R.id.media_notice_switch);
        SwitchCompat studentNoticeSwitch = view.findViewById(R.id.student_notice_switch);

        mediaNoticeSwitch.setChecked(User.getInstance().isSubscribeMediaNotice());
        studentNoticeSwitch.setChecked(User.getInstance().isSubscribeStudentNotice());

        mediaNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                User.getInstance().setSubscribeMediaNotice(isChecked);
                saveSettings("subscribeMediaNotice", isChecked);
            }
        });

        studentNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                User.getInstance().setSubscribeStudentNotice(isChecked);
                saveSettings("subscribeStudentNotice", isChecked);
            }
        });

        return view;
    }

    private void saveSettings(String type, boolean isChecked) {
        databaseReference.child("User").child(User.getInstance().getUid()).child(type).setValue(isChecked);
        Toast.makeText(getContext(), "앱을 다시 시작하시면 적용 됩니다.", Toast.LENGTH_SHORT).show();
    }
}
