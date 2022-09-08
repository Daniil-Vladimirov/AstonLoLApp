package com.example.astonlolapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.astonlolapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigation = binding.bottomNavigationView
        bottomNavigation.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.listScreenFragment, R.id.fragmentLocations, R.id.fragmentComics)
        )


    }


    /*      bottomNavigation.setOnItemSelectedListener { menueItem ->
              when (menueItem.itemId) {
                  R.id.heroes_list_menu_item -> supportFragmentManager.commit {
                      replace<ListScreenFragment>(R.id.navHostFragment)
                      setReorderingAllowed(true)
                      // addToBackStack("list_item")
                  }
                  R.id.locations_fragment_menu_item -> supportFragmentManager.commit {
                      replace<FragmentLocations>(R.id.navHostFragment)
                      setReorderingAllowed(true)

                      //addToBackStack("location_item")
                  }
                  R.id.comics_fragment_menu_item -> supportFragmentManager.commit {
                      replace<FragmentComics>(R.id.navHostFragment)
                      setReorderingAllowed(true)
                      //addToBackStack("comics_item")
                  }
              }
              return@setOnItemSelectedListener true
          }*/
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}






