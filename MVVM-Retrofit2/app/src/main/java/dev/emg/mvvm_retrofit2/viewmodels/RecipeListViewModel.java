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
  private boolean mIsViewingRecipes;
  private boolean mIsPerformingQuery;

  public RecipeListViewModel() {
    mRecipeRepository = RecipeRepository.getInstance();
    mIsPerformingQuery = false;
  }

  public LiveData<List<Recipe>> getRecipes() {
    return mRecipeRepository.getRecipes();
  }

  public void searchRecipesApi(String query, int pageNumber) {
    mIsViewingRecipes = true;
    mIsPerformingQuery = true;
    mRecipeRepository.searchRecipesApi(query, pageNumber);
  }

  public void searchNextPage() {
    if (!mIsPerformingQuery && mIsViewingRecipes) {
      mRecipeRepository.searchNextPage();
    }
  }

  public boolean isViewingRecipes() {
    return mIsViewingRecipes;
  }

  public void setIsViewingRecipes(boolean isViewingRecipes) {
    mIsViewingRecipes = isViewingRecipes;
  }

  public boolean isPerformingQuery() {
    return mIsPerformingQuery;
  }

  public void setIsPerformingQuery(boolean isPerformingQuery) {
    mIsPerformingQuery = isPerformingQuery;
  }

  public boolean onBackPressed() {
    if (mIsPerformingQuery) {
      mRecipeRepository.cancelRequest();
      mIsPerformingQuery = false;
    }
    if (mIsViewingRecipes) {
      mIsViewingRecipes = false;
      return false;
    }
    return true;
  }
}
