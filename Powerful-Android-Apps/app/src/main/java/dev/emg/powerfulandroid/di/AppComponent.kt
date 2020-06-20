package dev.emg.powerfulandroid.di

//import dev.emg.powerfulandroid.ui.BaseActivity
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.emg.powerfulandroid.di.auth.AuthComponent
import dev.emg.powerfulandroid.di.main.MainComponent
import dev.emg.powerfulandroid.session.SessionManager
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, SubComponentsModule::class])
interface AppComponent {

  val sessionManager: SessionManager

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance application: Application): AppComponent
  }

//  @Component.Builder
//  interface Builder {
//    @BindsInstance
//    fun application(application: Application): Builder
//
//    fun build(): AppComponent
//  }

//  fun inject(baseActivity: BaseActivity)

  fun authComponent(): AuthComponent.Factory
  fun mainComponent(): MainComponent.Factory
}