package dev.emg.mvi.api

import androidx.lifecycle.LiveData
import dev.emg.mvi.model.BlogPost
import dev.emg.mvi.model.User
import dev.emg.mvi.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("placeholder/blogs")
  fun getBlogPosts(): LiveData<GenericApiResponse<List<BlogPost>>>

  @GET("placeholder/user/{userId}")
  fun getUser(
    @Path("userId") userId: String
  ): User

}