package com.example.tecnobank.home.fragments.services

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tecnobank.R
import com.example.tecnobank.databinding.PixInfoDestinationFragmentBinding
import com.example.tecnobank.home.viewmodel.PixInfoDestinationViewModel
import com.example.tecnobank.intro.viewmodel.SplashViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class PixInfoDestinationFragment : Fragment() {

    private var _binding: PixInfoDestinationFragmentBinding? = null
    private val binding: PixInfoDestinationFragmentBinding get() = _binding!!
    //private lateinit var viewModel: PixInfoDestinationViewModel
    private val viewModel: PixInfoDestinationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PixInfoDestinationFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(this).get(PixInfoDestinationViewModel::class.java)

        binding.toolbarPixInsertEmail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.defaultEditText.addTextChangedListener {
            viewModel.changeDestinationEmailPix(it.toString())
        }

        viewModel.confirmationButtonEnabled.observe(viewLifecycleOwner, {
            binding.pixApplyEmail.isEnabled = it
        })

        viewModel.goToDescriptionPix.observe(viewLifecycleOwner, {
            findNavController().navigate(
                PixInfoDestinationFragmentDirections
                    .actionPixInfoDestinationFragmentToPixDescriptionFragment(
                        it
                    )
            )
        })

        viewModel.emailErro.observe(viewLifecycleOwner, {
            binding.editEmail.error = it
        })

        binding.pixApplyEmail.setOnClickListener {
            viewModel.onClickApplyInfoDestinationPix()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
