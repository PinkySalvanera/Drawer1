package com.globe.drawer.tipcalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import com.globe.drawer.R


class CalculatorActivity : AppCompatActivity() {

    private lateinit var evAmount: EditText
    private lateinit var SBPercent: SeekBar
    private lateinit var tvTipPercent: TextView
    private lateinit var tvTip: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_calculator)

        // getActionBar().setHomeButtonEnabled(true)e

        evAmount = findViewById(R.id.evAmount)
        SBPercent = findViewById(R.id.SBPercent)
        tvTipPercent = findViewById(R.id.tvTipPercent)
        tvTip = findViewById(R.id.tvTip)
        tvTotal = findViewById(R.id.tvTotal)
        tvDescription = findViewById(R.id.tvDescription)

        addEditTextListener()
        addSeekbarListener()

    }

    private fun addEditTextListener() {

        evAmount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("MainActivity", s?.toString() ?: "")
                computeTip()
            }
        })
    }

    private fun addSeekbarListener(){
        SBPercent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(s: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("MainActivity", "$progress%")
                tvTipPercent.text = "$progress%"

                computeTip()
            }

            override fun onStartTrackingTouch(s: SeekBar?) {
            }

            override fun onStopTrackingTouch(s: SeekBar?) {
            }

        })
    }

    private fun computeTip(){
        val amount = evAmount.text.toString().toDoubleOrNull() ?: 0.0
        val percentage: Int = SBPercent.progress
        val tip = amount * (percentage/100.00)
        val total = amount+tip

        tvTip.text = "%.2f".format(tip)
        tvTotal.text = "%.2f".format(total)

        var classification = when(percentage){
            in 0..9 -> "Poor"
            in 10..15 -> "Good"
            in 15..20 -> "Great"
            else -> "Generous"
        }
        tvDescription.text = classification

        when (classification){
            in "Poor" -> tvDescription.setTextColor(Color.parseColor("#d63031")).toString()
            in "Good" -> tvDescription.setTextColor(Color.parseColor("#ff9f43")).toString()
            in "Great" -> tvDescription.setTextColor(Color.parseColor("#00b894")).toString()
            else -> tvDescription.setTextColor(Color.parseColor("#0984e3")).toString()
        }


    }

}

