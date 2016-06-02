package com.sasi.wearabletest;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;

/**
 * Created by sasikumarlakshmanan on 02/06/16.
 */
public class AlarmService extends IntentService {
    private static final String TAG = "AlarmService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param /name Used to name the worker thread, important only for debugging.
     */
    public AlarmService() {
        super("WEARABLE_TEST_ALARM_SERVICE");
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "In AlarmService!!!!");

        // Send a notification.
        notifyNow();

    }

    public void notifyNow() {

        int notificationID = 100;

        // Build intent for notification content.
        Intent intent = new Intent(this, ScrollingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("Content Alarm Title")
                .setContentText("Content Alarm Text")
                .setContentIntent(pendingIntent)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
        ;

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }
}
