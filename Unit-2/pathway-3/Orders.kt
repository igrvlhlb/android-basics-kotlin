open class Item(val name: String, val price: Int)

class Noodles : Item("Noodles", 10) {
   override fun toString(): String {
       return name
   }
}

class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
	override fun toString(): String {
    	if (toppings.isEmpty()) {
        	return "$name [Chef's Choice]"
	    } else {
    	    return "$name [${toppings.joinToString()}]"
		}
	}
}

class Order(val orderNumber: Int) {
    private val itemList = mutableListOf<Item>()
    
    fun addItem(newItem: Item): Order {
        itemList.add(newItem)
        return this
    }
    
    fun addAll(newItems: List<Item>): Order {
        itemList.addAll(newItems)
        return this
    }
    
    fun removeItem() {
        // TODO
    }
    
    fun print() {
        var total = 0
        println("Order #${orderNumber}")
        for (item in itemList) {
            total += item.price
            println("\t$item: $${item.price}")
        }
        println("Total: $$total")
    }
}

fun main() {
    val orderList = mutableListOf<Order>()

    orderList.add(
        Order(0)
            .addAll(
                listOf(
                    Noodles(),
                    Vegetables("Cabbage", "Sprouts", "Onion"),
                    Vegetables())))
    
	orderList.add(Order(1).addItem(Noodles()))
    
    orderList.add(
        Order(2)
    		.addItem(Noodles())
            .addItem(Vegetables()))

    orderList.add(
        Order(3)
    		.addAll(
                listOf(
                    Noodles(),
                    Vegetables("Carrots", "Beans", "Celery"))))
    
    orderList.add(
        Order(4)
            .addItem(Noodles())
    		.addItem(Vegetables("Spinach"))
    		.addItem(Noodles()))

    for (order in orderList) {
        order.print()
        println()
    }
    
}
