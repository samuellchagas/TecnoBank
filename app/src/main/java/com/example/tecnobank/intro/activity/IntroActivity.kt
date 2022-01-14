package com.example.tecnobank.intro.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tecnobank.databinding.IntroActivityBinding
import com.google.firebase.FirebaseApp

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: IntroActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = IntroActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
