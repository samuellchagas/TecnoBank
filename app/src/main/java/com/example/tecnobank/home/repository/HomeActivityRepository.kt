package com.example.tecnobank.home.repository

import com.example.tecnobank.data.remote.EndPoint
import com.example.tecnobank.data.remote.model.home.TokenFirebase

class HomeActivityRepository(
    private val endPoint: EndPoint
) {

    suspend fun sendToken(tokenFirebase: TokenFirebase){
        endPoint.sendToken(tokenFirebase)
    }
}