package com.example.tecnobank.home.repository

import com.example.tecnobank.data.local.SharedPreferenceServices

class ProfileRepository(
    private val sharedPreferenceServices: SharedPreferenceServices
) {

    fun getSaveUserName():String? = sharedPreferenceServices
        .getSaveUserName()

    fun getSaveUserEmail():String = sharedPreferenceServices
        .getUserEmail()

}
