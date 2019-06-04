package dev.emg.mvvm_retrofit2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import dev.emg.mvvm_retrofit2.R;
import dev.emg.mvvm_retrofit2.model.Recipe;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emeruvia on 5/13/2019.
 */
public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int RECIPE_TYPE = 1;
  private static final int LOADING_TYPE = 2;

  private List<Recipe> mRecipes;
  private OnRecipeListener mOnRecipeListener;

  public RecipeRecyclerAdapter(OnRecipeListener mOnRecipeListener) {
    this.mOnRecipeListener = mOnRecipeListener;
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = null;
    switch (viewType) {
      case RECIPE_TYPE:
        view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_recipe_list_item, parent, false);
        return new RecipeViewHolder(view, mOnRecipeListener);
      case LOADING_TYPE:
        view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_loading_list_item, parent, false);
        return new LoadingViewHolder(view);
      default:
        view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_recipe_list_item, parent, false);
        return new RecipeViewHolder(view, mOnRecipeListener);
    }
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    int itemViewType = getItemViewType(position);
    if (itemViewType == RECIPE_TYPE) {
      RequestOptions requestOptions = new RequestOptions()
          .placeholder(R.drawable.ic_launcher_background);

      Glide.with(holder.itemView.getContext())
          .setDefaultRequestOptions(requestOptions)
          .load(mRecipes.get(position).getImageUrl())
          .into(((RecipeViewHolder) holder).image);

      ((RecipeViewHolder) holder).title.setText(mRecipes.get(position).getTitle());
      ((RecipeViewHolder) holder).publisher.setText(mRecipes.get(position).getPublisher());
      ((RecipeViewHolder) holder).socialScore.setText(
          String.valueOf(Math.round(mRecipes.get(position).getSocialRank())));
    }
  }

  @Override public int getItemViewType(int position) {
    if (mRecipes.get(position).getTitle().equals("LOADING...")) {
      return LOADING_TYPE;
    }
    return RECIPE_TYPE;
  }

  public void displayLoading() {
    if (!isLoading()) {
      Recipe recipe = new Recipe();
      recipe.setTitle("LOADING...");
      List<Recipe> loadingList = new ArrayList<>();
      loadingList.add(recipe);
      mRecipes = loadingList;
      notifyDataSetChanged();
    }
  }

  private boolean isLoading() {
    if (mRecipes != null) {
      if (mRecipes.size() > 0) {
        return mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...");
      }
    }
    return false;
  }

  @Override public int getItemCount() {
    if (mRecipes != null) {
      return mRecipes.size();
    }
    return 0;
  }

  public void setRecipes(List<Recipe> recipes) {
    mRecipes = recipes;
    notifyDataSetChanged();
  }
}
