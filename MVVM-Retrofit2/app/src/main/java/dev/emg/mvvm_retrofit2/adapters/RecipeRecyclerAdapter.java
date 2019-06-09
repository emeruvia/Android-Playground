package dev.emg.mvvm_retrofit2.adapters;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import dev.emg.mvvm_retrofit2.R;
import dev.emg.mvvm_retrofit2.model.Recipe;
import dev.emg.mvvm_retrofit2.util.Constants;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by emeruvia on 5/13/2019.
 */
public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int RECIPE_TYPE = 1;
  private static final int LOADING_TYPE = 2;
  private static final int CATEGORY_TYPE = 3;

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
        Log.d(TAG, "onCreateViewHolder: RECIPE_TYPE");
        view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_recipe_list_item, parent, false);
        return new RecipeViewHolder(view, mOnRecipeListener);
      case LOADING_TYPE:
        Log.d(TAG, "onCreateViewHolder: LOADING_TYPE");
        view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_loading_list_item, parent, false);
        return new LoadingViewHolder(view);
      case CATEGORY_TYPE:
        Log.d(TAG, "onCreateViewHolder: CATEGORY_TYPE");
        view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_category_list_item, parent, false);
        return new CategoryViewHolder(view, mOnRecipeListener);
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
          .centerCrop()
          .error(R.drawable.ic_launcher_background);

      Glide.with(holder.itemView.getContext())
          .setDefaultRequestOptions(requestOptions)
          .load(mRecipes.get(position).getImageUrl())
          .into(((RecipeViewHolder) holder).image);

      ((RecipeViewHolder) holder).title.setText(mRecipes.get(position).getTitle());
      ((RecipeViewHolder) holder).publisher.setText(mRecipes.get(position).getPublisher());
      ((RecipeViewHolder) holder).socialScore.setText(
          String.valueOf(Math.round(mRecipes.get(position).getSocialRank())));
    } else if (itemViewType == CATEGORY_TYPE) {
      RequestOptions requestOptions = new RequestOptions()
          .centerCrop()
          .error(R.drawable.ic_launcher_background);

      Uri path = Uri.parse(
          "android.resource://dev.emg.mvvm_retrofit2/drawable/" +
              mRecipes.get(position).getImageUrl());
      Glide.with(holder.itemView.getContext())
          .setDefaultRequestOptions(requestOptions)
          .load(path)
          .into(((CategoryViewHolder) holder).categoryImage);
      ((CategoryViewHolder) holder).categoryTitle.setText(mRecipes.get(position).getTitle());
    }
  }

  @Override public int getItemViewType(int position) {
    if (mRecipes.get(position).getSocialRank() == -1) {
      return CATEGORY_TYPE;
    } else if (mRecipes.get(position).getTitle().equals("LOADING...")) {
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

  public void displaySearchCategories(){
    List<Recipe> categories = new ArrayList<>();
    for(int i = 0; i < Constants.DEFAULT_SEARCH_CATEGORIES.length; i++){
      Recipe recipe = new Recipe();
      recipe.setTitle(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
      recipe.setImageUrl(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
      recipe.setSocialRank(-1);
      categories.add(recipe);
    }
    mRecipes = categories;
    notifyDataSetChanged();
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
