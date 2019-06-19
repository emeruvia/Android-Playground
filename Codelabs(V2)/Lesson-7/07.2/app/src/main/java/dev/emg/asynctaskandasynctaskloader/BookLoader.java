package dev.emg.asynctaskandasynctaskloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by emeruvia on 6/19/2019.
 */
public class BookLoader extends AsyncTaskLoader<String> {

  private String mQueryString;

  public BookLoader(@NonNull Context context, String queryString) {
    super(context);
    mQueryString = queryString;
  }

  @Override
  protected void onStartLoading() {
    super.onStartLoading();
    forceLoad();
  }

  @Nullable
  @Override
  public String loadInBackground() {
    return NetworkUtils.getBookInfo(mQueryString);
  }
}
