package dev.emg.roomwordssample.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import dev.emg.roomwordssample.model.Word;
import dev.emg.roomwordssample.repositories.WordRepository;
import java.util.List;

public class WordViewModel extends AndroidViewModel {

  private WordRepository mRepository;
  private LiveData<List<Word>> mAllWords;

  public WordViewModel(Application application) {
    super(application);
    mRepository = new WordRepository(application);
    mAllWords = mRepository.getAllWords();
  }

  public LiveData<List<Word>> getAllWords() {
    return mAllWords;
  }

  public void insert(Word word) {
    mRepository.insert(word);
  }

  public void delete(Word word) {
    mRepository.delete(word);
  }

  public void deleteAll() {
    mRepository.deleteAll();
  }
}
