package com.example.tecnobank.extract.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tecnobank.R
import com.example.tecnobank.databinding.FilterExtractFragmentBinding
import com.example.tecnobank.extract.recyclerview.ListFilterAdapter
import com.example.tecnobank.extract.viewmodel.ExtractViewModel
import com.example.tecnobank.extract.viewmodel.FilterExtractViewModel
import com.example.tecnobank.intro.viewmodel.SplashViewModel
import com.example.tecnobank.viewmodelfactory.ViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterExtractFragment : Fragment(), ListFilterAdapter.SelectFilterlistener {

    private var _binding: FilterExtractFragmentBinding? = null
    private val binding: FilterExtractFragmentBinding get() = _binding!!
    //private lateinit var viewModel: FilterExtractViewModel
    private val viewModel: FilterExtractViewModel by viewModel()

    private val listItemFilter: List<String> by lazy {
        listOf(
            getString(R.string.list_filter_item_1),
            getString(R.string.list_filter_item_2),
            getString(R.string.list_filter_item_3),
            getString(R.string.list_filter_item_4),
            getString(R.string.list_filter_item_5)
        )
    }
    private var positionSelected: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilterExtractFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(requireContext(),null)
//        ).get(FilterExtractViewModel::class.java)

        binding.filterToolbar.setNavigationOnClickListener {
            requireActivity().finish()
        }

        viewModel.getSaveItemFilterSelected()

        viewModel.itemFilterPosition.observe(viewLifecycleOwner, {
            binding.listFilters.adapter = ListFilterAdapter(
                listItemFilter,
                this@FilterExtractFragment,
                it
            )
        })

        binding.applyFilter.setOnClickListener {
            requireActivity().setResult(
                RESULT_CODE,
                Intent().apply {
                    putExtra(FILTER_TEXT, listItemFilter[positionSelected])
                    putExtra(FILTER_POSITION, positionSelected)
                }
            )
            requireActivity().finish()
        }

    }

    override fun selectedFilterlistener(position: Int) {
        this.positionSelected = position
        viewModel.saveItemFilterSelected(position)
    }

    companion object {
        const val RESULT_CODE: Int = 2
        const val FILTER_TEXT = "filter_text"
        const val FILTER_POSITION = "filter_position"
    }

}
