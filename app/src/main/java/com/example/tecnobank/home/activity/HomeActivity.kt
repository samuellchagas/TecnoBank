package com.example.tecnobank.home.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tecnobank.R
import com.example.tecnobank.data.remote.model.home.TokenFirebase
import com.example.tecnobank.databinding.HomeActivityBinding
import com.example.tecnobank.home.viewmodel.HomeActivityViewModel
import com.example.tecnobank.intro.viewmodel.SplashViewModel
import com.example.tecnobank.viewmodelfactory.ViewModelFactory
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private var _binding: HomeActivityBinding? = null
    private val binding: HomeActivityBinding get() = _binding!!
    //private lateinit var viewModel: HomeActivityViewModel
    private val viewModel: HomeActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel = ViewModelProvider(this,ViewModelFactory(this,null))
//            .get(HomeActivityViewModel::class.java)

        binding.bottomNavigation.setupWithNavController(
            (supportFragmentManager
                .findFragmentById(R.id.navHostFragmentPix) as NavHostFragment).navController
        )

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            viewModel.sendToken(TokenFirebase(task.result!!))
        })

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Deseja sair do aplicativo?")
            .setMessage("")
            .setNegativeButton("Não") { _, _ -> }
            .setPositiveButton("Sim") { _, _ -> finish() }
            .create()
            .show()
    }

}
