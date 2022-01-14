package com.example.tecnobank.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tecnobank.databinding.QrCodeSafeActivityBinding

class QrCodeSafeActivity : AppCompatActivity() {

    private var binding: QrCodeSafeActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = QrCodeSafeActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

    }

}
