package com.ajou.jinwoo.median;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {

    private Fragment toolbarFragment;
    boolean click = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button alarmSettingButton = (Button) findViewById(R.id.alarm_setting_button);
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        Button versionButton = (Button) findViewById(R.id.version_button);
        Button helpDeveloperButton = (Button) findViewById(R.id.help_developer_button);
        Button developerButton = (Button) findViewById(R.id.developer_button);

        alarmSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                SettingAlarmButtonFragment settingAlarmOnButtonFragment = new SettingAlarmButtonFragment();

                if (!click) {
                    fm.beginTransaction().add(R.id.alarm_setting_container, settingAlarmOnButtonFragment,"Tag").commit();
                    click = true;

                } else {
                    fm.findFragmentByTag("Tag");
                    fm.beginTransaction().remove(fm.findFragmentByTag("Tag")).commit();
                    click = false;
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        helpDeveloperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                String[] mailAddress = {"gch01410@gmail.com", "jjw1933@gmail.com"};

                it.setType("plaine/text");
                it.putExtra(Intent.EXTRA_EMAIL, mailAddress);

                startActivity(it);
            }
        });

        versionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager ft = getFragmentManager();
                VersionDialogFragment dialog = new VersionDialogFragment();
                dialog.show(ft, "aaa");
            }
        });

        developerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, DeveloperNameCardActivity.class);
                startActivity(intent);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        toolbarFragment = new ToolbarFragment();
        fm.beginTransaction().add(R.id.setting_toolbar_container, toolbarFragment).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((ToolbarFragment) toolbarFragment).setToolbarTitle("Settings");
    }
}
