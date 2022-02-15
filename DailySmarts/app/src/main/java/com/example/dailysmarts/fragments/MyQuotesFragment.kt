package com.example.dailysmarts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dailysmarts.databinding.FragmentMyQuotesBinding

class MyQuotesFragment : Fragment() {

    private lateinit var binding: FragmentMyQuotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyQuotesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
}