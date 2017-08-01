package com.ajou.jinwoojeon.median.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ajou.jinwoojeon.median.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingFragment extends Fragment {

    private boolean click = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        setHasOptionsMenu(true);

        Button alarmSettingButton = (Button) view.findViewById(R.id.alarm_setting_button);
        Button logoutButton = (Button) view.findViewById(R.id.logout_button);
        Button versionButton = (Button) view.findViewById(R.id.version_button);
        Button helpDeveloperButton = (Button) view.findViewById(R.id.help_developer_button);
        Button developerButton = (Button) view.findViewById(R.id.developer_button);

        alarmSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getChildFragmentManager();
                SettingAlarmButtonFragment settingAlarmOnButtonFragment = new SettingAlarmButtonFragment();

                if (!click) {
                    fm.beginTransaction().add(R.id.alarm_setting_container, settingAlarmOnButtonFragment, "Tag").commit();
                    click = true;

                } else {
                    fm.beginTransaction().remove(fm.findFragmentByTag("Tag")).commit();
                    click = false;
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);

                //종료처리하기
                startActivity(intent);

            }
        });

        helpDeveloperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                String[] mailAddress = {"gch01410@gmail.com", "jeonjw1933@gmail.com"};
                it.setType("plain/text");
                it.putExtra(Intent.EXTRA_EMAIL, mailAddress);

                startActivity(it);
            }
        });

        versionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VersionDialogFragment dialog = new VersionDialogFragment();
                dialog.show(getFragmentManager(), "version");
            }
        });

        developerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeveloperNameCardDialogFragment dialog = new DeveloperNameCardDialogFragment();
                dialog.show(getFragmentManager(), "devloper");
            }
        });


        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.menu_write).setVisible(false);
        menu.findItem(R.id.menu_search).setVisible(false);
    }
}
