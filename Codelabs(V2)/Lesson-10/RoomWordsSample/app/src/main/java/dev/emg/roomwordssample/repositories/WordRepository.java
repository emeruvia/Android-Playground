package dev.emg.roomwordssample.repositories;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import dev.emg.roomwordssample.model.Word;
import dev.emg.roomwordssample.persistence.WordDao;
import dev.emg.roomwordssample.persistence.WordRoomDatabase;
import java.util.List;

public class WordRepository {

  private WordDao mWordDao;
  private LiveData<List<Word>> mAllWords;

  public WordRepository(Application application) {
    WordRoomDatabase db = WordRoomDatabase.getInstance(application);
    mWordDao = db.wordDao();
    mAllWords = mWordDao.getAllWords();
  }

  public LiveData<List<Word>> getAllWords() {
    return mAllWords;
  }

  public void insert(Word word) {
    new insertAsyncTask(mWordDao).execute(word);
  }

  public void delete(Word word) {
    new deleteWordAsyncTask(mWordDao).execute(word);
  }

  public void deleteAll() {
    new deleteAllWordsAsyncTask(mWordDao).execute();
  }

  private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

    private WordDao mAsyncTaskDao;

    insertAsyncTask(WordDao wordDao) {
      this.mAsyncTaskDao = wordDao;
    }

    @Override protected Void doInBackground(Word... words) {
      mAsyncTaskDao.insert(words[0]);
      return null;
    }
  }

  private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
    private WordDao mAsyncTaskDao;

    deleteWordAsyncTask(WordDao dao) {
      this.mAsyncTaskDao = dao;
    }

    @Override protected Void doInBackground(Word... words) {
      mAsyncTaskDao.delete(words[0]);
      return null;
    }
  }

  private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
    private WordDao mAsyncTaskDao;

    deleteAllWordsAsyncTask(WordDao dao) {
      this.mAsyncTaskDao = dao;
    }

    @Override protected Void doInBackground(Void... voids) {
      mAsyncTaskDao.deleteAll();
      return null;
    }
  }
}
