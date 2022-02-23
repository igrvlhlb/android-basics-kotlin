package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

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
        // Atualiza o texto da TextView
        val resultTextView0: TextView = findViewById(R.id.dado1)
        val resultTextView1: TextView = findViewById(R.id.dado2)
        resultTextView0.text = dice.roll().toString()
        resultTextView1.text = dice.roll().toString()
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
