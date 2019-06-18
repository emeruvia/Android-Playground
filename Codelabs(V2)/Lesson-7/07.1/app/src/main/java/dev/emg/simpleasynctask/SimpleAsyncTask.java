package dev.emg.simpleasynctask;

import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * Created by emeruvia on 6/17/2019.
 */
public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

  private WeakReference<TextView> mTextView;
  private WeakReference<ProgressBar> mProgressBar;
  private int sleepTime;

  public SimpleAsyncTask(TextView textView, ProgressBar progressBar) {
    mTextView = new WeakReference<>(textView);
    mProgressBar = new WeakReference<>(progressBar);
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    Random random = new Random();
    sleepTime = random.nextInt(11);
    mProgressBar.get().setMax(sleepTime);
  }

  @Override
  protected String doInBackground(Void... voids) {
    int milliSeconds = sleepTime * 200;
    for (int i = 1; i <= sleepTime; i++) {
      try {
        Thread.sleep(milliSeconds);
        publishProgress(i);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return "Awake at last after sleeping for " + milliSeconds + " milliseconds";
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    super.onProgressUpdate(values);
    mProgressBar.get().setProgress(values[0]);
  }

  @Override
  protected void onPostExecute(String s) {
    mTextView.get().setText(s);
    mProgressBar.get().setVisibility(View.GONE);
  }
}
