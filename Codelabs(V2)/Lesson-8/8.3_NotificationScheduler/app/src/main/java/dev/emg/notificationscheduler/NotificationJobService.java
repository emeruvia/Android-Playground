package dev.emg.notificationscheduler;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by emeruvia on 6/24/2019.
 */
public class NotificationJobService extends JobService {

  NotificationManager mNotifyManager;
  private static final int NOTIFICATION_ID = 0;
  private static final String PRIMARY_CHANNEL_ID =
      "primary_notification_channel";

  @Override public boolean onStartJob(JobParameters jobParameters) {
    createNotificationChannel();
    PendingIntent contentPendingIntent = PendingIntent.getActivity
        (this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder builder =
        new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("Job Service")
            .setContentText("Your Job is running!")
            .setContentIntent(contentPendingIntent)
            .setSmallIcon(R.drawable.ic_job_running)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true);
    mNotifyManager.notify(NOTIFICATION_ID, builder.build());
    return false;
  }

  @Override public boolean onStopJob(JobParameters jobParameters) {
    return false;
  }

  public void createNotificationChannel() {
    mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel notificationChannel = new NotificationChannel(
          PRIMARY_CHANNEL_ID, "Job Service notification", NotificationManager.IMPORTANCE_HIGH);
      notificationChannel.enableLights(true);
      notificationChannel.setLightColor(Color.RED);
      notificationChannel.enableVibration(true);
      notificationChannel.setDescription("Notifications from Job service");
      mNotifyManager.createNotificationChannel(notificationChannel);
    }
  }
}
