package dev.emg.asynctaskandasynctaskloader;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by emeruvia on 6/18/2019.
 */
public class FetchBook extends AsyncTask<String, Void, String> {

  private WeakReference<TextView> mTitleText;
  private WeakReference<TextView> mAuthorText;

  public FetchBook(TextView titleText, TextView authorText) {
    mTitleText = new WeakReference<>(titleText);
    mAuthorText = new WeakReference<>(authorText);
  }

  @Override
  protected String doInBackground(String... strings) {
    return NetworkUtils.getBookInfo(strings[0]);
  }

  @Override
  protected void onPostExecute(String s) {
    super.onPostExecute(s);
    try {
      JSONObject jsonObject = new JSONObject(s);
      JSONArray itemsArray = jsonObject.getJSONArray("items");
      int i = 0;
      String title = null;
      String authors = null;
      while (i < itemsArray.length() && (title == null && authors == null)) {
        JSONObject book = itemsArray.getJSONObject(i);
        JSONObject volumeInfo = book.getJSONObject("volumeInfo");
        try {
          title = volumeInfo.getString("title");
          authors = volumeInfo.getString("authors");
        } catch (Exception e) {
          e.printStackTrace();
        }
        i++;
      }

      if (title != null && authors != null) {
        mTitleText.get().setText(title);
        mAuthorText.get().setText(authors);
      } else {
        mTitleText.get().setText(R.string.no_results);
        mAuthorText.get().setText("");
      }
    } catch (JSONException e) {
      e.printStackTrace();
      mTitleText.get().setText(R.string.no_results);
      mAuthorText.get().setText("");
    }
  }
}
