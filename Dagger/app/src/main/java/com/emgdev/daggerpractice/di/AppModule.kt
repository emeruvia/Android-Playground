package com.emgdev.daggerpractice.di

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Created by emeruvia on 9/14/2019.
 */
@Module
object AppModule {

  @JvmStatic
  @Provides
  internal fun someString(): String = "this is a test string"

  @JvmStatic
  @Provides
  fun getApp(application: Application): Application {
    return application
  }
}