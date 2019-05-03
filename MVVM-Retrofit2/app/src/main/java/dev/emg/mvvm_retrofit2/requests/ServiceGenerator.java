package dev.emg.mvvm_retrofit2.requests;

import dev.emg.mvvm_retrofit2.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Edgar Meruvia on 5/2/2019.
 */
public class ServiceGenerator {

  private static Retrofit.Builder retrofitBuilder =
      new Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create());

  private static Retrofit retrofit = retrofitBuilder.build();

  private static RecipeApi recipeApi = retrofit.create(RecipeApi.class);

  public static RecipeApi getRecipeApi() {
    return recipeApi;
  }
}
