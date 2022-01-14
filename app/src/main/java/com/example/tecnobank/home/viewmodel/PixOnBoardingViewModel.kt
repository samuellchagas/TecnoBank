package com.example.tecnobank.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tecnobank.home.repository.PixRepository

class PixOnBoardingViewModel(private val pixRepository: PixRepository): ViewModel() {

    private val _goToPix = MutableLiveData<Unit>()
    val goToPix: LiveData<Unit> = _goToPix

    fun onClickStartPix(){
        pixRepository.saveUserPassPixOnboarding()
        _goToPix.postValue(Unit)
    }
}
