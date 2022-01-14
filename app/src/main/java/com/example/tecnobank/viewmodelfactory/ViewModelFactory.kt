package com.example.tecnobank.viewmodelfactory

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tecnobank.R
import com.example.tecnobank.data.local.SharedPreferenceServices
import com.example.tecnobank.data.local.database.ExtractDAO
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

class ViewModelFactory(private val context: Context, private val args: PixItemsRequest?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SplashViewModel::class.java) {
            return providerSplashViewModel() as T
        }
        if (modelClass == OnBoardingViewModel::class.java) {
            return providerOnBoardingViewModel() as T
        }
        if (modelClass == LoginViewModel::class.java) {
            return providerLoginViewModel() as T
        }
        if (modelClass == HomeViewModel::class.java) {
            return providerHomeViewModel() as T
        }
        if (modelClass == ProfileViewModel::class.java) {
            return providerProfileViewModel() as T
        }
        if (modelClass == ExtractViewModel::class.java) {
            return providerExtractViewModel() as T
        }
        if (modelClass == FilterExtractViewModel::class.java) {
            return providerFilterExtractViewModel() as T
        }
        if (modelClass == PixOnBoardingViewModel::class.java) {
            return providerPixOnBordingViewModel() as T
        }
        if (modelClass == PixValueRequestViewModel::class.java) {
            return providerPixValueRequestViewModel() as T
        }
        if (modelClass == PixConfirmationViewModel::class.java) {
            return providerPixConfirmationViewModel() as T
        }
        if (modelClass == HomeActivityViewModel::class.java) {
            return providerHomeActivityViewModel() as T
        }
        throw Exception("ViewModel não encotrado")
    }

    private fun providerSplashViewModel(): SplashViewModel {
        return SplashViewModel(
            SplashRepository(
                providerSharedPreferenceService(
                    providerSharedPreference()
                )
            )
        )
    }

    private fun providerOnBoardingViewModel(): OnBoardingViewModel {
        return OnBoardingViewModel(
            OnBoardingRepository(
                providerSharedPreferenceService(
                    providerSharedPreference()
                )
            )
        )
    }

    private fun providerLoginViewModel(): LoginViewModel {
        return LoginViewModel(
            LoginRepository(
                providerEndPointInstance(),
                providerSharedPreferenceService(providerSharedPreference())
            )
        )
    }

    private fun providerHomeViewModel(): HomeViewModel {
        return HomeViewModel(
            HomeRepository(
                providerEndPointInstance(),
                providerSharedPreferenceService(providerSharedPreference())
            )
        )
    }

    private fun providerProfileViewModel(): ProfileViewModel {
        return ProfileViewModel(
            ProfileRepository(
                providerSharedPreferenceService(
                    providerSharedPreference()
                )
            )
        )
    }

    private fun providerExtractViewModel(): ExtractViewModel {
        return ExtractViewModel(
            ExtractRepository(
                providerEndPointInstance(),
                providerExtractDAO(providerExtractDatabase()),
                providerSharedPreferenceService(providerSharedPreference())
            )
        )
    }

    private fun providerFilterExtractViewModel(): FilterExtractViewModel {
        return FilterExtractViewModel(
            ExtractRepository(
                providerEndPointInstance(),
                providerExtractDAO(providerExtractDatabase()),
                providerSharedPreferenceService(providerSharedPreference())
            )
        )
    }

    private fun providerPixOnBordingViewModel(): PixOnBoardingViewModel {
        return PixOnBoardingViewModel(
            PixRepository(
                providerSharedPreferenceService(
                    providerSharedPreference()
                )
            )
        )
    }

    private fun providerPixValueRequestViewModel(): PixValueRequestViewModel {
        return PixValueRequestViewModel(
            HomeRepository(
                providerEndPointInstance(),
                providerSharedPreferenceService(providerSharedPreference())
            )
        )
    }

    private fun providerPixConfirmationViewModel(): PixConfirmationViewModel {
        return PixConfirmationViewModel(
            PixConfirmationRepository(
                providerEndPointInstance()
            ),
            args
        )
    }

    private fun providerHomeActivityViewModel(): HomeActivityViewModel {
        return HomeActivityViewModel(
            HomeActivityRepository(
                providerEndPointInstance()
            )
        )
    }

    private fun providerExtractDAO(extractDatabase: ExtractDatabase): ExtractDAO {
        return extractDatabase.extractDAO()
    }

    private fun providerExtractDatabase(): ExtractDatabase {
        return ExtractDatabase.getInstance(context)
    }

    private fun providerEndPointInstance(): EndPoint {
        return EndPoint.getEndPointInstance(
            providerSharedPreferenceService(
                providerSharedPreference()
            )
        )
    }

    private fun providerSharedPreferenceService(
        preferences: SharedPreferences
    ): SharedPreferenceServices {
        return SharedPreferenceServices(preferences)
    }

    private fun providerSharedPreference(): SharedPreferences {
        return context.getSharedPreferences(
            R.string.preference_file_key.toString(), Context.MODE_PRIVATE
        )
    }

}
