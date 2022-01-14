package com.example.tecnobank.intro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnobank.intro.repository.OnBoardingRepository
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val repository: OnBoardingRepository) : ViewModel() {

    private val _goToLogin = MutableLiveData<Unit>()
    val goToLogin: LiveData<Unit> = _goToLogin

    fun onClickedLoginStart() {
        viewModelScope.launch {
            repository.saveUserPassOnboarding()
            _goToLogin.postValue(Unit)
        }
    }

}
