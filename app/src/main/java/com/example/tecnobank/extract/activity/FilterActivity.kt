package com.example.tecnobank.extract.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tecnobank.databinding.FilterActivityBinding
import com.example.tecnobank.databinding.IntroActivityBinding

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: FilterActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FilterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
