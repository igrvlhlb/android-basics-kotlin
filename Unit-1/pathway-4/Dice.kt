class Dice(val numSides: Int, val color: String = "None") {
    
    fun roll(): Int {
        return (1..numSides).random()
    }

    fun showInfo() {
	println("Sides: ${numSides}")
	println("Color: ${color}")
    }
}
