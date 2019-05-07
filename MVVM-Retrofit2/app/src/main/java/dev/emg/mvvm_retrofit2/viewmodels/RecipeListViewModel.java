package dev.emg.mvvm_retrofit2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.emg.mvvm_retrofit2.model.Recipe;

/**
 * Created by emeruvia on 5/6/2019.
 */
public class RecipeListViewModel extends ViewModel {

  private LiveData<List<Recipe>> mRecipes = new MutableLiveData<>();

  public RecipeListViewModel() {

  }

  public LiveData<List<Recipe>> getRecipes() {
    return mRecipes;
  }

}
