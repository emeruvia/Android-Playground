package com.emgdev.daggerpractice.di

import android.app.Application
import com.emgdev.daggerpractice.BaseApplication
import com.emgdev.daggerpractice.SessionManager
import com.emgdev.daggerpractice.di.auth.AuthViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      AndroidSupportInjectionModule::class,
      ActivityBuildersModule::class,
      AppModule::class,
      ViewModelBuilder::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

  val sessionManager: SessionManager

  @Component.Builder
  interface Builder {
    fun build(): AppComponent
    @BindsInstance fun application(application: Application): Builder
  }
}
