package com.example.tecnobank.extract.repository

import com.example.tecnobank.data.local.SharedPreferenceServices
import com.example.tecnobank.data.local.database.ExtractDAO
import com.example.tecnobank.data.remote.EndPoint
import com.example.tecnobank.data.remote.model.extract.ExtractResponse
import com.example.tecnobank.data.remote.model.pix.ExtractEntity

class ExtractRepository(
    private val endPoint: EndPoint,
    private val extractDAO: ExtractDAO,
    private val sharedPreferenceServices: SharedPreferenceServices
) {

    suspend fun extractTransactions(
        dataFilterStart: String,
        dataFilterEnd: String,
        default: Int
    ): List<ExtractResponse> {

        val response = endPoint.extractTransactions(
            dataFilterStart, dataFilterEnd
        )

        if (response.isSuccessful) {
            if (default == 7) {
                //extractDAO.deleteCache()
                //saveResponseInDatabase(response.body()!!)
            }
            return response.body()!!
        } else if (response.code() == 304) {
            //return extractDAO.returnCache()
            throw Exception("Codigo 304")
        } else {
            throw Exception("Erro no sistema.")
        }

    }

    fun saveResponseInDatabase(listExtracts: List<ExtractResponse>) {
        listExtracts.forEach {
            extractDAO.saveCache(
                ExtractEntity(
                    it.status,
                    it.time,
                    it.type,
                    it.typeDescription,
                    it.value,
                    it.date
                )
            )
        }
    }

    fun saveItemFilterSelected(positionFilter: Int) =
        sharedPreferenceServices.saveItemFilterSelected(positionFilter)

    fun getSaveItemFilterSelected(): Int = sharedPreferenceServices.getSaveItemFilterSelected()
}
