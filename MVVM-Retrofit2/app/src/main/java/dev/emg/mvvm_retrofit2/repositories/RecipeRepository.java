package dev.emg.mvvm_retrofit2.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dev.emg.mvvm_retrofit2.model.Recipe;

/**
 * Created by emeruvia on 5/6/2019.
 */
public class RecipeRepository {

  private static RecipeRepository instance;
  private MutableLiveData<List<Recipe>> mRecipes;

  public static RecipeRepository getInstance() {
    if (instance == null) {
      instance = new RecipeRepository();
    }
    return instance;
  }

  private RecipeRepository() {

  }

  public LiveData<List<Recipe>> getRecipes() {
    return mRecipes;
  }
}
