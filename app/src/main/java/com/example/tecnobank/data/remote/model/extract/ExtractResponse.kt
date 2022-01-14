package com.example.tecnobank.data.remote.model.extract

import com.google.gson.annotations.SerializedName

data class ExtractResponse(
    val status: String,
    val time: String,
    val type: String,
    @SerializedName("type_description") val typeDescription: String,
    val value: String,
    val date: String
)
