package dev.emg.powerfulandroid.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.emg.powerfulandroid.di.auth.AuthComponent
import dev.emg.powerfulandroid.di.main.MainComponent
import dev.emg.powerfulandroid.session.SessionManager
import dev.emg.powerfulandroid.ui.BaseActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, SubComponentsModule::class])
interface AppComponent {
  val sessionManager: SessionManager

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(baseActivity: BaseActivity)

  fun authComponent(): AuthComponent.Factory

  fun mainComponent(): MainComponent.Factory
}