package com.emgdev.daggerpractice.di

import android.app.Application
import com.emgdev.daggerpractice.BaseApplication
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
      ViewModelBuilder::class,
      AuthModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

  @Component.Builder
  interface Builder {
    fun build(): AppComponent
    @BindsInstance fun application(application: Application): Builder
  }
}
