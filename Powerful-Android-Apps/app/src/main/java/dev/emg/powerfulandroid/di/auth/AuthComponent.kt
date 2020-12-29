package dev.emg.powerfulandroid.di.auth

import dagger.Subcomponent
import dev.emg.powerfulandroid.ui.auth.AuthActivity
import dev.emg.powerfulandroid.ui.auth.LauncherFragment
import dev.emg.powerfulandroid.ui.auth.LoginFragment
import dev.emg.powerfulandroid.ui.auth.RegisterFragment

@AuthScope
@Subcomponent(modules = [AuthModule::class, AuthViewModelModule::class, AuthFragmentsModule::class])
interface AuthComponent {
  @Subcomponent.Factory
  interface Factory {
    fun create(): AuthComponent
  }

  fun inject(authActivity: AuthActivity)
  fun inject(fragment: LauncherFragment)
  fun inject(fragment: LoginFragment)
  fun inject(fragment: RegisterFragment)
}