package com.peritaro.openhandsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var display: EditText
    private var currentInput = ""
    private var currentOperator = ""
    private var firstOperand = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        // Set up number button click listeners
        val numberButtons = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        )

        numberButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { onNumberClick(it) }
        }

        // Set up operator button click listeners
        val operatorButtons = listOf(
            R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide
        )

        operatorButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { onOperatorClick(it) }
        }

        // Set up other buttons
        findViewById<Button>(R.id.btn_equals).setOnClickListener { onEqualsClick() }
        findViewById<Button>(R.id.btn_clear).setOnClickListener { onClearClick() }
    }

    private fun onNumberClick(view: View) {
        val button = view as Button
        currentInput += button.text
        display.setText(currentInput)
    }

    private fun onOperatorClick(view: View) {
        val button = view as Button
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toDouble()
            currentOperator = button.text.toString()
            currentInput = ""
        }
    }

    private fun onEqualsClick() {
        if (currentInput.isNotEmpty() && currentOperator.isNotEmpty()) {
            val secondOperand = currentInput.toDouble()
            val result = when (currentOperator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> firstOperand / secondOperand
                else -> 0.0
            }
            display.setText(result.toString())
            currentInput = result.toString()
            currentOperator = ""
        }
    }

    private fun onClearClick() {
        currentInput = ""
        currentOperator = ""
        firstOperand = 0.0
        display.setText("")
    }
}
