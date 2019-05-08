package dev.emg.mvvm_retrofit2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.emg.mvvm_retrofit2.model.Recipe;
import dev.emg.mvvm_retrofit2.repositories.RecipeRepository;

/**
 * Created by emeruvia on 5/6/2019.
 */
public class RecipeListViewModel extends ViewModel {

  private RecipeRepository mRecipeRepository;

  public RecipeListViewModel() {
    mRecipeRepository = RecipeRepository.getInstance();
  }

  public LiveData<List<Recipe>> getRecipes() {
    return mRecipeRepository.getRecipes();
  }

  public void searchRecipesApi(String query, int pageNumber) {
    mRecipeRepository.searchRecipesApi(query, pageNumber);
  }
}
