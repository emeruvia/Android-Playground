package com.emgdev.daggerpractice.di

import com.emgdev.daggerpractice.auth.AuthActivity
import com.emgdev.daggerpractice.di.auth.AuthModule
import com.emgdev.daggerpractice.di.auth.AuthViewModelModule
import com.emgdev.daggerpractice.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Place activity declarations that @ContributesAndroidInjector
 */
@Module
abstract class ActivityBuildersModule {
  @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
  abstract fun contributeAuthActivity(): AuthActivity

  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity
}
