package ru.cpt.fsp.data.api

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenHolder @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    fun saveAccessToken(token: String?) {
        sharedPreferences.edit {
            putString(ACCESS_TOKEN_KEY, token)
        }
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    fun saveRefreshToken(token: String?) {
        sharedPreferences.edit {
            putString(ACCESS_TOKEN_KEY, token)
        }
    }

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_key"
        private const val REFRESH_TOKEN_KEY = "refresh_token_key"
    }
}