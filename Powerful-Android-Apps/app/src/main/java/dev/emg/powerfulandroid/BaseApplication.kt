package dev.emg.powerfulandroid

import android.app.Application
import dev.emg.powerfulandroid.di.AppComponent
import dev.emg.powerfulandroid.di.DaggerAppComponent
import dev.emg.powerfulandroid.di.auth.AuthComponent
import dev.emg.powerfulandroid.di.main.MainComponent

class BaseApplication : Application() {

  lateinit var appComponent: AppComponent
  private var authComponent: AuthComponent? = null
  private var mainComponent: MainComponent? = null

  override fun onCreate() {
    super.onCreate()
    initAppComponent()
  }

  fun initAppComponent() {
    appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()
  }

  fun authComponent(): AuthComponent {
    if (authComponent == null) {
      authComponent = appComponent.authComponent()
          .create()
    }
    return authComponent as AuthComponent
  }

  fun mainComponent(): MainComponent {
    if (mainComponent == null) {
      mainComponent = appComponent.mainComponent()
          .create()
    }
    return mainComponent as MainComponent
  }

  fun releaseAuthComponent() {
    authComponent = null
  }

  fun releaseMainComponent() {
    mainComponent = null
  }
}