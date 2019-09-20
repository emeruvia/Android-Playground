package com.emgdev.daggerpractice.auth

import com.emgdev.daggerpractice.auth.AuthStatus.AUTHENTICATED
import com.emgdev.daggerpractice.auth.AuthStatus.ERROR
import com.emgdev.daggerpractice.auth.AuthStatus.LOADING
import com.emgdev.daggerpractice.auth.AuthStatus.NOT_AUTHENTICATED

/**
 * Created by emeruvia on 9/19/2019.
 */
data class AuthResource<out T>(
  val status: AuthStatus,
  val data: T?,
  val message: String?
) {

  companion object {
    fun <T> authenticated(data: T): AuthResource<T> {
      return AuthResource(AUTHENTICATED, data, null)
    }

    fun <T> error(msg: String, data: T?): AuthResource<T> {
      return AuthResource(ERROR, data, msg)
    }

    fun <T> loading(data: T?): AuthResource<T> {
      return AuthResource(LOADING, data, null)
    }

    fun <T> logout(): AuthResource<T> {
      return AuthResource(NOT_AUTHENTICATED, null, null)
    }
  }
}

enum class AuthStatus {
  AUTHENTICATED,
  ERROR,
  LOADING,
  NOT_AUTHENTICATED
}