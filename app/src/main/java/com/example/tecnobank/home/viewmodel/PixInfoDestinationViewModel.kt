package com.example.tecnobank.home.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tecnobank.data.remote.model.pix.SingleLiveEvent

class PixInfoDestinationViewModel: ViewModel() {

    private lateinit var pixEmail: String

    private val _goToDescriptionPix = SingleLiveEvent<String>()
    val goToDescriptionPix: LiveData<String> = _goToDescriptionPix

    private val _confirmationButtonEnabled = MutableLiveData<Boolean>()
    val confirmationButtonEnabled: LiveData<Boolean> = _confirmationButtonEnabled

    private val _emailErro = MutableLiveData<String>()
    val emailErro: LiveData<String> = _emailErro

    fun changeDestinationEmailPix(email: String){
        pixEmail = email
        changeButtonColor()
    }

    private fun changeButtonColor(){
        _confirmationButtonEnabled.postValue(!(pixEmail.isNullOrEmpty()))
    }

    fun onClickApplyInfoDestinationPix(){
        if(isValidEmail()){
            _goToDescriptionPix.postValue(pixEmail)
        }else{
            _emailErro.postValue("O email digitado é invalido")
        }
    }

    private fun isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(pixEmail).matches()
}
