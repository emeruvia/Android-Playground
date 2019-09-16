package com.emgdev.daggerpractice.network.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by emeruvia on 9/15/2019.
 */
interface AuthApi {

  @GET
  fun getData(): Call<ResponseBody>

}