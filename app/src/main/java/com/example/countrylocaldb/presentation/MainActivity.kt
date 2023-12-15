package com.example.countrylocaldb.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.countrylocaldb.databinding.ActivityMainBinding
import com.example.countrylocaldb.presentation.people.PeopleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActionBar()

        viewModel.setPeopleList()
        setupRvPeople()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        title = null
    }

    private fun setupRvPeople() {
        val peopleAdapter = PeopleAdapter().also { binding.rvPeople.adapter = it }
        viewModel.liveDataPeople.observe(this) { peopleList ->
//            println("65765765   ${peopleList.size}")
            peopleAdapter.submitList(peopleList)
        }
    }
}