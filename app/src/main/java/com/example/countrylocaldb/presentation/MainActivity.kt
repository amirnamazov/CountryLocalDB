package com.example.countrylocaldb.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.countrylocaldb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModels()

    private val peopleAdapter by lazy { PeopleAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActionBar()

        binding.rvPeople.adapter = peopleAdapter

        viewModel.getCountries()
        viewModel.liveDataCountries.observe(this) { res ->
            peopleAdapter.submitList(res)
        }

//        setupRvPeople()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        title = null
    }

    private fun setupRvPeople() = with(binding.rvPeople) {

    }
}