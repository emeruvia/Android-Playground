package dev.emg.standup;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

  private NotificationManager mNotificationManager;
  private static final int NOTIFICATION_ID = 0;
  private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Intent notifyIntent = new Intent(this, AlarmReceiver.class);
    final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(
        this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    boolean isAlarmUp = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
        PendingIntent.FLAG_NO_CREATE) != null;

    ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
    alarmToggle.setChecked(isAlarmUp);
    // init the notification service
    mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    createNotificationChannel();

    alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String toastMessage;
        long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        long triggerTime = SystemClock.elapsedRealtime()
            + repeatInterval;
        if (b) {
          if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime, repeatInterval, notifyPendingIntent);
          }
          toastMessage = "Stand Up Alarm On!";
        } else {
          if (alarmManager != null) {
            alarmManager.cancel(notifyPendingIntent);
          }
          mNotificationManager.cancelAll();
          toastMessage = "Stand Up Alarm Off!";
        }
        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void createNotificationChannel() {
    mNotificationManager =
        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel notificationChannel = new NotificationChannel(
          PRIMARY_CHANNEL_ID, "Stand up notification", NotificationManager.IMPORTANCE_HIGH);
      notificationChannel.enableLights(true);
      notificationChannel.setLightColor(Color.RED);
      notificationChannel.enableVibration(true);
      notificationChannel.setDescription("Notifies every 15 minutes to stand up and walk");
      mNotificationManager.createNotificationChannel(notificationChannel);
    }
  }
}
