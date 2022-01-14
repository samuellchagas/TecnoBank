package com.example.tecnobank.home.repository

import com.example.tecnobank.data.local.SharedPreferenceServices

class PixRepository(
    private val sharedPreferenceServices: SharedPreferenceServices
) {

    fun saveUserPassPixOnboarding() = sharedPreferenceServices.saveUserPassPixOnboarding()

}
