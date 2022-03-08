import kotlin.math.PI

fun main() {
    val squareCabin = SquareCabin(5, 5.0)
    val roundHut = RoundHut(3, 3.14)
    val roundTower = RoundTower(4, 3.0, 5)
    val dwellings = listOf(squareCabin, roundHut, roundTower)
    
    while ( dwellings.map{ it.hasRoom() }.any{ it == true } ) {
        dwellings.map({
            instance -> with (instance) {
                println("\n${className}\n============")
                println("Capacity: ${capacity}")
                println("Material: ${buildingMaterial}")
                println("Has room? ${hasRoom()}")
                getRoom()
                println("Floor area: ${floorArea()}")
                if (instance is RoundTower) {
                    println("Floors: ${instance.floors}")
                }
            }
        })
    }
    
	
}


abstract class Dwelling(private var residents: Int) {
    abstract val className: String
    abstract val buildingMaterial: String
    abstract val capacity: Int
    
    fun hasRoom(): Boolean {
        return residents < capacity
    }
    
    fun getRoom() {
        if (capacity > residents) {
            residents++
            println("You got a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }
    
    abstract fun floorArea(): Double
}

class SquareCabin(
    residents: Int,
	val length: Double) : Dwelling(residents) {
    
	override val className = "Square Cabin"
    override val buildingMaterial = "Wood"
    override val capacity = 6
    
    override fun floorArea(): Double {
        return length * length
    }
}

open class RoundHut(
    residents: Int,
	val radius: Double) : Dwelling(residents) {
    
    override val className = "Round Hut"
    override val buildingMaterial = "Straw"
    override val capacity = 4
    
    override fun floorArea(): Double {
        return PI * radius * radius
    }
}

class RoundTower(
    residents: Int,
    radius: Double,
	val floors: Int = 2) : RoundHut(residents, radius) {
    
    override val className = "Round Tower"
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
    
    override fun floorArea(): Double {
        return super.floorArea() * floors
    }
}
