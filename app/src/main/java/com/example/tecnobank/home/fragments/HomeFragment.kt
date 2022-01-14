package com.example.tecnobank.home.fragments

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnobank.R
import com.example.tecnobank.data.remote.model.home.BalanceBenefitsResponse
import com.example.tecnobank.databinding.HomeFragmentBinding
import com.example.tecnobank.extension.HelperFunctions.converterToReal
import com.example.tecnobank.home.adapter.ViewPagerServicesAdapter
import com.example.tecnobank.home.dialog.ProfileDialog
import com.example.tecnobank.home.recyclerview.DotsDecoration
import com.example.tecnobank.home.recyclerview.ListBenefitsAdapter
import com.example.tecnobank.home.recyclerview.PagerDecoratorDots
import com.example.tecnobank.home.viewmodel.HomeViewModel
import com.example.tecnobank.intro.viewmodel.SplashViewModel
import com.example.tecnobank.viewmodelfactory.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!
    //private lateinit var viewModel: HomeViewModel
    private val viewModel: HomeViewModel by viewModel()
    private val onUpdateBalanceReceiver:BroadcastReceiver = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            viewModel.onOpenHome()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val broadcast = LocalBroadcastManager.getInstance(requireContext())
        broadcast.registerReceiver(onUpdateBalanceReceiver, IntentFilter("UPDATE_BALANCE"))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        viewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(requireContext(),null)
//        ).get(HomeViewModel::class.java)

        viewModel.onOpenHome()

        viewModel.loading.observe(viewLifecycleOwner, {
            binding.progressCircular.isVisible = it
            binding.shimmerViewContainer.showShimmer(it)
            binding.shimmerViewContainer.isVisible = it
            binding.currentValue.isInvisible = it
        })

        viewModel.responseSucess.observe(viewLifecycleOwner, {
            binding.listBenefits.isVisible = true
            binding.currentValue.text = converterToReal(it.balance.currentValue.toDouble())
            binding.receivables.text = converterToReal(it.balance.receivables.toDouble())
            recyclerViewConfig(it.benefits)
        })

        viewModel.responseErro.observe(viewLifecycleOwner, {
            showInfo(it)
        })

        binding.btVisibleBalance.setOnClickListener {
            viewModel.checkVisibleBalances()
        }

        binding.incompletSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_accountDependencyActivity)
        }

        binding.toolbarHome.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.scannerqrcode -> {
                    findNavController().navigate(R.id.action_homeFragment_to_qrCodeSafeActivity)
                    true
                }
                R.id.notification -> {
                    findNavController().navigate(R.id.action_homeFragment_to_notificationActivity)
                    true
                }
                else ->{
                    ProfileDialog().show(childFragmentManager, "dialog_profile")
                    false
                }
            }
        }

        viewModel.balanceVisible.observe(viewLifecycleOwner, {
            if(it) {
                binding.currentValue.transformationMethod = null
                binding.receivables.transformationMethod = null
                binding.btVisibleBalance.setImageResource(R.drawable.ic_visibility_on)
            }else {
                binding.currentValue.transformationMethod = PasswordTransformationMethod()
                binding.receivables.transformationMethod = PasswordTransformationMethod()
                binding.btVisibleBalance.setImageResource(R.drawable.ic_visibility_off)
            }
        })

        binding.cardHelp.setOnClickListener {
            openWebPage("https://chat.blip.ai/?appKey=YXNzaXN0ZW50ZXZpcnR1YWx0ZWNub2Jhbms6MjEyNDY4M2QtYmMzMS00ZmUyLTlkMDQtYWRmODhmMDU4YWMx")
        }

        viewPagerAndTabLayoutConfig()
    }

    private fun viewPagerAndTabLayoutConfig() {
        binding.pagerFunctionalities.adapter = ViewPagerServicesAdapter(this)
        tabLayoutConfig()
    }

    private fun tabLayoutConfig() {
        TabLayoutMediator(
            binding.tabLayoutFunctionalities,
            binding.pagerFunctionalities
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.main_services)
                }
                1 -> {
                    tab.text = getString(R.string.products_services)
                }
                2 -> {
                    tab.text = getString(R.string.service_services)
                }
            }
        }.attach()
    }

    private fun recyclerViewConfig(listBenefitsResponse: List<BalanceBenefitsResponse.Benefits>) {
        with(binding.listBenefits) {
            adapter = ListBenefitsAdapter(listBenefitsResponse)
            addItemDecoration(DotsDecoration(requireContext()))
            onFlingListener=null
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    fun showInfo(titulo: String?) {
        AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle(titulo)
            .setMessage("")
            .show()
    }

    fun openWebPage(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(onUpdateBalanceReceiver)
    }

}
