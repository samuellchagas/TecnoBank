package com.example.tecnobank.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val user: User,
    @SerializedName("token") val tokenAuthentication: String
) {
    data class User(
        val lastName: String,
        val firstName: String
    )

}
