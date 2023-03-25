package com.example.hairmasterplaner

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hairmasterplaner.databinding.ActivityMainBinding
import com.example.hairmasterplaner.ui.customerList.CustomerListFragmentDirections
import com.example.hairmasterplaner.ui.jobElementList.JobElementFragmentDirections
import com.example.hairmasterplaner.ui.jobList.JobListFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_job_list,
                R.id.nav_customer_list,
                R.id.nav_job_element_list,
                R.id.nav_price_register_list
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        binding.appBarMain.fab.setOnClickListener {
            val currentDestinationLabel = navController.currentDestination?.label
            val direction =
                when (currentDestinationLabel) {
                    getString(R.string.menu_job_element_list) -> JobElementFragmentDirections.actionNavJobElementListToJobElementItemFragment(
                        null
                    )
                    getString(R.string.menu_customer_list) -> CustomerListFragmentDirections.actionNavCustomerListToFragmentCustomerItem(
                        null
                    )
                    getString(R.string.menu_job_list) -> JobListFragmentDirections.actionNavJobListToNavJobBody(
                        null
                    )
                    else -> TODO("Трэба допилить навигацию кнопки добавить")
                }
            navController.navigate(direction)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}