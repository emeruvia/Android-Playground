package com.emgdev.daggerpractice.di

import com.emgdev.daggerpractice.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

 /**
 * Place activity declarations that @ContributesAndroidInjector
 */
@Module
abstract class ActivityBuildersModule {
  @ContributesAndroidInjector
  abstract fun contributeAuthActivity(): AuthActivity
}