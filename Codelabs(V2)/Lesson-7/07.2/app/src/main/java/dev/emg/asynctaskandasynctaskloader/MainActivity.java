package dev.emg.asynctaskandasynctaskloader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private EditText mBookInput;
  private TextView mTitleText;
  private TextView mAuthorText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mBookInput = findViewById(R.id.editText_book);
    mTitleText = findViewById(R.id.titleText);
    mAuthorText = findViewById(R.id.authorText);
  }

  public void searchButton(View view) {
    String queryString = mBookInput.getText().toString();
    hideKeyboard(view);
    ConnectivityManager connectivityManager =
        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = null;
    if (connectivityManager != null) {
      networkInfo = connectivityManager.getActiveNetworkInfo();
    }
    if (networkInfo != null && networkInfo.isConnected()
        && queryString.length() != 0) {
      new FetchBook(mTitleText, mAuthorText).execute(queryString);
      mAuthorText.setText("");
      mTitleText.setText(R.string.loading);
    } else {
      mAuthorText.setText("");
      mTitleText.setText((queryString.length() == 0)
          ? R.string.no_search_term : R.string.no_network);
    }
  }

  private void hideKeyboard(View view) {
    InputMethodManager inputMethodManager =
        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    if (inputMethodManager != null) {
      inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
          InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }
}
