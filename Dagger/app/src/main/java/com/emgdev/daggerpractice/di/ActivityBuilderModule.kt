package com.emgdev.daggerpractice.di

import com.emgdev.daggerpractice.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ApplicationModuleBinds::class])
object ActivityBuilderModule {

  @JvmStatic
  @Provides
  internal fun someString(): String = "this is a test string"
}

@Module
abstract class ApplicationModuleBinds {
  @ContributesAndroidInjector
  internal abstract fun contributeAuthActivity(): AuthActivity
}