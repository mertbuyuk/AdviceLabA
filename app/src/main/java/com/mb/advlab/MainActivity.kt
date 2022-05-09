package com.mb.advlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mb.advlab.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        supportActionBar?.hide()
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        navView = binding.navView
        navView.setupWithNavController(navController)

        var appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.profileFragment,
                R.id.homeFragment,
                R.id.exploreFragment
            )
        )

        binding.fab.setOnClickListener {
            navController.navigate(R.id.sharePostFragment)
        }

        setupActionBarWithNavController(navController,appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signupFragment -> hideBottomNav()
                R.id.loginFragment -> hideBottomNav()
                R.id.sharePostFragment -> {
                    hideBottomNav()
                    supportActionBar?.show()
                }
                R.id.followedDetailsFragment ->{
                    hideBottomNav()
                    supportActionBar?.show()
                }
                R.id.followerFragment->{
                    hideBottomNav()
                    supportActionBar?.show()
                }
                else -> showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.framecontainer.visibility = View.VISIBLE
        supportActionBar?.hide()
    }

    private fun hideBottomNav() {
        binding.framecontainer.visibility  = View.GONE
        supportActionBar?.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}