package com.emgdev.daggerpractice.di

import com.emgdev.daggerpractice.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

  @ContributesAndroidInjector
  abstract fun contributeAuthActivity(): AuthActivity
}