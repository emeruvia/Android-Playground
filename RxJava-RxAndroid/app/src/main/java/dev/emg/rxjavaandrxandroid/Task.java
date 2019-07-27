package dev.emg.rxjavaandrxandroid;

/**
 * Created by emeruvia on 7/6/2019.
 */
public class Task {

  private String description;
  private boolean isComplete;
  private int priority;

  public Task(String description, boolean isComplete, int priority) {
    this.description = description;
    this.isComplete = isComplete;
    this.priority = priority;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public void setComplete(boolean complete) {
    isComplete = complete;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }
}
