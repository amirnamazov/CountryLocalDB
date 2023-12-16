package com.example.countrylocaldb.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.countrylocaldb.R
import com.example.countrylocaldb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragContainer.id) as NavHostFragment
        navHostFragment.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        setupNavigation()
    }

    private fun initializeViews() = with(binding) {
        setContentView(root)
        setSupportActionBar(toolbar)
    }

    private fun setupNavigation() {
        navController.addOnDestinationChangedListener { _, des, _ ->
            des.label.also {
                title = it
                setupNavigationBack(it != getString(R.string.people))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    private fun setupNavigationBack(show: Boolean) {
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(show)
            it.setDisplayHomeAsUpEnabled(show)
        }
    }
}