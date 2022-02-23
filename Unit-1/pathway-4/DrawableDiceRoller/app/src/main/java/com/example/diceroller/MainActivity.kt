package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

/**
 * Atividade que cria um número aleatório conforme
 * o usuário pressiona o botão.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Acdiciona funcionalidade ao botão
        val rollButton: Button = findViewById(R.id.button2)
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Gera novo número aleatório e atualiza a TextView
     */
    private fun rollDice() {
        // Cria dado de 6 lados
        val dice = Dice(6)
        val diceRoll = dice.roll()
        // Atualiza o texto da TextView
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()
    }
}

/**
 * Classe geradora de números
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
