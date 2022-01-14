package com.example.tecnobank.home.fragments.services

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tecnobank.R
import com.example.tecnobank.databinding.PixTransferModeFragmentBinding

class PixTransferModeFragment: Fragment() {

    private var _binding: PixTransferModeFragmentBinding? = null
    private val binding: PixTransferModeFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PixTransferModeFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarPixChoice.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.btPixEmail.setOnClickListener {
         findNavController().navigate(R.id.action_pixModeTransferFragment_to_pixInfoDestinationFragment)
        }

        binding.btPhone.setOnClickListener {
            callMessagePix()
        }

        binding.btCpf.setOnClickListener {
            callMessagePix()
        }

        binding.btManualKey.setOnClickListener {
            callMessagePix()
        }

        binding.btRandomKey.setOnClickListener {
            callMessagePix()
        }

    }

    private fun callMessagePix() {
        AlertDialog.Builder(requireContext()).setTitle(getString(R.string.title_tranfer_dialog))
            .setMessage(getString(R.string.text_tranfer_dialog))
            .show()
    }


}
