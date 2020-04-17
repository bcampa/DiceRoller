package com.bcampa.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // todo: include modifier (e.g. Rolled 12 + 3 (1d20 + 3), Rolled 2, 4 + 2 = 8 (2d6 + 2))
    // todo: make sure the app doesn't crash if one of the fields is empty
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDecreaseDice.setOnClickListener {
            val currentNum = txtInputDiceQuantity.text.toString().toInt()
            if(currentNum > 1) {
                txtInputDiceQuantity.setText("${currentNum - 1}")
            }
        }

        btnIncreaseDice.setOnClickListener {
            val currentNum = txtInputDiceQuantity.text.toString().toInt()
            txtInputDiceQuantity.setText("${currentNum + 1}")
        }

        btnDecreaseFaces.setOnClickListener {
            val currentNum = txtInputFaceQuantity.text.toString().toInt()
            if(currentNum > 1) {
                txtInputFaceQuantity.setText("${currentNum - 1}")
            }
        }

        btnIncreaseFaces.setOnClickListener {
            val currentNum = txtInputFaceQuantity.text.toString().toInt()
            txtInputFaceQuantity.setText("${currentNum + 1}")
        }

        btnRoll.setOnClickListener {
            rollDice()
        }
    }

    private fun fieldsAreValid(): Boolean {
        val dieQuantity = txtInputDiceQuantity.text.toString().toInt()
        val faceQuantity = txtInputFaceQuantity.text.toString().toInt()
        var errorMessage = ""
        when {
            dieQuantity < 1 -> errorMessage = "You can't roll 0 dice!"
            faceQuantity < 2 -> errorMessage = "You can't roll a die with less than 2 faces!"
            dieQuantity > 200 -> errorMessage = "That's too many dice!"
            faceQuantity > 900 -> errorMessage = "That's too many faces!"
        }
        return if(errorMessage.isNotBlank()) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            false
        }
        else {
            true
        }
    }

    private fun rollDice() {
        if(fieldsAreValid()) {
            val dieQuantity = txtInputDiceQuantity.text.toString().toInt()
            val faceQuantity = txtInputFaceQuantity.text.toString().toInt()
            var outputText = "Rolled "
            if(dieQuantity > 1) {
                var total = 0
                for (i in 1..dieQuantity) {
                    val roll = Random.nextInt(1, faceQuantity + 1)
                    total += roll
                    outputText += roll
                    if (i != dieQuantity) outputText += ","
                    outputText += " "
                }
                outputText += "= $total (${dieQuantity}d${faceQuantity})"
            }
            else {
                val roll = Random.nextInt(1, faceQuantity + 1)
                outputText += "$roll (${dieQuantity}d${faceQuantity})"
            }
            txtResult.text = outputText
        }
    }
}
