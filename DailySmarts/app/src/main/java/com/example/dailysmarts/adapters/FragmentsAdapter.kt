package com.example.dailysmarts.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.dailysmarts.fragments.MyQuotesFragment
import com.example.dailysmarts.fragments.ViewDailyQuotesFragment

class FragmentsAdapter(var context: Context, fragmentManager: FragmentManager, var tabs: Int) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MyQuotesFragment()
            }
            1 -> {
                ViewDailyQuotesFragment()
            }
            else -> getItem(position)
        }
    }

    override fun getCount() = tabs
}