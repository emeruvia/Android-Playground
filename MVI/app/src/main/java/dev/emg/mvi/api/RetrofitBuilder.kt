package dev.emg.mvi.api

import dev.emg.mvi.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// objects in kotlin are singletons
object RetrofitBuilder {

  const val BASE_URL = "https://open-api.xyz/"

  // by lazy only initalizes the instance once, after that it will keep reusing the same one
  val retrofitBuilder: Retrofit.Builder by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
  }

  val apiService: ApiService by lazy {
    retrofitBuilder
        .build()
        .create(ApiService::class.java)
  }
}