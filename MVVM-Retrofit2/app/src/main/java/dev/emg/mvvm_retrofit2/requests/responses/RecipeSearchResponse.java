package dev.emg.mvvm_retrofit2.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dev.emg.mvvm_retrofit2.model.Recipe;
import java.util.List;

/**
 * Created by Edgar Meruvia on 5/3/2019.
 */
public class RecipeSearchResponse {

  @SerializedName("count")
  @Expose()
  private int count;

  @SerializedName("recipes")
  @Expose
  private List<Recipe> recipes;

  public int getCount() {
    return count;
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  @Override public String toString() {
    return "RecipeSearchResponse{" +
        "count=" + count +
        ", recipes=" + recipes +
        '}';
  }
}
