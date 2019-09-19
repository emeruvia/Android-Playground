package com.emgdev.daggerpractice.network.auth

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by emeruvia on 9/15/2019.
 */
interface AuthApi {

  @GET("users/{id}")
  fun getUser(
    @Path("id") id: Int
  ): Flowable<User>

}