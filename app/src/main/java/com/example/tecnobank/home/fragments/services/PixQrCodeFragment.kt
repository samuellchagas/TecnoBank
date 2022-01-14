package com.example.tecnobank.home.fragments.services

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tecnobank.R
import com.example.tecnobank.databinding.PixQrCodeFragmentBinding


class PixQrCodeFragment : Fragment() {

    private var _binding: PixQrCodeFragmentBinding? = null
    private val binding: PixQrCodeFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PixQrCodeFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pixQrcodeToobar.setNavigationOnClickListener{
            requireActivity().finish()
        }

        binding.pixTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_pixQrCodeFragment_to_pixModeTransferFragment)
        }

        binding.payQrCode.setOnClickListener {
            findNavController().navigate(R.id.action_pixQrCodeFragment_to_payQrCodeActivity2)
        }

        binding.helpPix.setOnClickListener {
            openWebPage("https://chat.blip.ai/?appKey=YXNzaXN0ZW50ZXZpcnR1YWx0ZWNub2Jhbms6MjEyNDY4M2QtYmMzMS00ZmUyLTlkMDQtYWRmODhmMDU4YWMx")
        }

        binding.btShare.setOnClickListener {
            Toast.makeText(requireContext(),"Copiado para a área de transferência",Toast.LENGTH_LONG).show()
        }
    }

    fun openWebPage(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
