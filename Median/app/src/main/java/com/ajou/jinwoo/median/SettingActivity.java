package com.ajou.jinwoo.median;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    private Fragment toolbarFragment;
    Button alarmSettingButton;
    Button logoutButton;
    Button versionButton;
    Button helpDeveloperButton;
    Button licenseButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        alarmSettingButton = (Button) findViewById(R.id.alarm_setting_button);
        logoutButton = (Button) findViewById(R.id.logout_button);
        versionButton = (Button) findViewById(R.id.version_button);
        helpDeveloperButton = (Button) findViewById(R.id.help_developer_button);
        licenseButton = (Button) findViewById(R.id.license_button);

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
