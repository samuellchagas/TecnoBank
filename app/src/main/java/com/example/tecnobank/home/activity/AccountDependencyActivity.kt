package com.example.tecnobank.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tecnobank.databinding.AccountDependencyActivityBinding

class AccountDependencyActivity : AppCompatActivity() {

    private var binding: AccountDependencyActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AccountDependencyActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

}
