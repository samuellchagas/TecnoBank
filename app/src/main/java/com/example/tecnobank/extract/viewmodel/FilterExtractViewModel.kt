package com.example.tecnobank.extract.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tecnobank.extract.repository.ExtractRepository

class FilterExtractViewModel(private val extractRepository: ExtractRepository) :ViewModel() {

    private val _itemFilterPosition = MutableLiveData<Int>()
    val itemFilterPosition: LiveData<Int> = _itemFilterPosition

    fun saveItemFilterSelected(positionFilter: Int) =
        extractRepository.saveItemFilterSelected(positionFilter)

    fun getSaveItemFilterSelected() {
        _itemFilterPosition.postValue(extractRepository.getSaveItemFilterSelected())
    }

}