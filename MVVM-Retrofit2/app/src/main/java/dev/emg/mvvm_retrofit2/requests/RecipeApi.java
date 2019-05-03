package dev.emg.mvvm_retrofit2.requests;

import dev.emg.mvvm_retrofit2.requests.responses.RecipeResponse;
import dev.emg.mvvm_retrofit2.requests.responses.RecipeSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Edgar Meruvia on 5/2/2019.
 */
public interface RecipeApi {

  // SEARCH REQUEST
  @GET("api/search")
  Call<RecipeSearchResponse> searchRecipe(
      @Query("key") String key,
      @Query("q") String query,
      @Query("page") String page
  );

  // RECIPE REQUEST
  @GET("api/get")
  Call<RecipeResponse> getRecipe(
      @Query("key") String key,
      @Query("rId") String recipe_id
  );
}
