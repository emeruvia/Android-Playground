package dev.emg.powerfulandroid.di.auth

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dev.emg.powerfulandroid.api.auth.OpenApiAuthService
import dev.emg.powerfulandroid.persistance.AccountPropertiesDao
import dev.emg.powerfulandroid.persistance.AuthTokenDao
import dev.emg.powerfulandroid.repository.auth.AuthRepository
import dev.emg.powerfulandroid.session.SessionManager
import retrofit2.Retrofit

@Module
object AuthModule {

  @JvmStatic
  @AuthScope
  @Provides
  fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): OpenApiAuthService {
    return retrofitBuilder.build()
        .create(OpenApiAuthService::class.java)
  }

  @JvmStatic
  @AuthScope
  @Provides
  fun provideAuthRepository(
    sessionManager: SessionManager,
    authTokenDao: AuthTokenDao,
    accountPropertiesDao: AccountPropertiesDao,
    openApiAuthService: OpenApiAuthService,
    preferences: SharedPreferences,
    editor: SharedPreferences.Editor
  ): AuthRepository {
    return AuthRepository(authTokenDao, accountPropertiesDao, openApiAuthService, sessionManager)
  }
}