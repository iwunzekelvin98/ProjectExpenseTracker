package com.example.projectexpensetracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var etExpenseName: EditText
    private lateinit var etExpenseAmount: EditText
    private lateinit var btnAddExpense: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        etExpenseName = findViewById(R.id.etExpenseName)
        etExpenseAmount = findViewById(R.id.etExpenseAmount)
        btnAddExpense = findViewById(R.id.btnAddExpense)

        btnAddExpense.setOnClickListener {
            // Logic to handle adding an expense
            // You might want to save these values to a database or a shared preference
            finish() // Close this activity and return to the main screen
        }
    }
}
