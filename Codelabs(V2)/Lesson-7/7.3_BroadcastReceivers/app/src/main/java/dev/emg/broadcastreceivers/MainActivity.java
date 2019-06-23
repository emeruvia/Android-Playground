package dev.emg.broadcastreceivers;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  public static final String ACTION_CUSTOM_BROADCAST =
      BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
  private CustomReceiver mReceiver = new CustomReceiver();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    IntentFilter filter = new IntentFilter();
    filter.addAction(Intent.ACTION_SCREEN_ON);
    filter.addAction(Intent.ACTION_POWER_CONNECTED);
    filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
    this.registerReceiver(mReceiver, filter);
    LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
  }

  @Override
  protected void onDestroy() {
    this.unregisterReceiver(mReceiver);
    LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    super.onDestroy();
  }

  public void sendCustomBroadcast(View view) {
    // Sends the broadcast locally, meaning it can only be used inside the application
    Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
    LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
  }
}
