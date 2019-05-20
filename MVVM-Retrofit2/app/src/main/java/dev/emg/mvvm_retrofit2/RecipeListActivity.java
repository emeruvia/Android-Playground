package dev.emg.mvvm_retrofit2;

import android.os.Bundle;

import android.util.Log;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.emg.mvvm_retrofit2.adapters.OnRecipeListener;
import dev.emg.mvvm_retrofit2.adapters.RecipeRecyclerAdapter;
import dev.emg.mvvm_retrofit2.model.Recipe;

import dev.emg.mvvm_retrofit2.util.Testing;
import java.util.List;

import dev.emg.mvvm_retrofit2.viewmodels.RecipeListViewModel;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

  private static final String TAG = "RecipeListActivity";

  private RecipeListViewModel mRecipeListViewModel;
  private RecyclerView mRecyclerView;
  private RecipeRecyclerAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);
    mRecyclerView = findViewById(R.id.recipe_list);
    // Reference and instantiation of the ViewModel
    mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
    initRecyclerView();
    subscribeObservers();
    testRetrofitRequest();
  }

  // Observe the livedata object
  private void subscribeObservers() {
    mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(List<Recipe> recipes) {
        // onChanged method will trigger if anything is changed or added to the list of recipes
        if (recipes != null) {
          Testing.printRecipes(recipes, TAG);
        }
        mAdapter.setRecipes(recipes);
      }
    });
  }

  private void testRetrofitRequest() {
    searchRecipesApi("chicken breast", 1);
  }

  private void initRecyclerView() {
    mAdapter = new RecipeRecyclerAdapter(this);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  private void searchRecipesApi(String query, int pageNumber) {
    mRecipeListViewModel.searchRecipesApi(query, pageNumber);
  }

  @Override public void onRecipeClick(int position) {
    Log.d(TAG, "onRecipeClick: clicked." + position);
  }

  @Override public void onCategoryClick(String category) {

  }
}