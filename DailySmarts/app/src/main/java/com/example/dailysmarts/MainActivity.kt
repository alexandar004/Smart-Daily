package com.example.dailysmarts

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.dailysmarts.adapters.FragmentsAdapter
import com.example.dailysmarts.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var pager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setTabs()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    private fun setTabs() {
        tabLayout = binding.tabLayout
        pager = binding.pager

        tabLayout.addTab(tabLayout.newTab().setText("Daily Quotes"))
        tabLayout.addTab(tabLayout.newTab().setText("My Quotes"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val pagerAdapter = FragmentsAdapter(this, supportFragmentManager, tabLayout.tabCount)
        pager.adapter = pagerAdapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }
}