package dev.emg.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    String intentAction = intent.getAction();

    if (intentAction != null) {
      String toastMessage = "unkown intent action";
      switch (intentAction) {
        case Intent.ACTION_POWER_CONNECTED:
          toastMessage = "Power connected";
          break;
        case Intent.ACTION_POWER_DISCONNECTED:
          toastMessage = "Power disconnected";
          break;
        case Intent.ACTION_SCREEN_ON:
          toastMessage = "Power screen on";
          break;
      }
      Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
  }
}
