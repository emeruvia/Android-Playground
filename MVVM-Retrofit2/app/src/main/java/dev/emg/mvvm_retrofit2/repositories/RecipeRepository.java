package dev.emg.mvvm_retrofit2.repositories;

import androidx.lifecycle.LiveData;

import dev.emg.mvvm_retrofit2.requests.RecipeApiClient;
import java.util.List;

import dev.emg.mvvm_retrofit2.model.Recipe;

/**
 * Created by emeruvia on 5/6/2019.
 */
public class RecipeRepository {

  private static RecipeRepository instance;
  private RecipeApiClient mRecipeApiClient;

  public static RecipeRepository getInstance() {
    if (instance == null) {
      instance = new RecipeRepository();
    }
    return instance;
  }

  private RecipeRepository() {
    mRecipeApiClient = RecipeApiClient.getInstance();
  }

  public LiveData<List<Recipe>> getRecipes() {
    return mRecipeApiClient.getRecipes();
  }

  public void searchRecipesApi(String query, int pageNumber) {
    if (pageNumber == 0) {
      // set pageNumber to 1 as default if pageNumber value is 0.
      pageNumber = 1;
    }
    mRecipeApiClient.searchRecipesApi(query, pageNumber);
  }

  public void cancelRequest() {
    mRecipeApiClient.cancelRequest();
  }
}
