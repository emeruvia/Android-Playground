package dev.emg.rxjavaandrxandroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emeruvia on 7/6/2019.
 */
public class DataSource {

  public static List<Task> createTaskList() {
    List<Task> tasks = new ArrayList<>();
    tasks.add(new Task("Take out the trash", true, 3));
    tasks.add(new Task("Walk the dog", false, 2));
    tasks.add(new Task("Make my be", true, 1));
    tasks.add(new Task("Unload the dishwasher", false, 0));
    tasks.add(new Task("Make dinner", true, 5));
    return tasks;
  }
}
