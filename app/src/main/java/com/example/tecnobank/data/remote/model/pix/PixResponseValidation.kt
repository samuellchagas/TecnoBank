package com.example.tecnobank.data.remote.model.pix

import com.google.gson.annotations.SerializedName

data class PixResponseValidation(
    val user: User,
    val pix: String,
    val description: String,
    val organization: String,
    @SerializedName("pix_value") val pixValue: String,
    @SerializedName("pix_token") val pixToken: String,
    val date: String
){
    data class User(
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String
    )
}
