package com.example.tecnobank.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnobank.data.remote.model.pix.PixItemsRequest
import com.example.tecnobank.data.remote.model.pix.PixResponseConfirmation
import com.example.tecnobank.data.remote.model.pix.PixResponseValidation
import com.example.tecnobank.home.repository.PixConfirmationRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PixConfirmationViewModel(
    private val pixConfirmationRepository: PixConfirmationRepository,
    private val args: PixItemsRequest?
): ViewModel() {

    private var pixType: String = args!!.type
    private var pixEmail: String = args!!.email
    private var pixDescription: String = args!!.description
    private var pixValue = args!!.value
    private var pixDate: String = SimpleDateFormat("dd/MM/yyyy")
        .format(Calendar.getInstance().time)
    private lateinit var responseValidation: PixResponseValidation
    private lateinit var responseConfirm: PixResponseConfirmation

    private val _pixValidationSucess = MutableLiveData<PixResponseValidation>()
    val pixValidationSucess: LiveData<PixResponseValidation> = _pixValidationSucess

    private val _pixValidationError = MutableLiveData<String>()
    val pixValidationError: LiveData<String> = _pixValidationError

    private val _pixConfirmationSucess = MutableLiveData<PixResponseConfirmation>()
    val pixConfirmationSucess: LiveData<PixResponseConfirmation> = _pixConfirmationSucess

    private val _pixConfirmationError = MutableLiveData<String>()
    val pixConfirmationError: LiveData<String> = _pixConfirmationError

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _validDatePix = MutableLiveData<String>()
    val validDatePix: LiveData<String> = _validDatePix

    private val _confirmationButtonEnabled = MutableLiveData<Boolean>()
    val confirmationButtonEnabled: LiveData<Boolean> = _confirmationButtonEnabled

    fun validationDatePix(calendar: Calendar){
        pixDate = SimpleDateFormat("dd/MM/yyyy").format(calendar.time)
        requestValidationPix()
    }

    fun requestValidationPix(){
        viewModelScope.launch {
            _loading.postValue(true)
            _confirmationButtonEnabled.postValue(false)
            try{
                responseValidation = pixConfirmationRepository.pixValidation(
                    PixItemsRequest(
                        pixEmail,
                        pixType,
                        pixDescription,
                        pixValue,
                        pixDate
                    )
                )
                _pixValidationSucess.postValue(responseValidation)
                _validDatePix.postValue(pixDate)
                _confirmationButtonEnabled.postValue(true)
            }catch (e:Exception){
                _pixValidationError.postValue(e.message)
                _confirmationButtonEnabled.postValue(false)
            }
            _loading.postValue(false)
        }
    }

    fun onClickConfirmationPix() {
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                responseConfirm =
                    pixConfirmationRepository.pixConfirmation(responseValidation.pixToken)
                _pixConfirmationSucess.postValue(responseConfirm)
            } catch (e: Exception) {
                _pixConfirmationError.postValue(e.message)
            }
            _loading.postValue(false)
        }
    }

}
