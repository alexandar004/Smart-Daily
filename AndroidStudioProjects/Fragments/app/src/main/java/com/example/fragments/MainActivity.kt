package com.example.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var profileFragment: ProfileFragment
    private lateinit var btnProfile: Button
    private lateinit var btnTeam: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnProfile = findViewById(R.id.btn_profile)
        btnTeam = findViewById(R.id.btn_team)
    }

    private fun setClickListeners(){
        btnTeam.setOnClickListener{
        }
        btnTeam.setOnClickListener{

        }
    }
}