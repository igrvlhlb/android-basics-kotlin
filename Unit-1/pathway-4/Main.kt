fun main() {
    val myFirstDice = Dice(6, "Red")
    myFirstDice.showInfo()
    println("Your ${myFirstDice.numSides} sided dice rolled ${myFirstDice.roll()}")
    
    val mySecondDice = Dice(120)
    mySecondDice.showInfo()
    println("Your ${mySecondDice.numSides} sided dice rolled ${mySecondDice.roll()}")
}
