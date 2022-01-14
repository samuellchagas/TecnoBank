package com.example.tecnobank.home.fragments.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tecnobank.R
import com.example.tecnobank.databinding.PixDescriptionFragmentBinding

class PixDescriptionFragment : Fragment() {

    private var _binding: PixDescriptionFragmentBinding? = null
    private val binding: PixDescriptionFragmentBinding get() = _binding!!
    private val args: PixDescriptionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PixDescriptionFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarPixDescription.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.pixApplyDescription.setOnClickListener {
            findNavController().navigate(
                PixDescriptionFragmentDirections
                    .actionPixDescriptionFragmentToPixValueRequestFragment(
                        args.email,
                        binding.editTextDescription.text.toString()
                    )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
