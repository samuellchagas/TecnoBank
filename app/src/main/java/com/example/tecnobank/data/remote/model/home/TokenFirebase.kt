package com.example.tecnobank.data.remote.model.home

import com.google.gson.annotations.SerializedName

data class TokenFirebase(
    @SerializedName("fcm_token") val tokenFirebase: String
)