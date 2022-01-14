package com.example.tecnobank.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tecnobank.R
import com.example.tecnobank.databinding.NotificationFragmentBinding
import com.example.tecnobank.home.adapter.ViewPagerNotificationAdapter
import com.google.android.material.tabs.TabLayoutMediator

class NoficationFragment: Fragment() {

    private var _binding: NotificationFragmentBinding? = null
    private val binding: NotificationFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NotificationFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pagerNotification.adapter = ViewPagerNotificationAdapter(this)

        TabLayoutMediator(
            binding.tabLayoutNotification,
            binding.pagerNotification
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.title_tab_layoult_notification_1)
                }
                1 -> {
                    tab.text = getString(R.string.title_tab_layoult_notification_2)
                }
                2 -> {
                    tab.text = getString(R.string.title_tab_layoult_notification_3)
                }
            }
        }.attach()

        binding.toobarNotification.setNavigationOnClickListener {
            requireActivity().finish()
        }

    }

}
