package dev.emg.mvvm_retrofit2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by emeruvia on 5/7/2019.
 */
public class AppExecutors {

  private static AppExecutors instance;

  /**
   * Create a pool of three threads to do all the work required for the application.
   * ScheduledExecutorService is an Executor service that schedules commands to run after a given
   * delay. Same thing as an executor, essentially run executable tasks.
   */
  private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

  public static AppExecutors getInstance() {
    if (instance == null) {
      instance = new AppExecutors();
    }
    return instance;
  }

  public ScheduledExecutorService networkIO() {
    return mNetworkIO;
  }
}
