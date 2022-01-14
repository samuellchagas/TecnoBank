package com.example.tecnobank.home.activity.services

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.example.tecnobank.R
import com.example.tecnobank.databinding.PixQrCodeActivityBinding

class PixQrCodeActivity : AppCompatActivity() {

    private lateinit var binding: PixQrCodeActivityBinding
    private val args: PixQrCodeActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PixQrCodeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = (supportFragmentManager
            .findFragmentById(R.id.navHostFragmentPix) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.pix_qr_code_nav_graph)
        graph.startDestination = if (args.passedToPixOnbording) {
            R.id.pixQrCodeFragment
        } else {
            R.id.pixOnBordingFragment
        }
        navHostFragment.navController.graph = graph
    }
}
