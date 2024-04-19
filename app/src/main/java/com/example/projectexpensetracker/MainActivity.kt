package com.example.projectexpensetracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
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
    private lateinit var budgetTextView: TextView

    // Register the activity result launcher
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val budget = result.data?.getStringExtra("budget") ?: "0"
            budgetTextView.text = getString(R.string.currency_format, budget)

            // Save the budget in SharedPreferences
            getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit().apply {
                putString("budget", budget)
                apply()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monthSpinner = findViewById(R.id.monthSpinner)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        addButton = findViewById(R.id.Add)
        budgetTextView = findViewById(R.id.budject) // Initialize the TextView

        // Load the saved budget when the app starts
        val savedBudget = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("budget", "0")
        budgetTextView.text = getString(R.string.currency_format, savedBudget)

        val months = arrayOf("Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        monthSpinner.adapter = adapter

        addButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            val intent = when (menuItem.itemId) {
                R.id.nav_add_expense -> Intent(this, SetBudgetActivity::class.java)
                R.id.nav_set_budget -> Intent(this, AddExpenseActivity::class.java)
                else -> null
            }
            intent?.let { startForResult.launch(it) }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
