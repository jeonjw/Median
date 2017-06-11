package com.ajou.jinwoojeon.median;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ajou.jinwoojeon.median.ui.MainActivity;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FCM Service";
//    private static PowerManager.WakeLock sCpuWakeLock;



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        if (sCpuWakeLock != null) {
//            return;
//        }
//        PowerManager pm = (PowerManager) getBaseContext().getSystemService(Context.POWER_SERVICE);
//        sCpuWakeLock = pm.newWakeLock(
//                PowerManager.SCREEN_DIM_WAKE_LOCK |
//                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
//                        PowerManager.ON_AFTER_RELEASE, "hi");
//
//        sCpuWakeLock.acquire();
//
//
//        if (sCpuWakeLock != null) {
//            sCpuWakeLock.release();
//            sCpuWakeLock = null;
//        }


        Log.d(TAG, "From: " + remoteMessage.getData().size()); // for the data size
        final Map<String, String> data = remoteMessage.getData();
        String title = data.get("title");
        String body = data.get("body");
        if (!title.equals("") && !body.equals("")) {
            sendNotificationData(title, body); //send notification to user
        }


    }

    private void sendNotificationData(String messageTitle, String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long[] pattern = {500, 500, 500, 500, 500};


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.media_logo)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH) //head up notification (푸시 메시지 도착시 화면에 뜨게함)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }




}
