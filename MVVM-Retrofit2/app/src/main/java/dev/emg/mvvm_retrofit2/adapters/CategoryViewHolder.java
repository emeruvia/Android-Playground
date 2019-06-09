package dev.emg.mvvm_retrofit2.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import dev.emg.mvvm_retrofit2.R;

/**
 * Created by emeruvia on 6/9/2019.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  private OnRecipeListener mListener;
  CircleImageView categoryImage;
  TextView categoryTitle;

  public CategoryViewHolder(@NonNull View itemView, OnRecipeListener listener) {
    super(itemView);
    this.mListener = listener;
    categoryImage = itemView.findViewById(R.id.category_image);
    categoryTitle = itemView.findViewById(R.id.category_title);
    itemView.setOnClickListener(this);
  }

  @Override public void onClick(View view) {
    mListener.onCategoryClick(categoryTitle.getText().toString());
  }
}
