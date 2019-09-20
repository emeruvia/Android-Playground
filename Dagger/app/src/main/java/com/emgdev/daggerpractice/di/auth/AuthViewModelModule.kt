package com.emgdev.daggerpractice.di.auth

import androidx.lifecycle.ViewModel
import com.emgdev.daggerpractice.auth.AuthViewModel
import com.emgdev.daggerpractice.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by emeruvia on 9/14/2019.
 */

@Module
abstract class AuthViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(AuthViewModel::class)
  abstract fun bindViewModel(viewModel: AuthViewModel): ViewModel
}
