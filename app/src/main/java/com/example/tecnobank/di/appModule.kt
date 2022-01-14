package com.example.tecnobank.di

import android.content.Context
import com.example.tecnobank.R
import com.example.tecnobank.data.local.SharedPreferenceServices
import com.example.tecnobank.data.local.database.ExtractDatabase
import com.example.tecnobank.data.remote.EndPoint
import com.example.tecnobank.data.remote.model.pix.PixItemsRequest
import com.example.tecnobank.extract.repository.ExtractRepository
import com.example.tecnobank.extract.viewmodel.ExtractViewModel
import com.example.tecnobank.extract.viewmodel.FilterExtractViewModel
import com.example.tecnobank.home.repository.*
import com.example.tecnobank.home.viewmodel.*
import com.example.tecnobank.intro.repository.LoginRepository
import com.example.tecnobank.intro.repository.OnBoardingRepository
import com.example.tecnobank.intro.repository.SplashRepository
import com.example.tecnobank.intro.viewmodel.LoginViewModel
import com.example.tecnobank.intro.viewmodel.OnBoardingViewModel
import com.example.tecnobank.intro.viewmodel.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel{
        SplashViewModel(get())
    }

    viewModel{
        OnBoardingViewModel(get())
    }

    viewModel{
        LoginViewModel(get())
    }

    viewModel{
        HomeActivityViewModel(get())
    }

    viewModel{
        HomeViewModel(get())
    }

    viewModel{ (args:PixItemsRequest) ->
        PixConfirmationViewModel(get(),args)
    }

    viewModel{
        PixInfoDestinationViewModel()
    }

    viewModel{
        PixOnBoardingViewModel(get())
    }

    viewModel{
        PixValueRequestViewModel(get())
    }

    viewModel{
        ProfileViewModel(get())
    }

    viewModel{
        ExtractViewModel(get())
    }

    viewModel{
        FilterExtractViewModel(get())
    }

    factory {
        SplashRepository(get())
    }

    factory {
        OnBoardingRepository(get())
    }

    factory {
        LoginRepository(get(),get())
    }

    factory {
        HomeActivityRepository(get())
    }

    factory {
        HomeRepository(get(),get())
    }

    factory {
        PixConfirmationRepository(get())
    }

    factory {
        PixRepository(get())
    }

    factory {
        ProfileRepository(get())
    }

    factory {
        ExtractRepository(get(),get(),get())
    }

    single {
        ExtractDatabase.getInstance(androidContext()).extractDAO()
    }

    single {
        EndPoint.getEndPointInstance(get())
    }

    single {
        SharedPreferenceServices(get())
    }

    single {
        androidContext()
            .getSharedPreferences(R.string.preference_file_key.toString(), Context.MODE_PRIVATE)
    }

}