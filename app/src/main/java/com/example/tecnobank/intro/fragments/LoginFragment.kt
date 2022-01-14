package com.example.tecnobank.intro.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tecnobank.R
import com.example.tecnobank.databinding.LoginFragmentBinding
import com.example.tecnobank.databinding.ModalBottomSheetDialogBinding
import com.example.tecnobank.intro.viewmodel.LoginViewModel
import com.example.tecnobank.intro.viewmodel.SplashViewModel
import com.example.tecnobank.viewmodelfactory.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding: LoginFragmentBinding get() = _binding!!
    //private lateinit var viewModel: LoginViewModel
    private val viewModel: LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(this, ViewModelFactory(requireContext(),null)).get(
//            LoginViewModel::class.java
//        )

        binding.loginEmail.addTextChangedListener {
            viewModel.onEmailChange(it.toString())
        }

        binding.loginPassword.addTextChangedListener {
            viewModel.onPasswordChange(it.toString())
        }

        binding.loginEmail.setText(viewModel.getEmail())
        binding.loginPassword.setText(viewModel.getPassword())

        viewModel.initLogin()

        viewModel.rememberUserToogle.observe(viewLifecycleOwner, {
            binding.remeberLogin.toggle()
        })

        viewModel.emailErro.observe(viewLifecycleOwner, {
            binding.loginUsernameLayout.error = it
        })

        viewModel.passwordErro.observe(viewLifecycleOwner, {
            binding.loginInputLayout.error = it
        })

        viewModel.goToHome.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.acao_loginfragment_to_homeactivity)
            requireActivity().finish()
        })

        viewModel.showErro.observe(viewLifecycleOwner, {
            ModalBottomSheet(it).show(childFragmentManager, null)
        })

        binding.remeberLogin.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onRememberChecked(isChecked)
        }

        binding.loginEnter.setOnClickListener {
            viewModel.onLoginClicked()
        }

        viewModel.showLoading.observe(viewLifecycleOwner,{
            binding.progressCircular.isVisible = it
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

class ModalBottomSheet(private val message: String): BottomSheetDialogFragment() {

    private var _binding: ModalBottomSheetDialogBinding? = null
    private val binding: ModalBottomSheetDialogBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModalBottomSheetDialogBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.messageError.text = message

        binding.reponseOk.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

}
