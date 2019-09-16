package com.emgdev.daggerpractice.di.auth

import com.emgdev.daggerpractice.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by emeruvia on 9/15/2019.
 */
@Module
object AuthModule {
  @JvmStatic
  @Provides
  fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}