package com.example.tecnobank.data.remote

import com.example.tecnobank.data.local.SharedPreferenceServices
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sharedPreferences: SharedPreferenceServices):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(sharedPreferences.getSaveTokenAuthentication().isNotEmpty()){
            return chain.proceed(chain.request().newBuilder().addHeader("token",
                sharedPreferences.getSaveTokenAuthentication()).build())
        }else{
           return chain.proceed(chain.request().newBuilder().build())
        }
    }
}