package dev.emg.powerfulandroid.di.main

import dagger.Subcomponent
import dev.emg.powerfulandroid.session.SessionManager
import dev.emg.powerfulandroid.ui.main.MainActivity

@MainScope
@Subcomponent(modules = [MainModule::class, MainViewModelModule::class, MainFragmentsModule::class])
interface MainComponent {

  @Subcomponent.Factory
  interface Factory {
    fun create(): MainComponent
  }

  fun inject(mainActivity: MainActivity)
}