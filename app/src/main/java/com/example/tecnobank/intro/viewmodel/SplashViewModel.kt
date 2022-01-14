package com.example.tecnobank.intro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tecnobank.intro.repository.SplashRepository

class SplashViewModel(private val repository: SplashRepository):ViewModel() {

    private val _splashToOnBoarding = MutableLiveData<Unit>()
    val splashToOnBoarding: LiveData<Unit> = _splashToOnBoarding
    private val _splashToLogin = MutableLiveData<Unit>()
    val splashToLogin: LiveData<Unit> = _splashToLogin

    fun initSplash() {
        if (repository.passedByTheOnBoarding()) {
            _splashToLogin.postValue(Unit)
        } else {
            _splashToOnBoarding.postValue(Unit)
        }
    }

}
