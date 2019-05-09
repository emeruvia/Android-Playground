package dev.emg.notificationscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private JobScheduler mScheduler;
  private static final int JOB_ID = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void scheduleJob(View view) {
    RadioGroup networkOptions = findViewById(R.id.networkOptions);
    int selectedNetworkId = networkOptions.getCheckedRadioButtonId();
    int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;

    mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

    switch (selectedNetworkId) {
      case R.id.noNetwork:
        selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
        break;
      case R.id.anyNetwork:
        selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
        break;
      case R.id.wifiNetwork:
        selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
        break;
    }

    ComponentName serviceName =
        new ComponentName(getPackageName(), NotificationJobService.class.getName());
    JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
    builder.setRequiredNetworkType(selectedNetworkOption);

    JobInfo myJobInfo = builder.build();
    mScheduler.schedule(myJobInfo);

    Toast.makeText(this, "Job Scheduled, job will run when the constraints are met",
        Toast.LENGTH_SHORT).show();
  }

  public void cancelJobs(View view) {
    if (mScheduler != null) {
      mScheduler.cancelAll();
      mScheduler = null;
      Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
    }
  }
}
