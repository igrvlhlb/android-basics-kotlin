package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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

        rollDice()
    }

    /**
     * Gera novo número aleatório e atualiza a ImageView
     */
    private fun rollDice() {
        // Cria dado de 6 lados
        val dice = Dice(6)
        val diceRoll = dice.roll()
        // Atualiza o texto da TextView
        val diceImage: ImageView = findViewById(R.id.imageView)
        // Determina a imagem que será utilizada
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // Atualiza a imagem e o texto descritivo
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = "Dice with number ${diceRoll} on the top face"
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
