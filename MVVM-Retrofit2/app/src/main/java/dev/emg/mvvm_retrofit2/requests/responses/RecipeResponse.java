package dev.emg.mvvm_retrofit2.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dev.emg.mvvm_retrofit2.model.Recipe;

/**
 * Created by Edgar Meruvia on 5/3/2019.
 */
public class RecipeResponse {

  @SerializedName("recipe")
  @Expose()
  private Recipe recipe;

  public Recipe getRecipe() {
    return recipe;
  }

  @Override public String toString() {
    return "RecipeResponse{" +
        "recipe=" + recipe +
        '}';
  }
}
