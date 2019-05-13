package dev.emg.mvvm_retrofit2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.emg.mvvm_retrofit2.R;
import dev.emg.mvvm_retrofit2.model.Recipe;
import java.util.List;

/**
 * Created by emeruvia on 5/13/2019.
 */
public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<Recipe> mRecipes;
  private OnRecipeListener mOnRecipeListener;

  public RecipeRecyclerAdapter(List<Recipe> mRecipes, OnRecipeListener mOnRecipeListener) {
    this.mRecipes = mRecipes;
    this.mOnRecipeListener = mOnRecipeListener;
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_recipe_list_item, parent, false);
    return new RecipeViewHolder(view, mOnRecipeListener);
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ((RecipeViewHolder) holder).title.setText(mRecipes.get(position).getTitle());
    ((RecipeViewHolder) holder).publisher.setText(mRecipes.get(position).getPublisher());
    ((RecipeViewHolder) holder).socialScore.setText(
        String.valueOf(Math.round(mRecipes.get(position).getSocialRank())));
  }

  @Override public int getItemCount() {
    return mRecipes.size();
  }

  public void setRecipes(List<Recipe> recipes) {
    mRecipes = recipes;
    notifyDataSetChanged();
  }
}
