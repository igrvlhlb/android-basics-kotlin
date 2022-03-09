/**
* Program that implements classes for different kinds of dwellings.
* Shows how to:
* Create class hierarchy, variables and functions with inheritance,
* abstract class, overriding, and private vs. public variables.
*/

import kotlin.math.PI
import kotlin.math.sqrt

fun main() {
    val squareCabin = SquareCabin(5, 5.0)
    val roundHut = RoundHut(3, 3.14)
    val roundTower = RoundTower(4, 3.0, 5)
    var dwellings = listOf(squareCabin, roundHut, roundTower)
    
    // while some of the dwelling has room, run the loop
    while ( dwellings.map{ it.hasRoom() }.any{ it == true } ) {
        dwellings.map {
            println("\n${it.className}\n============")
            println("Capacity: ${it.capacity}")
            println("Material: ${it.buildingMaterial}")
            println("Has room? ${it.hasRoom()}")
            it.getRoom()
            println("Floor area: ${it.floorArea()}")
            if (it is RoundHut) {
                println("Carpet size: ${it.calculateMaxCarpetSize()}")
            }
            if (it is RoundTower) {
                println("Floors: ${it.floors}")
            }
        }
    }
    
	
}

/**
 * Abstract class with all dwellings shared attributes
 *
 * @param residents Current number of residents
 */
abstract class Dwelling(private var residents: Int) {
    abstract val className: String
    abstract val buildingMaterial: String
    abstract val capacity: Int
    
    /**
     * Checks if a there is room for a new dweller
     *
     * @return true if room available, false otherwise
     */
    fun hasRoom(): Boolean {
        return residents < capacity
    }
    
    /**
     * Adds a new dweller if possible. Prints the result.
     */
    fun getRoom() {
        if (capacity > residents) {
            residents++
            println("You got a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }
    
    /**
     * Calculates the floor area of the dwelling
     * based on its shape
     *
     * @return floor area
     */
    abstract fun floorArea(): Double
}

/**
 * A square shaped dwelling with a single floor
 *
 * @param residents Current number of residents
 * @param length Length of one side of the square
 */
class SquareCabin(
    residents: Int,
    val length: Double) : Dwelling(residents) {
    
    override val className = "Square Cabin"
    override val buildingMaterial = "Wood"
    override val capacity = 6
    
    /**
     * Calculates the floor area using square side length
     *
     * @return floor area
     */
    override fun floorArea(): Double {
        return length * length
    }
}

/**
 * A circle shaped dwelling with a single floor
 *
 * @param residents Current number of residents
 * @param radius Radius of the circle
 */
open class RoundHut(
    residents: Int,
    val radius: Double) : Dwelling(residents) {
    
    override val className = "Round Hut"
    override val buildingMaterial = "Straw"
    override val capacity = 4
    
    /**
     * Calculates the floor area using the circle radius
     *
     * @return floor area
     */
    override fun floorArea(): Double {
        return PI * radius * radius
    }
    
    /**
     * Calculates the max carpet size.
     * It must be the largest square
     * that fits into a circle with radius `radius`
     * 
     * @return Max carpet size
     */
    fun calculateMaxCarpetSize(): Double {
        // diameter * diameter = 4 * radius ^ 2
        // (diameter * diameter) / 2 = 2 * radius ^ 2
        return sqrt(2 * radius * radius)
    }
}

/**
 * A circle shaped dwelling with one or more floors
 *
 * @param residents Current number of residents
 * @param radius Radius of the circle
 * @param floors Number of floors
 */
class RoundTower(
    residents: Int,
    radius: Double,
    val floors: Int = 2) : RoundHut(residents, radius) {
    
    override val className = "Round Tower"
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
    
    /**
     * Calculates the max carpet size.
     * It must be RoundHut's max carpet size
     * times the number of floors present in the tower
     *
     * @return Max carpet size times the number of floors.
     */
    override fun floorArea(): Double {
        return super.floorArea() * floors
    }
}
