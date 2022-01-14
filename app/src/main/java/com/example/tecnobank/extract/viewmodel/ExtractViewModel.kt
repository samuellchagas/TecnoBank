package com.example.tecnobank.extract.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tecnobank.R
import com.example.tecnobank.data.remote.model.extract.ExtractResponse
import com.example.tecnobank.extension.HelperFunctions.formatDate
import com.example.tecnobank.extension.HelperFunctions.getDateMonthFormat
import com.example.tecnobank.extract.recyclerview.ListExtractsAdapter.Companion.CANCELED
import com.example.tecnobank.extract.recyclerview.ListExtractsAdapter.Companion.EXPENSE
import com.example.tecnobank.extract.repository.ExtractRepository
import kotlinx.coroutines.launch
import java.util.*

class ExtractViewModel(private val extractRepository: ExtractRepository) : ViewModel() {

    private lateinit var dateFilterStart: String
    private lateinit var dateFilterEnd: String
    private var filterPosition: Int = 1
    private var listfilterDays: List<Int> = listOf(3, 7, 30, 60, 120)
    private lateinit var receivedListApi: List<ExtractResponse>
    private val listItemFilter: List<String> =
        listOf(
            "Últimos 3 dias",
            "Últimos 7 dias",
            "Últimos 30 dias",
            "Últimos 60 dias",
            "Últimos 120 dias",
        )

    private val _responseErro = MutableLiveData<String>()
    val responseErro: LiveData<String> = _responseErro

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _extractList = MutableLiveData<List<ExtractItemAdapter>>()
    val extractList: LiveData<List<ExtractItemAdapter>> = _extractList

    private val _dataFilter = MutableLiveData<String>()
    val dataFilter: LiveData<String> = _dataFilter

    fun onChangeDataFilter(filterText: String, filterPosition: Int) {
        this.filterPosition = filterPosition
        requestExtracts()
        _dataFilter.postValue("nos $filterText")
    }

    private fun requestDatesStartAndEnd() {
        this.dateFilterStart = formatDate(Calendar.getInstance()
            .apply { add(Calendar.DAY_OF_MONTH, -listfilterDays[filterPosition]) }.time
        )
        this.dateFilterEnd = formatDate(Calendar.getInstance().time)
    }

    fun requestExtracts() {
        viewModelScope.launch {
            try {
                requestDatesStartAndEnd()
                _loading.postValue(true)
                receivedListApi = extractRepository.extractTransactions(
                    dateFilterStart,
                    dateFilterEnd,
                    listfilterDays[filterPosition]
                )
                _extractList.postValue(mapItemsForAdapter(receivedListApi))
            } catch (e: Exception) {
                _responseErro.postValue(e.message)
            }
            _loading.postValue(false)
        }
    }

    private fun mapItemsForAdapter(extractList: List<ExtractResponse>): List<ExtractItemAdapter> {
        val formattedList: MutableList<ExtractItemAdapter> = mutableListOf()

        for (i in 0 until extractList.size) {
            if ((i == 0) || (extractList[i].date != extractList[i - 1].date)) {
                formattedList.add(ExtractItemHeader(getDateMonthFormat(extractList[i].date)))
            }
            formattedList.add(ExtractItemBody(extractList[i]))
        }

        return formattedList
    }

    fun buttonPressedEvery() {
        _extractList.postValue(mapItemsForAdapter(receivedListApi))
    }

    fun buttonPressedInputs() {
        _extractList.postValue(mapItemsForAdapter(receivedListApi.filter{it.type!= EXPENSE &&
                !it.time.contains(CANCELED)}.toMutableList()))
    }

    fun buttonPressedExit() {
        _extractList.postValue(mapItemsForAdapter(receivedListApi.filter{it.type == EXPENSE &&
                !it.time.contains(CANCELED)}.toMutableList()))
    }

    fun onChangeSaveDataFilter() {
        _dataFilter
            .postValue("nos ${listItemFilter[extractRepository.getSaveItemFilterSelected()].toLowerCase()}")
    }

    open class ExtractItemAdapter

    data class ExtractItemHeader(val date: String) : ExtractItemAdapter()

    data class ExtractItemBody(val body: ExtractResponse) : ExtractItemAdapter()

}
