package dev.emg.simpleasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  private TextView mTextView;
  private ProgressBar mProgressBar;
  private static final String TEXT_STATE = "currentText";
  private static final String PROGRESS_STATE = "currentProgress";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mTextView = findViewById(R.id.task_textView);
    mProgressBar = findViewById(R.id.progressBar);

    if (savedInstanceState != null) {
      mTextView.setText(savedInstanceState.getString(TEXT_STATE));
      mProgressBar.setVisibility(View.VISIBLE);
      mProgressBar.setProgress(
          Integer.valueOf(Objects.requireNonNull(savedInstanceState.getString(PROGRESS_STATE))));
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(TEXT_STATE, mTextView.getText().toString());
    outState.putString(PROGRESS_STATE, String.valueOf(mProgressBar.getProgress()));
  }

  public void startTask(View view) {
    mTextView.setText(R.string.napping);
    mProgressBar.setVisibility(View.VISIBLE);
    mProgressBar.setProgress(0);
    new SimpleAsyncTask(mTextView, mProgressBar).execute();
  }
}
