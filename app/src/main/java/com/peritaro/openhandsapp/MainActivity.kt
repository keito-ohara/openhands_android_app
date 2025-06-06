package com.peritaro.openhandsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private var currentInput = ""
    private var currentOperator = ""
    private var firstValue = 0.0
    private var secondValue = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)

        // Set up number button click listeners
        for (i in 0..9) {
            val resId = resources.getIdentifier("btn_$i", "id", packageName)
            findViewById<Button>(resId)?.setOnClickListener { onNumberClick(it) }
        }

        // Set up operator buttons
        findViewById<Button>(R.id.btn_clear)?.setOnClickListener { onClearClick() }
        findViewById<Button>(R.id.btn_add)?.setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btn_subtract)?.setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btn_multiply)?.setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.btn_divide)?.setOnClickListener { onOperatorClick("/") }
        findViewById<Button>(R.id.btn_equals)?.setOnClickListener { onEqualsClick() }
    }

    private fun onNumberClick(view: View) {
        currentInput += (view as Button).text
        result.setText(currentInput)
    }

    private fun onOperatorClick(operator: String) {
        if (currentInput.isNotEmpty()) {
            firstValue = currentInput.toDouble()
            currentOperator = operator
            currentInput = ""
        }
    }

    private fun onEqualsClick() {
        if (currentInput.isNotEmpty() && currentOperator.isNotEmpty()) {
            secondValue = currentInput.toDouble()
            val calculatedValue = when (currentOperator) {
                "+" -> firstValue + secondValue
                "-" -> firstValue - secondValue
                "*" -> firstValue * secondValue
                "/" -> firstValue / secondValue
                else -> 0.0
            }
            result.setText(calculatedValue.toString())
            currentInput = calculatedValue.toString()
            currentOperator = ""
        }
    }

    private fun onClearClick() {
        currentInput = ""
        currentOperator = ""
        result.setText("")
    }
}
