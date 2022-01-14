package com.example.tecnobank.intro.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tecnobank.R
import com.example.tecnobank.databinding.SplashFragmentBinding
import com.example.tecnobank.intro.viewmodel.SplashViewModel
import com.example.tecnobank.viewmodelfactory.ViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val SPLASH_DELAY: Long = 3000
    private var _binding: SplashFragmentBinding? = null
    private val binding: SplashFragmentBinding get() = _binding!!
    //private lateinit var viewModel: SplashViewModel
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        viewModel = ViewModelProvider(this, ViewModelFactory(requireContext(),null)).get(
//            SplashViewModel::class.java
//        )

        binding.splashLogo.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.top_animation)

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.initSplash()
        }, SPLASH_DELAY)

        viewModel.splashToOnBoarding.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.acao_splashfragment_para_onbordingfragment)
        })
        viewModel.splashToLogin.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.acao_splashfragment_para_loginfragment)
        })

    }

}
