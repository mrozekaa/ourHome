package com.mrozeka.naszdom

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mrozeka.naszdom.databinding.ActivityMainBinding
import com.mrozeka.naszdom.helper.PermissionHelper
import com.mrozeka.naszdom.pref.PrefRepository

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var prefRepository: PrefRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefRepository = PrefRepository(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_emapa,
                R.id.nav_geoportal,
                R.id.nav_z500,
                R.id.nav_extra_dom,
                R.id.nav_archon,
                R.id.nav_archeton,
                R.id.nav_murator,
                R.id.nav_przytulny3,
                R.id.nav_mpzp,
                R.id.nav_land_register,
                R.id.nav_zimmermannhaus,
                R.id.nav_samborrex,
                R.id.nav_contacts,
                R.id.nav_notes
            ), drawerLayout
        )
        if (!PermissionHelper.isCallPermissionGranted((this))) {
            PermissionHelper.requestCallPermission(this)
        }

        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
                drawerView.findViewById<TextView>(R.id.nav_header_title_tv)?.apply {
                    if (text != null && text != prefRepository.getHomeTitle()) {
                        text = prefRepository.getHomeTitle()
                    }
                }
                drawerView.findViewById<TextView>(R.id.nav_header_subtitle_tv)?.apply {
                    if (text != null && text != prefRepository.getHomeSubtitle()) {
                        text = prefRepository.getHomeSubtitle()
                    }
                }
            }

            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerStateChanged(newState: Int) {

            }
        })
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}