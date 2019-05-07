package dev.emg.mvvm_retrofit2.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dev.emg.mvvm_retrofit2.model.Recipe;
import java.util.List;

/**
 * Created by emeruvia on 5/6/2019.
 */
public class RecipeApiClient {

  private static RecipeApiClient instance;
  private MutableLiveData<List<Recipe>> mRecipes;

  public static RecipeApiClient getInstance() {
    if (instance == null) {
      instance = new RecipeApiClient();
    }
    return instance;
  }

  private RecipeApiClient() {
    mRecipes = new MutableLiveData<>();
  }

  public LiveData<List<Recipe>> getRecipes() {
    return mRecipes;
  }
}
