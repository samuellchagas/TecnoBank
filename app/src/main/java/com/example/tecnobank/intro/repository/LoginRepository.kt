package com.example.tecnobank.intro.repository

import com.example.tecnobank.data.local.SharedPreferenceServices
import com.example.tecnobank.data.remote.EndPoint
import com.example.tecnobank.data.remote.model.login.LoginPayload
import com.example.tecnobank.data.remote.model.login.LoginResponse

class LoginRepository(
    private val endPoint: EndPoint,
    private val sharedPreferenceServices: SharedPreferenceServices
) {

    suspend fun login(email: String, password: String): LoginResponse {

        val response = endPoint.login(LoginPayload(email, password))
        if (response.isSuccessful) {
            return response.body()!!
        } else if (response.code() == 401) {
            throw Exception("E-mail ou senha inválida.")
        } else if (response.code() == 404) {
            throw Exception("E-mail não encontrado")
        } else {
            throw Exception("Falha no sistema.")
        }

    }

    fun saveUserLogin(email: String, password: String) =
        sharedPreferenceServices.saveUserLogin(email, password)

    fun deleteUserLogin() = sharedPreferenceServices.deleteUserLogin()

    fun getUserEmail(): String = sharedPreferenceServices.getUserEmail()

    fun getUserPassword(): String = sharedPreferenceServices.getUserPassword()

    fun saveTokenAuthentication(tokenAuthentication: String) = sharedPreferenceServices
        .saveTokenAuthentication(tokenAuthentication)

    fun saveUserName(firstName: String, lastName: String) = sharedPreferenceServices
        .saveUserName(firstName, lastName)
}
