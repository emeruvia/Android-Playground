package dev.emg.notifyme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button notifyBtn;
  private Button updateBtn;
  private Button cancelBtn;

  private static final int NOTIFICATION_ID = 0;
  private NotificationManager mNotificationManager;
  private NotificationReceiver mReceiver = new NotificationReceiver();
  private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
  private static final String ACTION_UPDATE_NOTIFICATION =
      "dev.emg.notifyme.ACTION_UPDATE_NOTIFICATION";
  private static final String ACTION_DISMISS_NOTIFICATION =
      "dev.emg.notifyme.ACTION_DISMISS_NOTIFICATION";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    notifyBtn = findViewById(R.id.notify);
    updateBtn = findViewById(R.id.update);
    cancelBtn = findViewById(R.id.cancel);
    createNotificationChannel();
    setNotificationButtonState(true, false, false);

    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(ACTION_UPDATE_NOTIFICATION);
    intentFilter.addAction(ACTION_DISMISS_NOTIFICATION);
    registerReceiver(mReceiver, intentFilter);

    notifyBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        sendNotification();
      }
    });
    updateBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateNotification();
      }
    });
    cancelBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        cancelNotification();
      }
    });
  }

  @Override
  protected void onDestroy() {
    unregisterReceiver(mReceiver);
    super.onDestroy();
  }

  private void sendNotification() {
    Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
    PendingIntent updatePendingIntent = PendingIntent.getBroadcast(
        this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);
    NotificationCompat.Builder builder = getNotificationBuilder();
    builder.addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent);
    mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    setNotificationButtonState(false, true, true);
  }

  private void updateNotification() {
    Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
    NotificationCompat.Builder builder = getNotificationBuilder();
    builder.setStyle(new NotificationCompat.BigPictureStyle()
        .bigPicture(androidImage)
        .setBigContentTitle("Notification Updated!")
    );
    mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    setNotificationButtonState(false, false, true);
  }

  private void cancelNotification() {
    mNotificationManager.cancel(NOTIFICATION_ID);
    setNotificationButtonState(true, false, false);
  }

  public void createNotificationChannel() {
    mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      // Create NotificationChannel if the API level is lower than 26
      NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
          "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);
      notificationChannel.enableLights(true);
      notificationChannel.setLightColor(Color.RED);
      notificationChannel.enableVibration(true);
      notificationChannel.setDescription("Notification from Mascot");
      mNotificationManager.createNotificationChannel(notificationChannel);

    }
  }

  private void setNotificationButtonState(
      Boolean isNotifyEnabled, Boolean isUpdateEnabled, Boolean isCancelEnabled) {
    notifyBtn.setEnabled(isNotifyEnabled);
    updateBtn.setEnabled(isUpdateEnabled);
    cancelBtn.setEnabled(isCancelEnabled);
  }

  private NotificationCompat.Builder getNotificationBuilder() {
    Intent notificationIntent = new Intent(this, MainActivity.class);
    PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
        NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    // Acts when the notification is dismissed
    Intent dismissIntent = new Intent(ACTION_DISMISS_NOTIFICATION);
    PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(
        this, NOTIFICATION_ID, dismissIntent, PendingIntent.FLAG_ONE_SHOT);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID);
    builder.setContentTitle("You've been notified")
        .setContentText("This is your notification text.")
        .setSmallIcon(R.drawable.ic_notification)
        .setContentIntent(notificationPendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setDeleteIntent(dismissPendingIntent);
    return builder;
  }

  public class NotificationReceiver extends BroadcastReceiver {

    NotificationReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
      String intentAction = intent.getAction();
      if (intentAction != null) {
        switch (intentAction) {
          case ACTION_UPDATE_NOTIFICATION:
            updateNotification();
            break;
          case ACTION_DISMISS_NOTIFICATION:
            setNotificationButtonState(true, false, false);
            break;
        }
      }
    }
  }
}
