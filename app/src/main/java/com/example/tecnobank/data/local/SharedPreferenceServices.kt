package com.example.tecnobank.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferenceServices(private val preferences: SharedPreferences) {

    fun passedByTheOnBoarding(): Boolean = preferences.getBoolean(PASS_TO_ONBOARDING, false)

    fun saveUserPassOnboarding() {
        preferences.edit {
            putBoolean(PASS_TO_ONBOARDING, true)
        }
    }

    fun saveUserLogin(email: String, password: String) {
        preferences.edit {
            putString(USER, email)
            putString(PASSWORD, password)
        }
    }

    fun deleteUserLogin() {
        preferences.edit {
            putString(USER, "")
            putString(PASSWORD, "")
        }
    }

    fun getUserEmail(): String = preferences.getString(USER, "")!!

    fun getUserPassword(): String = preferences.getString(PASSWORD, "")!!

    fun saveTokenAuthentication(tokenAuthentication: String) {
        preferences.edit {
            putString(TOKEN_AUTHENTICATION, tokenAuthentication)
        }
    }

    fun getSaveTokenAuthentication(): String = preferences.getString(TOKEN_AUTHENTICATION, "")!!

    fun passedByPixOnBoarding(): Boolean = preferences.getBoolean(PASS_TO_PIX_ONBOARDING, false)

    fun saveUserPassPixOnboarding() {
        preferences.edit {
            putBoolean(PASS_TO_PIX_ONBOARDING, true)
        }
    }

    fun saveUserName(firstName: String, lastName: String) {
        preferences.edit {
            putString(USERNAME, "$firstName $lastName")
        }
    }

    fun getSaveUserName(): String? = preferences.getString(USERNAME, "")


    fun saveItemFilterSelected(positionFilter: Int) {
        preferences.edit {
            putInt(POSITION_FILTER, (positionFilter))
        }
    }

    fun getSaveItemFilterSelected(): Int = preferences.getInt(POSITION_FILTER, 1)

    companion object {
        private const val PASS_TO_ONBOARDING = "Passou"
        private const val USER = "Username"
        private const val PASSWORD = "Password"
        private const val TOKEN_AUTHENTICATION = "TokenAuthentication"
        private const val PASS_TO_PIX_ONBOARDING = "pass_to_pix_on_bording"
        private const val USERNAME = "UserName"
        private const val POSITION_FILTER = "PositionFilter"
    }
}
