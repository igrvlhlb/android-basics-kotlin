fun main() {
    val squareCabin = SquareCabin(5)
    val roundHut = RoundHut(3)
    val roundTower = RoundTower(4, 6)
	val dwellings = listOf(squareCabin, roundHut, roundTower)
    
	dwellings.map({
        instance -> with (instance) {
            println("\n${className}\n============")
            println("Capacity: ${capacity}")
            println("Material: ${buildingMaterial}")
            println("Has room? ${hasRoom()}")
            if (instance is RoundTower) {
                println("Floors: ${instance.floors}")
            }
        }
    })
}


abstract class Dwelling(private var residents: Int) {
    abstract val className: String
    abstract val buildingMaterial: String
    abstract val capacity: Int
    
    fun hasRoom(): Boolean {
        return residents < capacity
    }
}

class SquareCabin(residents: Int) : Dwelling(residents) {
	override val className = "Square Cabin"
    override val buildingMaterial = "Wood"
    override val capacity = 6
}

open class RoundHut(residents: Int) : Dwelling(residents) {
    override val className = "Round Hut"
    override val buildingMaterial = "Straw"
    override val capacity = 4
}

class RoundTower(
    residents: Int,
	val floors: Int = 2) : RoundHut(residents) {
    override val className = "Round Tower"
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
}
