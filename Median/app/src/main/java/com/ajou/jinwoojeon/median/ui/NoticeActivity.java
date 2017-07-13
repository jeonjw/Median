//package com.ajou.jinwoojeon.median.ui;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;
//import android.view.Menu;
//import android.view.MenuItem;
//
//import com.ajou.jinwoojeon.median.R;
//
//public class NoticeActivity extends AppCompatActivity {
//    private Fragment toolbarFragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_notice);
//
//        FragmentManager fm = getSupportFragmentManager();
//
//        toolbarFragment = new ToolbarFragment();
//        Fragment mediaNoticeFragment = new MediaNoticeFragment();
//        fm.beginTransaction().add(R.id.fragment_notice_toolbar_container, toolbarFragment).commit();
//        fm.beginTransaction().add(R.id.fragment_notice_container, mediaNoticeFragment).commit();
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
////        if (item.getItemId() == R.id.menu_write && User.getInstance().isAdmin()) {
////            Intent intent = new Intent(NoticeActivity.this, StudentNoticeWriteActivity.class);
////            startActivity(intent);
////        } else
////        if (item.getItemId() == R.id.menu_write && !User.getInstance().isAdmin()) {
////            Toast.makeText(NoticeActivity.this, "작성 권한이 없습니다.\n", Toast.LENGTH_SHORT).show();
////        }
//        return true;
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        ((ToolbarFragment) toolbarFragment).setToolbarTitle("Notice");
//    }
//
//    @Override
//    public void onBackPressed() {
//        Bundle extras = getIntent().getExtras();
//
//        boolean launchedFromNotif = false;
//
//        if (extras != null && extras.containsKey("ACTIVITY_FROM_NOTIFICATION"))
//            launchedFromNotif = extras.getBoolean("ACTIVITY_FROM_NOTIFICATION");
//
//        if (launchedFromNotif) {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(intent);
//            finish();
//        } else
//            super.onBackPressed();
//
//    }
//}
