package com.example.tecnobank.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tecnobank.home.fragments.ListNotificationFragment

class ViewPagerNotificationAdapter(fragment:Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return ListNotificationFragment().also {
            it.arguments = Bundle().apply {
                putInt(POSITION_VIEW_PAGER_NOTIFICATION,position)
            }
        }
    }

    companion object{
        const val POSITION_VIEW_PAGER_NOTIFICATION = "position_view_pager_notification"
    }

}