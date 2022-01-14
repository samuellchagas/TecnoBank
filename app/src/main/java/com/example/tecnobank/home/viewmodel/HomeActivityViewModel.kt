package com.example.tecnobank.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnobank.data.remote.model.home.TokenFirebase
import com.example.tecnobank.home.repository.HomeActivityRepository
import kotlinx.coroutines.launch

class HomeActivityViewModel(
    private val homeActivityRepository: HomeActivityRepository
) : ViewModel() {

    fun sendToken(tokenFirebase: TokenFirebase) {
        viewModelScope.launch {
            homeActivityRepository.sendToken(tokenFirebase)
        }
    }

}