package com.example.tecnobank.extract.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tecnobank.R
import com.example.tecnobank.databinding.ExtractFragmentBinding
import com.example.tecnobank.extract.activity.FilterActivity
import com.example.tecnobank.extract.fragments.FilterExtractFragment.Companion.FILTER_POSITION
import com.example.tecnobank.extract.fragments.FilterExtractFragment.Companion.FILTER_TEXT
import com.example.tecnobank.extract.fragments.FilterExtractFragment.Companion.RESULT_CODE
import com.example.tecnobank.extract.recyclerview.ListExtractsAdapter
import com.example.tecnobank.extract.viewmodel.ExtractViewModel
import com.example.tecnobank.intro.viewmodel.SplashViewModel
import com.example.tecnobank.viewmodelfactory.ViewModelFactory
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExtractFragment : Fragment() {

    private var _binding: ExtractFragmentBinding? = null
    private val binding: ExtractFragmentBinding get() = _binding!!
    //private lateinit var viewModel: ExtractViewModel
    private val viewModel: ExtractViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExtractFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(requireContext(),null)
//        ).get(ExtractViewModel::class.java)

        viewModel.requestExtracts()

        viewModel.onChangeSaveDataFilter()

        checkSelectedButton(everyButton = true, inputButton = false, exitButton = false)

        viewModel.dataFilter.observe(viewLifecycleOwner, {
            binding.textFilter.text = it
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            binding.progressCircular.isVisible = it
        })

        viewModel.responseErro.observe(viewLifecycleOwner, {
            showInfo(it)
        })

        binding.extractToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.filtro -> {
                    startActivityForResult(
                        Intent(requireActivity(), FilterActivity::class.java),
                        REQUEST_CODE
                    )
                    true
                }
                else -> false
            }
        }

        binding.everyExtracts.setOnClickListener {
            checkSelectedButton(everyButton = true, inputButton = false, exitButton = false)
            viewModel.buttonPressedEvery()
        }

        binding.inputsExtract.setOnClickListener {
            checkSelectedButton(everyButton = false, inputButton = true, exitButton = false)
            viewModel.buttonPressedInputs()
        }

        binding.exitExtracts.setOnClickListener {
            checkSelectedButton(everyButton = false, inputButton = false, exitButton = true)
            viewModel.buttonPressedExit()
        }

        viewModel.extractList.observe(viewLifecycleOwner,{
            recyclerViewConfig(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
            checkSelectedButton(everyButton = true, inputButton = false, exitButton = false)
            viewModel.onChangeDataFilter(
                filterText = data!!.getStringExtra(FILTER_TEXT)!!
                    .toString().lowercase(),
                filterPosition = data.getIntExtra(FILTER_POSITION, 1)
            )
        }
    }

    private fun recyclerViewConfig(listExtracts: List<ExtractViewModel.ExtractItemAdapter>) {
        if(listExtracts.isNotEmpty()) {
            binding.imageExtract.isVisible = false
            binding.textExtract.isVisible = false
            binding.textFilter.isVisible = false
            binding.listExtracts.isVisible = true
            binding.listExtracts.adapter = ListExtractsAdapter(listExtracts, requireContext())
        }else{
            binding.imageExtract.isVisible = true
            binding.textExtract.isVisible = true
            binding.textFilter.isVisible = true
            binding.listExtracts.isVisible = false
        }
    }

    private fun checkSelectedButton(everyButton:Boolean, inputButton:Boolean, exitButton:Boolean) {
        when {
            everyButton -> {
                paintButtonOn(binding.everyExtracts)
                paintButtonOff(binding.inputsExtract)
                paintButtonOff(binding.exitExtracts)
            }
            inputButton -> {
                paintButtonOff(binding.everyExtracts)
                paintButtonOn(binding.inputsExtract)
                paintButtonOff(binding.exitExtracts)
            }
            exitButton -> {
                paintButtonOff(binding.everyExtracts)
                paintButtonOff(binding.inputsExtract)
                paintButtonOn(binding.exitExtracts)
            }
        }
    }

    private fun paintButtonOn(button: ExtendedFloatingActionButton) {
        with(button) {
            setBackgroundColor(getColor(requireContext(), R.color.white))
            setTextColor(getColor(requireContext(), R.color.greenTecnoBank))
            setStrokeColorResource(R.color.greenTecnoBank)
        }
    }

    private fun paintButtonOff(button: ExtendedFloatingActionButton) {
        with(button) {
            setBackgroundColor(getColor(requireContext(), R.color.greenTecnoBank))
            setTextColor(getColor(requireContext(), R.color.white))
            setStrokeColorResource(R.color.white)
        }
    }

    fun showInfo(titulo: String?) {
        AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle(titulo)
            .setMessage("")
            .show()
    }

    companion object {
        private const val REQUEST_CODE: Int = 1
    }
}
