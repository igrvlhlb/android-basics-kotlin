package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

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
        // Atualiza o texto da TextView
        val diceImage: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        // Cria função que obtém recurso de imagem dependendo do valor do dado
        val getDrawableResource: (Int) -> Int = { num: Int -> when (num) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        }

        val updateImageAndContentDescription = { img: ImageView, num: Int ->
            img.setImageResource(getDrawableResource(num))
            img.contentDescription = "Dice with number ${num} on the top face"
        }

        // Atualiza a imagem e o texto descritivo
        updateImageAndContentDescription(diceImage, dice.roll())
        updateImageAndContentDescription(diceImage2, dice.roll())
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
