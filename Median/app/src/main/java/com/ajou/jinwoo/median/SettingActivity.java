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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button alarmSettingButton = (Button) findViewById(R.id.alarm_setting_button);
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        Button versionButton = (Button) findViewById(R.id.version_button);
        Button helpDeveloperButton = (Button) findViewById(R.id.help_developer_button);
        Button licenseButton = (Button) findViewById(R.id.license_button);

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
                String[] mailAddress = {"gch01410@naver.com","jjw1933@gmail.com"};

                it.setType("plaine/text");
                it.putExtra(Intent.EXTRA_EMAIL, mailAddress);

                startActivity(it);
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
