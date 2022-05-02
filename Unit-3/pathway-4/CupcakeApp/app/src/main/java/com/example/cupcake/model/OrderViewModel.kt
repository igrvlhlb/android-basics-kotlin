package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import java.text.SimpleDateFormat

class OrderViewModel : ViewModel() {
    private var _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> get() = _quantity
    private var _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> get() = _flavor
    private var _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date
    private var _price = MutableLiveData<Double>()
    val price: LiveData<Double> get() = _price
    val dateOptions = getPickupOptions()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
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
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }
}