package com.example.tecnobank.cards.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tecnobank.R
import com.example.tecnobank.databinding.CardsFragmentBinding

class CardsFragment : Fragment() {

    private var binding: CardsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CardsFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }
}
