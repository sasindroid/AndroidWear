package com.sasi.wearabletest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


// Tips
// Always use V4 library
// Always use Compat methods (eg: Always use NotificationManagerCompat instead of NotificationManagerd)

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_VOICE_REPLY_KEY = "EXTRA_VOICE_REPLY_KEY";
    int mNotificationID = 200;
    String mGroupKey = "GROUP1";
    int mNotificationID2 = 0;
    String mGroupKey2 = "GROUP2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notifyNow(View view) {

        int notificationID = 100;

        // Build intent for notification content.
        Intent intent = new Intent(this, ScrollingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setContentIntent(pendingIntent)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
        ;

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyMap(View view) {

        int notificationID = 101;

        // Build intent for notification content.
        Intent intent = new Intent(this, ScrollingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Build intent for notification map.
        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Chennai"));
        intent2.setData(geoUri);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_map)
                .setContentTitle("Map Intent")
                .setContentText("Notification - Map")
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Map test", pendingIntent2);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyWearableSpecific(View view) {

        int notificationID = 102;

//        If you want the actions available on the wearable to be different from those on the handheld,
//                then use WearableExtender.addAction(). Once you add an action with this method,
//                the wearable does not display any other actions added with NotificationCompat.Builder.addAction().
//                That is, only the actions added with WearableExtender.addAction() appear on the wearable and
//        they do not appear on the handheld.

        // Create an intent for the reply action
        Intent actionIntent = new Intent(this, ScrollingActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build intent for notification map.
        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Chennai"));
        intent2.setData(geoUri);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);

        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,
                        "Wearable specific test", actionPendingIntent)
                        .build();

        // Build the notification and add the action via WearableExtender
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.arrow_down_float)
                        .setContentTitle("Wearable specific")
                        .setContentText("Wearable specific notification")
                        .addAction(R.mipmap.ic_launcher, "Map test", pendingIntent2)
                        .extend(new NotificationCompat.WearableExtender().addAction(action));

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyBigViewStyle(View view) {

        int notificationID = 103;

        // Build intent for notification content.
        Intent intent = new Intent(this, ScrollingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Build intent for notification map.
        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Chennai"));
        intent2.setData(geoUri);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);

        // Specify the 'big view' content to display the long
        // event description that may not fit the normal content text.
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("Event Description - Notice that you can add a large icon image to any notification using the setLargeIcon() method. However, these icons appear as large background images on a wearable and do not look good as they are scaled up to fit the wearable screen. " +
                "To add a wearable-specific background image to a notification, see Add Wearable Features For a Notification. For more information about designing notifications with large images, see the Design Principles of Android Wear.\n" +
                "\n");


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_map)
                .setContentTitle("Map Intent")
                .setContentText("Content Text - You can insert extended text content to your notification by adding one of the \"big view\" styles to your notification. On a handheld device, " +
                        "users can see the big view content by expanding the notification. On a wearable device, the big view content is visible by default.")
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Map test", pendingIntent2)
                .setStyle(bigTextStyle)
        ;

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyInboxStyle(View view) {

        int notificationID = 104;

        // Build intent for notification content.
        Intent intent = new Intent(this, ScrollingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Build intent for notification map.
        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Chennai"));
        intent2.setData(geoUri);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine("Event Description - Notice that you can add a large icon image to any notification using the setLargeIcon() method. However, these icons appear as large background images on a wearable and do not look good as they are scaled up to fit the wearable screen. " +
                "To add a wearable-specific background image to a notification, see Add Wearable Features For a Notification. For more information about designing notifications with large images, see the Design Principles of Android Wear.\n" +
                "\n");


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_map)
                .setContentTitle("Map Intent")
                .setContentText("Content Text - You can insert extended text content to your notification by adding one of the \"big view\" styles to your notification. On a handheld device, " +
                        "users can see the big view content by expanding the notification. On a wearable device, the big view content is visible by default.")
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Map test", pendingIntent2)
                .setStyle(inboxStyle)
        ;

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyHideIcon(View view) {

        int notificationID = 105;

        // Create an intent
        Intent actionIntent = new Intent(this, ScrollingActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build intent for notification map.
        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Chennai"));
        intent2.setData(geoUri);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);

        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,
                        "Wearable specific test", actionPendingIntent)
                        .build();

        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
        extender.setHintHideIcon(true);


        // Build the notification and add the action via WearableExtender
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.arrow_down_float)
                        .setContentTitle("Wearable specific")
                        .setContentText("Wearable specific notification")
                        .addAction(R.mipmap.ic_launcher, "Map test", pendingIntent2)
                        .extend(extender.addAction(action));

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyReplyVoice(View view) {

        // Define for Voice Input
        // To create an action that supports voice input, create an instance of RemoteInput.Builder that you can add
        // to your notification action. This class's constructor accepts a string that the system uses as the key for the voice input,
        // which you will later use to retrieve the text o the input in your handheld app.

        // Key for the string that's delivered in the action's intent.
        String replyLabel = "Hi Sasi, Reply via";

        RemoteInput remoteInput = new RemoteInput.Builder("EXTRA_VOICE_REPLY_KEY")
                .setLabel(replyLabel)
                .build();

        int notificationID = 106;

        // Create an intent
        Intent actionIntent = new Intent(this, ScrollingActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build intent for notification map.
        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Chennai"));
        intent2.setData(geoUri);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);

        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_reply,
                        "Wearable specific test", actionPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
//        extender.setHintHideIcon(true);


        // Build the notification and add the action via WearableExtender
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.arrow_down_float)
                        .setContentTitle("Wearable specific")
                        .setContentText("Wearable specific notification")
                        .addAction(R.mipmap.ic_launcher, "Map test", pendingIntent2)
                        .extend(extender.addAction(action));

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyReplyVoiceAndChoice(View view) {

        // Define for Voice Input
        // To create an action that supports voice input, create an instance of RemoteInput.Builder that you can add
        // to your notification action. This class's constructor accepts a string that the system uses as the key for the voice input,
        // which you will later use to retrieve the text o the input in your handheld app.

        // Key for the string that's delivered in the action's intent.
        String replyLabel = "Hi Sasi, Reply/Choice";
        String[] replyChoice = getResources().getStringArray(R.array.reply_choices);

        RemoteInput remoteInput = new RemoteInput.Builder("EXTRA_VOICE_REPLY_KEY")
                .setLabel(replyLabel)
                .setChoices(replyChoice)
                .build();

        int notificationID = 107;

        // Create an intent
        Intent actionIntent = new Intent(this, ScrollingActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build intent for notification map.
        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Chennai"));
        intent2.setData(geoUri);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);

        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_reply,
                        "Wearable specific test", actionPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
//        extender.setHintHideIcon(true);


        // Build the notification and add the action via WearableExtender
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.arrow_down_float)
                        .setContentTitle("Wearable specific")
                        .setContentText("Wearable specific notification")
                        .addAction(R.mipmap.ic_launcher, "Map test", pendingIntent2)
                        .extend(extender.addAction(action));

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyMultiplePages(View view) {

        int notificationID = 108;

        // Build intent for notification content.
        Intent intent = new Intent(this, ScrollingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        // Build the second page notification.
        // Create BigTextStyle
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("Event Description - Notice that you can add a large icon image to any notification using the setLargeIcon() method. However, these icons appear as large background images on a wearable and do not look good as they are scaled up to fit the wearable screen. " +
                "To add a wearable-specific background image to a notification, see Add Wearable Features For a Notification. For more information about designing notifications with large images, see the Design Principles of Android Wear.\n" +
                "\n");

        // Create the page 2.
        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);
        builder2.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setStyle(bigTextStyle);


        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();

        // Build the notification and add the action via WearableExtender
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.arrow_down_float)
                        .setContentTitle("Page 1 Content")
                        .setContentText("Short message")
                        .addAction(R.mipmap.ic_launcher, "Map test", pendingIntent)
                        .extend(extender.addPage(builder2.build()));

        // Issue the notification
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(notificationID, builder.build());
    }

    public void notifyStack(View view) {

        ++mNotificationID;

        // Build intent for notification content.
        Intent intent = new Intent(this, ScrollingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("Content Title " + mNotificationID)
                .setContentText("Content Text " + mNotificationID)
                .setContentIntent(pendingIntent)
                .setGroup(mGroupKey);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(mNotificationID, builder.build());
    }

    public void notifyStackGroupSummary(View view) {

        ++mNotificationID2;

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_stat_name);

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setBackground(largeIcon);

        // Create an InboxStyle notification
        Notification summaryNotification = new NotificationCompat.Builder(this)
                .setContentTitle("2 new messages")
                .setSmallIcon(android.R.drawable.arrow_down_float)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Alex Faaborg   Check this out")
                        .addLine("Jeff Chang   Launch Party")
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("sasi@gmail.com"))
                .extend(wearableExtender)
                .setGroup(mGroupKey2)
                .setGroupSummary(true)
                .build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(mNotificationID2, summaryNotification);
    }

}


