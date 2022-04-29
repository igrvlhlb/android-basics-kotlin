package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private var _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> get() = _quantity
    private var _flavor = MutableLiveData<String>("")
    val flavor: LiveData<String> get() = _flavor
    private var _date = MutableLiveData<String>("")
    val date: LiveData<String> get() = _date
    private var _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> get() = _price

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }
}