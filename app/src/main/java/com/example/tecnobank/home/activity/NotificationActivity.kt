package com.example.tecnobank.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tecnobank.databinding.NotificationActivityBinding

class NotificationActivity : AppCompatActivity() {

    private var binding: NotificationActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NotificationActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

    }

}
