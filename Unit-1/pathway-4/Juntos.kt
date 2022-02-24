class Dice(val numSides: Int, val color: String = "None") {
    
    fun roll(): Int {
        return (1..numSides).random()
    }

    fun showInfo() {
	println("Sides: ${numSides}")
	println("Color: ${color}")
    }
}
fun main() {
    val myFirstDice = Dice(6, "Red")
    myFirstDice.showInfo()
    println("Your ${myFirstDice.numSides} sided dice rolled ${myFirstDice.roll()}")
    
    val mySecondDice = Dice(120)
    mySecondDice.showInfo()
    println("Your ${mySecondDice.numSides} sided dice rolled ${mySecondDice.roll()}")
}
