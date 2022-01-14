package com.example.tecnobank.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tecnobank.home.fragments.services.ServicesFragment

class ViewPagerServicesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return ServicesFragment().also {
            it.arguments = Bundle().apply {
                putInt(POSITION_VIEW_PAGER_SERVICES, position)
            }
        }
    }
    companion object{
        const val POSITION_VIEW_PAGER_SERVICES = "position_view_pager_services"
    }
}
