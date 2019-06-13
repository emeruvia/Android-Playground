package dev.emg.intreactive_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private TextView countTextView;
  private int mCount = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    countTextView = findViewById(R.id.count_tv);
  }

  public void toastButton(View view) {
    Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show();
  }

  public void countButton(View view) {
    ++mCount;
    if (countTextView != null) {
      countTextView.setText(Integer.toString(mCount));
    }
  }
}
