package dev.emg.mvvm_retrofit2;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.emg.mvvm_retrofit2.adapters.OnRecipeListener;
import dev.emg.mvvm_retrofit2.adapters.RecipeRecyclerAdapter;
import dev.emg.mvvm_retrofit2.model.Recipe;

import dev.emg.mvvm_retrofit2.util.Testing;
import dev.emg.mvvm_retrofit2.util.VerticalSpacingItemDecorator;
import java.util.List;

import dev.emg.mvvm_retrofit2.viewmodels.RecipeListViewModel;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

  private static final String TAG = "RecipeListActivity";

  private RecipeListViewModel mRecipeListViewModel;
  private RecyclerView mRecyclerView;
  private RecipeRecyclerAdapter mAdapter;
  private SearchView mSearchView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);
    mRecyclerView = findViewById(R.id.recipe_list);
    mSearchView = findViewById(R.id.search_view);
    // Reference and instantiation of the ViewModel
    mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
    initRecyclerView();
    subscribeObservers();
    initSearchView();
    if (!mRecipeListViewModel.isViewingRecipes()) {
      // display search categories
      displaySearchCategories();
    }
    setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
  }

  // Observe the livedata object
  private void subscribeObservers() {
    mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(List<Recipe> recipes) {
        // onChanged method will trigger if anything is changed or added to the list of recipes
        if (recipes != null) {
          if (mRecipeListViewModel.isViewingRecipes()) {
            Testing.printRecipes(recipes, TAG);
            mRecipeListViewModel.setIsPerformingQuery(false);
            mAdapter.setRecipes(recipes);
          }
        }
      }
    });
  }

  private void initRecyclerView() {
    mAdapter = new RecipeRecyclerAdapter(this);
    VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
    mRecyclerView.addItemDecoration(itemDecorator);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (!mRecyclerView.canScrollVertically(1)) {
          mRecipeListViewModel.searchNextPage();
        }
      }
    });
  }

  private void initSearchView() {
    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        mAdapter.displayLoading();
        mRecipeListViewModel.searchRecipesApi(query, 1);
        mSearchView.clearFocus();
        return false;
      }

      @Override public boolean onQueryTextChange(String newText) {
        // Queries as the user types
        return false;
      }
    });
  }

  private void searchRecipesApi(String query, int pageNumber) {
    mRecipeListViewModel.searchRecipesApi(query, pageNumber);
  }

  @Override public void onRecipeClick(int position) {
    Log.d(TAG, "onRecipeClick: " + position);
  }

  @Override public void onCategoryClick(String category) {
    Log.d(TAG, "onCategoryClick: " + category);
    mAdapter.displayLoading();
    mRecipeListViewModel.searchRecipesApi(category, 1);
    mSearchView.clearFocus();
  }

  private void displaySearchCategories() {
    mRecipeListViewModel.setIsViewingRecipes(false);
    mAdapter.displaySearchCategories();
  }

  @Override public void onBackPressed() {
    if (mRecipeListViewModel.onBackPressed()) {
      super.onBackPressed();
    } else {
      displaySearchCategories();
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_categories) {
      displaySearchCategories();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.recipe_search_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }
}