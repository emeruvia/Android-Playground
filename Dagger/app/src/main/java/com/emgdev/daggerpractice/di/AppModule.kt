package com.emgdev.daggerpractice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.emgdev.daggerpractice.R
import com.emgdev.daggerpractice.util.Constants
import com.emgdev.daggerpractice.util.Constants.Companion
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by emeruvia on 9/14/2019.
 */
@Module
object AppModule {

  @Singleton
  @JvmStatic
  @Provides
  fun provideRetrofitInstance(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(Constants.BASE_URL)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @JvmStatic
  @Provides
  fun provideRequestOptions(): RequestOptions {
    return RequestOptions
      .placeholderOf(R.drawable.white_background)
  }

  @Singleton
  @JvmStatic
  @Provides
  fun provideGlideInstance(
    application: Application,
    requestOptions: RequestOptions
  ): RequestManager {
    return Glide
      .with(application)
      .setDefaultRequestOptions(requestOptions)
  }

  @Singleton
  @JvmStatic
  @Provides
  fun provideAppDrawable(application: Application): Drawable {
    return ContextCompat.getDrawable(application, R.drawable.logo)!!
  }

}