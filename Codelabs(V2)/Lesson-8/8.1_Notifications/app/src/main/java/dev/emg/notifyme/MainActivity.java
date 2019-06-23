package dev.emg.notifyme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button notifyBtn;
  private static final int NOTIFICATION_ID = 0;
  private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
  private NotificationManager mNotificationManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    notifyBtn = findViewById(R.id.notify);
    createNotificationChannel();

    notifyBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        sendNotification();
      }
    });
  }

  private void sendNotification() {
    NotificationCompat.Builder builder = getNotificationBuilder();
    mNotificationManager.notify(NOTIFICATION_ID, builder.build());
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

  private NotificationCompat.Builder getNotificationBuilder() {
    Intent notificationIntent = new Intent(this, MainActivity.class);
    PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
        NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID);
    builder.setContentTitle("You've been notified")
        .setContentText("This is your notification text.")
        .setSmallIcon(R.drawable.ic_notification)
        .setContentIntent(notificationPendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setDefaults(NotificationCompat.DEFAULT_ALL);
    return builder;
  }
}
