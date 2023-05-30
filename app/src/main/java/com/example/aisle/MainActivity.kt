package com.example.aisle

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aisle.databinding.ActivityMainBinding
import com.example.aisle.ui.notes.NotesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        supportActionBar?.hide()

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        var token = intent.getStringExtra("token").toString()
        Log.e("TAG", "onCreate: $token")
//        if (navController.currentDestination?.id == R.id.navigation_notes) {
//            val bundle = Bundle().apply {
//                putString("token", intent.getStringExtra("token").toString())
//            }
//            navController.setGraph(navController.graph, bundle)
//        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard,
                R.id.navigation_matches,
                R.id.navigation_notes,
                R.id.navigation_profile
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}