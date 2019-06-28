package dev.emg.roomwordssample.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import dev.emg.roomwordssample.model.Word;
import java.util.List;

@Dao
public interface WordDao {

  @Query("SELECT * FROM word_table ORDER BY word ASC")
  LiveData<List<Word>> getAllWords();

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(Word word);

  @Query("DELETE FROM word_table")
  void deleteAll();
}
