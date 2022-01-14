package com.example.tecnobank.data.remote

import com.example.tecnobank.data.local.SharedPreferenceServices
import com.example.tecnobank.data.remote.model.extract.ExtractResponse
import com.example.tecnobank.data.remote.model.home.BalanceBenefitsResponse
import com.example.tecnobank.data.remote.model.home.TokenFirebase
import com.example.tecnobank.data.remote.model.login.LoginPayload
import com.example.tecnobank.data.remote.model.login.LoginResponse
import com.example.tecnobank.data.remote.model.pix.PixItemsRequest
import com.example.tecnobank.data.remote.model.pix.PixResponseConfirmation
import com.example.tecnobank.data.remote.model.pix.PixResponseValidation
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface EndPoint {

    @POST("login")
    suspend fun login(@Body loginPayload: LoginPayload): Response<LoginResponse>

    @GET("home")
    suspend fun BalancesAndBenefits(): Response<BalanceBenefitsResponse>

    @GET("extract")
    suspend fun extractTransactions(
        @Query("start") dateFilterStart: String,
        @Query("end") dateFilterEnd: String
    ): Response<List<ExtractResponse>>

    @POST("pix/validation")
    suspend fun pixValidation(
        @Body pixItensRequest: PixItemsRequest
    ): Response<PixResponseValidation>

    @POST("pix/confirm")
    suspend fun pixConfirmation(
        @Header ("pix_token") pixToken: String
    ): Response<PixResponseConfirmation>

    @POST("home/sendfcm")
    suspend fun sendToken(
        @Body tokenFirebase: TokenFirebase
    )//: Response<Void>

    companion object {

        fun getEndPointInstance(sharedPreference: SharedPreferenceServices): EndPoint {
            return Retrofit.Builder()
                .baseUrl("https://us-central1-programa-de-bolsas---puc-2021.cloudfunctions.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideAuthClient(sharedPreference))
                .build().create(EndPoint::class.java)
        }

        private fun provideAuthClient(sharedPreference: SharedPreferenceServices): OkHttpClient {
            return OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                addInterceptor(AuthInterceptor(sharedPreference))
                addInterceptor(MockInterceptor())
            }.build()
        }

    }
}
