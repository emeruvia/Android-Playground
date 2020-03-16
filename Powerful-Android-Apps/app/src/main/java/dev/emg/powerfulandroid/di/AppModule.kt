package dev.emg.powerfulandroid.di

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dev.emg.powerfulandroid.R
import dev.emg.powerfulandroid.persistance.AccountPropertiesDao
import dev.emg.powerfulandroid.persistance.AppDatabase
import dev.emg.powerfulandroid.persistance.AppDatabase.Companion.DB_NAME
import dev.emg.powerfulandroid.persistance.AuthTokenDao
import javax.inject.Singleton

@Module
object AppModule {
  @JvmStatic
  @Singleton
  @Provides
  fun provideAppDb(app: Application): AppDatabase {
    return Room.databaseBuilder(app, AppDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
  }

  @JvmStatic
  @Singleton
  @Provides
  fun provideAuthTokenDao(db: AppDatabase): AuthTokenDao {
    return db.authTokenDao()
  }

  @JvmStatic
  @Singleton
  @Provides
  fun provideAccountPropertiesDao(db: AppDatabase): AccountPropertiesDao {
    return db.accountProperties()
  }

  @JvmStatic
  @Singleton
  @Provides
  fun provideRequestOptions(): RequestOptions {
    return RequestOptions.placeholderOf(R.drawable.default_image)
        .error(R.drawable.default_image)
  }

  @JvmStatic
  @Singleton
  @Provides
  fun provideGlideInstance(
    application: Application,
    requestOptions: RequestOptions
  ): RequestManager {
    return Glide.with(application)
        .setDefaultRequestOptions(requestOptions)
  }
//  @JvmStatic
//  @Singleton
//  @Provides
//  fun provideSharedPreferences(application: Application) {
//    return application.getSharedPreferences(PreferenceK)
//  }
}