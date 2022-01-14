package com.example.tecnobank.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tecnobank.home.repository.ProfileRepository

class ProfileViewModel(private val profileRepository: ProfileRepository):ViewModel() {

    fun getSaveUserName(): String? = profileRepository.getSaveUserName()

    fun getUserEmail():String? = profileRepository.getSaveUserEmail()

}
