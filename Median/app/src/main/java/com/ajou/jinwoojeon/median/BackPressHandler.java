package com.ajou.jinwoojeon.median;


import android.app.Activity;
import android.widget.Toast;

public class BackPressHandler {
    private long backKeyPressedTime = 0;

    private Activity activity;
    private Toast toast;

    public BackPressHandler(Activity activity) {
        this.activity = activity;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
        } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();

        }
    }

    private void showGuide() {
        toast = Toast.makeText(activity, "뒤로가기 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT);
        toast.show();
    }
}
