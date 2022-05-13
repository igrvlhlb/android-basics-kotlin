package com.example.cupcake.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> get() = _quantity
    private var _currentQuantity = 0
    val currentQuantity get() = _currentQuantity
    private var _quantityByFlavor = AutoGenMap(mutableMapOf())
    val quantityByFlavor: Map<String, LiveData<String>> get() = _quantityByFlavor
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    val dateOptions = getPickupOptions()

    init {
        Log.d("MainActivity", "Init block called")
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    private fun getFlavorQuantity(desiredFlavor: String): Int {
        return _quantityByFlavor[desiredFlavor]?.value?.toInt() ?: 0
    }

    fun incFlavor(desiredFlavor: String): Boolean {
        val oldQuantity = getFlavorQuantity(desiredFlavor)
        val newQuantity = oldQuantity + 1

        if (_currentQuantity == quantity.value) return false

        (_quantityByFlavor[desiredFlavor] as MutableLiveData<String>).value = (newQuantity).toString()
        _currentQuantity += 1
        Log.d("MainActivity", "Incremented from $oldQuantity to $newQuantity")
        return true
    }

    fun decFlavor(desiredFlavor: String): Boolean {
        val oldQuantity = getFlavorQuantity(desiredFlavor)
        val newQuantity = oldQuantity - 1

        if ((_currentQuantity == 0) || (oldQuantity == 0)) return false

        (_quantityByFlavor[desiredFlavor] as MutableLiveData<String>).value = (newQuantity).toString()
        _currentQuantity -= 1
        Log.d("MainActivity", "Decremented from $oldQuantity to ${newQuantity}")
        return true
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun resetOrder() {
        Log.d("MainActivity", "resetOrder() called")
        _quantity.value = 0
        _date.value = dateOptions[0]
        _price.value = 0.0
        _currentQuantity = 0
        _quantityByFlavor.values
            .forEach { (it as MutableLiveData<String>).value = "0" }
    }

    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    /** LinkedHashMap customizado que chama o método .set() quando é usado o .get() para chaves que
     * não estão presentes.
     * Isso faz com que todas as instâncias de objetos retornados por padrão permanecam no Map
     */
    class AutoGenMap(_map: MutableMap<String,LiveData<String>>): LinkedHashMap<String,LiveData<String>>(_map) {
        override fun get(key: String): LiveData<String>? {
            var created_now = false
            if (!this.containsKey(key)) {
                this.set(key, MutableLiveData("0"))
                created_now = true
            }
            val ret = super.get(key)
            Log.v("MainActivity", "AutoGenMap.get($key) returned ${ret?.value} [Created: $created_now]")
            return super.get(key)
        }
    }
}