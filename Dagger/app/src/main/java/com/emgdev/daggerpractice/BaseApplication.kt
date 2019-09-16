package com.emgdev.daggerpractice

import com.emgdev.daggerpractice.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class BaseApplication : DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
        .application(this)
        .build()
  }

  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())
  }

}