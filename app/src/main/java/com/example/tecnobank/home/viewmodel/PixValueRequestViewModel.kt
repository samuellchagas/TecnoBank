package com.example.tecnobank.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnobank.data.remote.model.pix.SingleLiveEvent
import com.example.tecnobank.extension.HelperFunctions.converterToReal
import com.example.tecnobank.home.repository.HomeRepository
import kotlinx.coroutines.launch

class PixValueRequestViewModel(private val homeRepository:HomeRepository): ViewModel() {

    private var pixValue: String = "0.00"
    private lateinit var balanceValueCurrent: String

    private val _goToConfirmationPix = SingleLiveEvent<String>()
    val goToConfirmationPix: LiveData<String> = _goToConfirmationPix

    private val _balanceValue = MutableLiveData<String>()
    val balanceValue: LiveData<String> = _balanceValue

    private val _confirmationButtonEnabled = MutableLiveData<Boolean>()
    val confirmationButtonEnabled: LiveData<Boolean> = _confirmationButtonEnabled

    private val _balanceVisible = MutableLiveData<Boolean>()
    val balanceVisible: LiveData<Boolean> = _balanceVisible

    private val _invalidValueError = SingleLiveEvent<String>()
    val invalidValueError: SingleLiveEvent<String> = _invalidValueError

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _responseErro = MutableLiveData<String>()
    val responseErro: LiveData<String> = _responseErro

    fun changeValuePix(value: String){
        pixValue = parseRealForString(value)
        changeButtonColor()
    }

    private fun changeButtonColor(){
        _confirmationButtonEnabled.postValue(pixValue.isNotEmpty())
    }

    fun onClickApplyValuePix(){
        if (pixValue.toDouble() > 0 && pixValue.toDouble() <= balanceValueCurrent.toDouble()){
            _goToConfirmationPix.postValue(pixValue)
        } else {
            _invalidValueError.postValue("Valor inválido!")
        }
    }

    private fun parseRealForString(real: String):String = real
        .replace(".", "")
        .replace(",", ".")
        .substring(3)

    fun getSaveBalanceValue(){
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                balanceValueCurrent = homeRepository.BalancesAndBenefits().balance.currentValue
                _balanceValue.postValue(converterToReal(balanceValueCurrent.toDouble()))
            } catch (e: Exception) {
                _responseErro.postValue(e.message)
            }
            _loading.postValue(false)
        }
    }

    fun onOcultBalanceClicked(){
        _balanceVisible.postValue(!(balanceVisible.value == true))
    }
}
