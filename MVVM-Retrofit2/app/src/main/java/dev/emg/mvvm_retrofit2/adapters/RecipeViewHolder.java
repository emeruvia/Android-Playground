package dev.emg.mvvm_retrofit2.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import dev.emg.mvvm_retrofit2.R;

/**
 * Created by emeruvia on 5/13/2019.
 */
public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  TextView title, publisher, socialScore;
  AppCompatImageView image;
  OnRecipeListener onRecipeListener;

  public RecipeViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
    super(itemView);
    this.onRecipeListener = onRecipeListener;
    title = itemView.findViewById(R.id.recipe_title);
    publisher = itemView.findViewById(R.id.recipe_publisher);
    socialScore = itemView.findViewById(R.id.recipe_social_score);
    image = itemView.findViewById(R.id.recipe_image);

    itemView.setOnClickListener(this);
  }

  @Override public void onClick(View view) {
    onRecipeListener.onRecipeClick(getAdapterPosition());
  }
}
