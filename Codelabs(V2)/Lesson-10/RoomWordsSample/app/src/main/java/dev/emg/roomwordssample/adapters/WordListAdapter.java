package dev.emg.roomwordssample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.emg.roomwordssample.R;
import dev.emg.roomwordssample.model.Word;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

  private final LayoutInflater mInflater;
  private List<Word> mWords;

  public WordListAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
  }

  @NonNull @Override
  public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
    return new WordViewHolder(itemView);
  }

  @Override public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
    if (mWords != null) {
      Word current = mWords.get(position);
      holder.wordItemView.setText(current.getWord());
    } else {
      holder.wordItemView.setText("No Word");
    }
  }

  @Override public int getItemCount() {
    if (mWords != null) {
      return mWords.size();
    }
    return 0;
  }

  public void setWords(List<Word> words) {
    mWords = words;
    notifyDataSetChanged();
  }

  public Word getWordAtPosition(int position) {
    return mWords.get(position);
  }

  class WordViewHolder extends RecyclerView.ViewHolder {

    private final TextView wordItemView;

    public WordViewHolder(@NonNull View itemView) {
      super(itemView);
      wordItemView = itemView.findViewById(R.id.textView);
    }
  }
}
