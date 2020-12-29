package dev.emg.powerfulandroid.di.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.emg.powerfulandroid.ui.auth.AuthViewModel
import dev.emg.powerfulandroid.ui.auth.AuthViewModelFactory
import dev.emg.powerfulandroid.ui.auth.AuthViewModelKey

@Module
abstract class AuthViewModelModule {
  @AuthScope
  @Binds
  abstract fun bindViewModelFactory(factory: AuthViewModelFactory): ViewModelProvider.Factory

  @AuthScope
  @Binds
  @IntoMap
  @AuthViewModelKey(AuthViewModel::class)
  abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel
}