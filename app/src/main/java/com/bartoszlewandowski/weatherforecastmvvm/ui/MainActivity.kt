package com.bartoszlewandowski.weatherforecastmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bartoszlewandowski.weatherforecastmvvm.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	private lateinit var navController: NavController
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
		navController =  navHostFragment.navController
		bottom_nav.setupWithNavController(navController)
		NavigationUI.setupActionBarWithNavController(this, navController)
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp()
	}
}