package dev.emg.mvvm_retrofit2.util;

import android.util.Log;
import dev.emg.mvvm_retrofit2.model.Recipe;
import java.util.List;

/**
 * Created by emeruvia on 5/8/2019.
 */
public class Testing {

  public static void printRecipes(List<Recipe> list, String tag) {
    for (Recipe recipe : list) {
      Log.d(tag, "printRecipes: " + recipe.getTitle());
    }
  }
}
