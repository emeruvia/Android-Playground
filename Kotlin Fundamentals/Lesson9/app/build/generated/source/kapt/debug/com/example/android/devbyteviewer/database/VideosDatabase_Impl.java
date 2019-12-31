package com.example.android.devbyteviewer.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class VideosDatabase_Impl extends VideosDatabase {
  private volatile VideoDao _videoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DatabaseVideo` (`url` TEXT NOT NULL, `updated` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `thumbnail` TEXT NOT NULL, PRIMARY KEY(`url`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"476a1b31bec62073ae29ab92ddd8c406\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `DatabaseVideo`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDatabaseVideo = new HashMap<String, TableInfo.Column>(5);
        _columnsDatabaseVideo.put("url", new TableInfo.Column("url", "TEXT", true, 1));
        _columnsDatabaseVideo.put("updated", new TableInfo.Column("updated", "TEXT", true, 0));
        _columnsDatabaseVideo.put("title", new TableInfo.Column("title", "TEXT", true, 0));
        _columnsDatabaseVideo.put("description", new TableInfo.Column("description", "TEXT", true, 0));
        _columnsDatabaseVideo.put("thumbnail", new TableInfo.Column("thumbnail", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDatabaseVideo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDatabaseVideo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDatabaseVideo = new TableInfo("DatabaseVideo", _columnsDatabaseVideo, _foreignKeysDatabaseVideo, _indicesDatabaseVideo);
        final TableInfo _existingDatabaseVideo = TableInfo.read(_db, "DatabaseVideo");
        if (! _infoDatabaseVideo.equals(_existingDatabaseVideo)) {
          throw new IllegalStateException("Migration didn't properly handle DatabaseVideo(com.example.android.devbyteviewer.database.DatabaseVideo).\n"
                  + " Expected:\n" + _infoDatabaseVideo + "\n"
                  + " Found:\n" + _existingDatabaseVideo);
        }
      }
    }, "476a1b31bec62073ae29ab92ddd8c406", "daf12d7f37ddf91e77e6f02901c6c946");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "DatabaseVideo");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `DatabaseVideo`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public VideoDao getVideoDao() {
    if (_videoDao != null) {
      return _videoDao;
    } else {
      synchronized(this) {
        if(_videoDao == null) {
          _videoDao = new VideoDao_Impl(this);
        }
        return _videoDao;
      }
    }
  }
}
