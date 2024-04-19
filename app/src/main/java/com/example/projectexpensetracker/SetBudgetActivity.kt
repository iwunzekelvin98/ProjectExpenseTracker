package com.example.projectexpensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SetBudgetActivity : AppCompatActivity() {
    private lateinit var etBudget: EditText
    private lateinit var btnSetBudget: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to your layout for the SetBudgetActivity, e.g., R.layout.activity_set_budget
        setContentView(R.layout.activity_set_budget)

        // Initialize your EditText and Button
        etBudget = findViewById(R.id.etBudget)
        btnSetBudget = findViewById(R.id.btnSetBudget)

        // Set the OnClickListener for your button
        btnSetBudget.setOnClickListener {
            val budget = etBudget.text.toString()
            // Create an Intent that will hold the result data
            val resultIntent = Intent()
            // Put the budget value into the Intent
            resultIntent.putExtra("budget", budget)
            // Set the result with RESULT_OK and the Intent
            setResult(RESULT_OK, resultIntent)
            // Finish the activity, returning to the parent activity (MainActivity in your case)
            finish()
        }
    }
}
