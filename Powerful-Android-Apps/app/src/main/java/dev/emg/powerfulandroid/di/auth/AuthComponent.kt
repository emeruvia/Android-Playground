package dev.emg.powerfulandroid.di.auth

import dagger.Subcomponent
import dev.emg.powerfulandroid.ui.auth.AuthActivity

@AuthScope
@Subcomponent(modules = [AuthModule::class, AuthViewModelModule::class, AuthFragmentsModule::class])
interface AuthComponent {
  @Subcomponent.Factory
  interface Factory {
    fun create(): AuthComponent
  }

  fun inject(authActivity: AuthActivity)
}