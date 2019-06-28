package dev.emg.roomwordssample.persistence;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import dev.emg.roomwordssample.model.Word;

@Database(entities = { Word.class }, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

  public abstract WordDao wordDao();

  private static WordRoomDatabase INSTANCE;

  public static WordRoomDatabase getInstance(final Context context) {
    if (INSTANCE == null) {
      synchronized (WordRoomDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              WordRoomDatabase.class, "word_database")
              .fallbackToDestructiveMigration()
              .addCallback(sRoomDatabaseCallback)
              .build();
        }
      }
    }
    return INSTANCE;
  }

  private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
    @Override public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
      new PopulateDBAsync(INSTANCE).execute();
    }
  };

  private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

    private final WordDao mDao;
    String[] words = { "dolphin", "crocodile", "cobra" };

    PopulateDBAsync(WordRoomDatabase db) {
      mDao = db.wordDao();
    }

    @Override protected Void doInBackground(Void... voids) {
      if (mDao.getAnyWord().length < 1) {
        for (int i = 0; i < words.length; i++) {
          Word word = new Word(words[i]);
          mDao.insert(word);
        }
      }
      return null;
    }
  }
}
