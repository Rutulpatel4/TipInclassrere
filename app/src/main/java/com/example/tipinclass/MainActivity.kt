package com.example.tipinclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // variables
    var amount = 0.0
    var tipPercent = 0.15
    var tip = 0.0
    var total = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    // text changed listner
        editTextAmount.addTextChangedListener(object: TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(editTextAmount.text.isNotEmpty()) {
                    amount = editTextAmount.text.toString().toDouble()
                    calculate()
                }
                else {
                    amount = 0.0
                    calculate()
                }
            }

        })

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                // make sure amount is not empty
                if(editTextAmount.text.isEmpty()) {
                    return
                }
                else {
                    //display the current tip percentage
                    textViewTipPercentage.setText(p1.toString() + "%")

                    tipPercent = (p1/100.0)
                    calculate()

                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

    }


    fun calculate() {
        //calculate the vlaues
        tip = amount * tipPercent
        total = amount + tip

        //enable currency

        val currencyFormate = NumberFormat.getCurrencyInstance()

        //display the values
        textViewTipAmoUpdating.setText(currencyFormate.format(tip).toString())
        textViewTotalUpdating.setText(currencyFormate.format(total).toString())
    }
}
