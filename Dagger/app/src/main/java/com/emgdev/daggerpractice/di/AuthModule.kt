package com.emgdev.daggerpractice.di

import androidx.lifecycle.ViewModel
import com.emgdev.daggerpractice.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by emeruvia on 9/14/2019.
 */

@Module
abstract class AuthModule {

  @Binds
  @IntoMap
  @ViewModelKey(AuthViewModel::class)
  abstract fun bindViewModel(viewModel: AuthViewModel): ViewModel
}
