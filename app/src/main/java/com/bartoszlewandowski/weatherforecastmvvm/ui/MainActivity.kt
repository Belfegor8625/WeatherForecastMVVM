package com.bartoszlewandowski.weatherforecastmvvm.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bartoszlewandowski.weatherforecastmvvm.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

private const val MY_PERMISSION_ACCESS_COARSE_LOCATION = 1

class MainActivity : AppCompatActivity(), DIAware {

	override val di by closestDI()
	private val fusedLocationProviderClient: FusedLocationProviderClient by instance()
	private val locationCallback = object : LocationCallback() {
		override fun onLocationResult(p0: LocationResult?) {
			super.onLocationResult(p0)
		}
	}
	private lateinit var navController: NavController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
		val navHostFragment =
			supportFragmentManager.findFragmentById(
				R.id.nav_host_fragment_container) as NavHostFragment
		navController = navHostFragment.navController
		bottom_nav.setupWithNavController(navController)
		NavigationUI.setupActionBarWithNavController(this, navController)
		requestLocationPermission()
		if (hasLocationPermission()) {
			bindLocationManager()
		} else
			requestLocationPermission()
	}

	private fun bindLocationManager() {
		LifecycleBoundLocationManager(this, fusedLocationProviderClient, locationCallback)
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp()
	}

	private fun requestLocationPermission() {
		ActivityCompat.requestPermissions(
			this,
			arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
			MY_PERMISSION_ACCESS_COARSE_LOCATION)
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray) {
		if (requestCode == MY_PERMISSION_ACCESS_COARSE_LOCATION) {
			if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
				bindLocationManager()
			else
				Toast.makeText(this, "Please, set location manually in settings", Toast.LENGTH_LONG)
					.show()
		}
	}

	private fun hasLocationPermission(): Boolean {
		return ContextCompat.checkSelfPermission(this,
			Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
	}
}