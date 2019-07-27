package dev.emg.rxjavaandrxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  private TextView text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    text = findViewById(R.id.text);

    Observable<Task> taskObservable = Observable
        .fromIterable(DataSource.createTaskList())
        .subscribeOn(Schedulers.io())
        .filter(new Predicate<Task>() {
          @Override public boolean test(Task task) throws Exception {
            Log.d(TAG, "test: " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return task.isComplete();
          }
        })
        .observeOn(AndroidSchedulers.mainThread());

    taskObservable.subscribe(new Observer<Task>() {
      @Override public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: called.");
      }

      @Override public void onNext(Task task) {
        Log.d(TAG, "onNext: " + Thread.currentThread().getName());
        Log.d(TAG, "onNext: " + task.getDescription());
      }

      @Override public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
      }

      @Override public void onComplete() {
        Log.d(TAG, "onComplete: called.");
      }
    });
  }
}
