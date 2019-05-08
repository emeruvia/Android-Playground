package dev.emg.mvvm_retrofit2;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import dev.emg.mvvm_retrofit2.model.Recipe;
import dev.emg.mvvm_retrofit2.requests.RecipeApi;
import dev.emg.mvvm_retrofit2.requests.ServiceGenerator;
import dev.emg.mvvm_retrofit2.requests.responses.RecipeSearchResponse;

import java.util.ArrayList;
import java.util.List;

import dev.emg.mvvm_retrofit2.viewmodels.RecipeListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

  private static final String TAG = "RecipeListActivity";

  private RecipeListViewModel mRecipeListViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);
    // Reference and instantiation of the ViewModel
    mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
    subscribeObservers();
    findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        testRetrofitRequest();
      }
    });
  }

  // Observe the livedata object
  private void subscribeObservers() {
    mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(List<Recipe> recipes) {
        // onChanged method will trigger if anything is changed or added to the list of recipes
        if (recipes != null) {
          for (Recipe recipe : recipes) {
            Log.d(TAG, "onChanged: " + recipe.getTitle());
          }
        }
      }
    });
  }

  private void searchRecipesApi(String query, int pageNumber) {
    mRecipeListViewModel.searchRecipesApi(query, pageNumber);
  }

  private void testRetrofitRequest() {
    searchRecipesApi("chicken breast", 1);
  }
}