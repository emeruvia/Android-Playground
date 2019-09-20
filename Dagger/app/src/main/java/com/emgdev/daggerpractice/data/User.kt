package com.emgdev.daggerpractice.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by emeruvia on 9/18/2019.
 */
data class User(
  @SerializedName("id")
  @Expose var
  id: Int? = null,

  @SerializedName("username")
  @Expose
  val username: String? = null,

  @SerializedName("email")
  @Expose
  val email: String? = null,

  @SerializedName("website")
  @Expose
  val website: String? = null
)
