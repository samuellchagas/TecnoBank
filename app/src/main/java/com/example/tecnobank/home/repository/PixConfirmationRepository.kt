package com.example.tecnobank.home.repository

import com.example.tecnobank.data.local.SharedPreferenceServices
import com.example.tecnobank.data.remote.EndPoint
import com.example.tecnobank.data.remote.model.pix.PixItemsRequest
import com.example.tecnobank.data.remote.model.pix.PixResponseConfirmation
import com.example.tecnobank.data.remote.model.pix.PixResponseValidation

class PixConfirmationRepository(
    private val endPoint: EndPoint
) {

    suspend fun pixValidation(pixItensRequest: PixItemsRequest): PixResponseValidation {

        val response = endPoint.pixValidation(
            pixItensRequest
        )

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Erro no sistema.")
        }
    }

    suspend fun pixConfirmation(pixToken: String): PixResponseConfirmation{
        val response = endPoint.pixConfirmation(
            pixToken
        )

        if (response.isSuccessful) {
            return response.body()!!

        } else {
            throw Exception("Erro no sistema.")
        }
    }

}
