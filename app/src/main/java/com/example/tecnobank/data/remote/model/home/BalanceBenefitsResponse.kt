package com.example.tecnobank.data.remote.model.home

import com.google.gson.annotations.SerializedName

data class BalanceBenefitsResponse(
    val balance: Balance,
    val benefits: List<Benefits>
) {
    data class Balance(
        @SerializedName("current_value") val currentValue: String,
        val receivables: String
    )

    data class Benefits(
        @SerializedName("indicator_color") val indicatorColor: String,
        val image: String,
        val title: String,
        val message: String,
        @SerializedName("text_link") val textLink: String
    )

}
