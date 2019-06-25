package dev.emg.notificationscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  private JobScheduler mScheduler;
  private static final int JOB_ID = 0;

  private Switch mIdleSwitch;
  private Switch mChargingSwitch;
  private SeekBar mSeekBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mIdleSwitch = findViewById(R.id.idle_switch);
    mChargingSwitch = findViewById(R.id.charging_switch);
    mSeekBar = findViewById(R.id.seekbar);
    final TextView seekBarProgress = findViewById(R.id.seekbar_progress);

    mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (i > 0) {
          seekBarProgress.setText(i + " s");
        } else {
          seekBarProgress.setText("Not Set");
        }
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }

  public void scheduleJob(View view) {
    mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    RadioGroup networkOptions = findViewById(R.id.network_options);
    int selectedNetworkID = networkOptions.getCheckedRadioButtonId();
    int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
    int seekBarInteger = mSeekBar.getProgress();
    boolean seekBarSet = seekBarInteger > 0;

    switch (selectedNetworkID) {
      case R.id.no_network:
        selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
        break;
      case R.id.any_network:
        selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
        break;
      case R.id.wifi_network:
        selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
        break;
    }

    Log.d(TAG, "scheduleJob: " + mIdleSwitch.isChecked());
    Log.d(TAG, "scheduleJob: " + mChargingSwitch.isChecked());

    ComponentName serviceName = new ComponentName(getPackageName(),
        NotificationJobService.class.getName());
    JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
        .setRequiredNetworkType(selectedNetworkOption)
        .setRequiresDeviceIdle(mIdleSwitch.isChecked())
        .setRequiresCharging(mChargingSwitch.isChecked());

    if (seekBarSet) {
      builder.setOverrideDeadline(seekBarInteger * 10000);
    }

    boolean constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE)
        || mChargingSwitch.isChecked() || mIdleSwitch.isChecked() || seekBarSet;
    if (constraintSet) {
      JobInfo myJobInfo = builder.build();
      mScheduler.schedule(myJobInfo);
      Toast.makeText(this, "Job scheduled, job will run when the constraints are met",
          Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "Please set at least on constraint", Toast.LENGTH_SHORT).show();
    }
  }

  public void cancelJobs(View view) {
    if (mScheduler != null) {
      mScheduler.cancelAll();
      mScheduler = null;
      Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
    }
  }
}
