package com.example.tecnobank.home.activity.services

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnobank.databinding.PayQrCodeActivityBinding
import com.example.tecnobank.databinding.PayQrCodeFragmentBinding
import com.example.tecnobank.databinding.PixQrCodeActivityBinding

class PayQrCodeActivity : AppCompatActivity() {

    private var binding: PayQrCodeActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PayQrCodeActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

}
