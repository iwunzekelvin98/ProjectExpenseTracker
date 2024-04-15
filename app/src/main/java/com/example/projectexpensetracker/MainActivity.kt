package com.example.projectexpensetracker

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var monthSpinner: Spinner
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monthSpinner = findViewById(R.id.monthSpinner)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        addButton = findViewById(R.id.Add)  // Get a reference to the FloatingActionButton

        val months = arrayOf("Select Month","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        monthSpinner.adapter = adapter

        // Set up the click listener for the FloatingActionButton
        addButton.setOnClickListener {
            // Open the navigation drawer
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Setting navigation drawer listener
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                // Handle your menu item clicks here, example:
                // R.id.nav_home -> navigateToHome()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Setting window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Example navigation function
    private fun navigateToHome() {
        // Navigate to another activity or perform action
    }
}
