package dev.emg.mvvm_retrofit2;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import dev.emg.mvvm_retrofit2.model.Recipe;
import dev.emg.mvvm_retrofit2.requests.RecipeApi;
import dev.emg.mvvm_retrofit2.requests.ServiceGenerator;
import dev.emg.mvvm_retrofit2.requests.responses.RecipeSearchResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

  private static final String TAG = "RecipeListActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);

    findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        testRetrofitRequest();
      }
    });
  }

  private void testRetrofitRequest() {
    RecipeApi recipeApi = ServiceGenerator.getRecipeApi();

    Call<RecipeSearchResponse> responseCall = recipeApi.searchRecipe(
        getResources().getString(R.string.RECIPE_API_KEY),
        "chicken breast",
        "1");

    responseCall.enqueue(new Callback<RecipeSearchResponse>() {
      @Override public void onResponse(Call<RecipeSearchResponse> call,
          Response<RecipeSearchResponse> response) {
        Log.d(TAG, "onResponse: server response: " + response.toString());
        if (response.code() == 200) {
          Log.d(TAG, "onResponse: response code 200");
          List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
          for (Recipe recipe : recipes) {
            Log.d(TAG, "onResponse: " + recipe.getTitle());
          }
        } else {
          Log.d(TAG, "onResponse: " + response.errorBody().toString());
        }
      }

      @Override public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {

      }
    });
  }
}