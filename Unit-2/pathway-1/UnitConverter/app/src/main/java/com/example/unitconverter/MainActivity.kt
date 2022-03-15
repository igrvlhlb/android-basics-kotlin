package com.example.unitconverter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.unitconverter.databinding.ActivityMainBinding

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.convertButton.setOnClickListener { convertValue() }
    }

    fun convertValue() {
        val unitFrom = when (binding.radioGroupFrom.checkedRadioButtonId) {
            binding.cupFrom.id -> UnitConverter.Units.CUP
            binding.teaspoonFrom.id -> UnitConverter.Units.TSP
            binding.tablespoonFrom.id -> UnitConverter.Units.TBSP
            binding.fluidOuncesFrom.id -> UnitConverter.Units.FL_OZ
            else -> return
        }
        var volume = binding.editTextFrom.text.toString().toDoubleOrNull()
        if (volume == null) {
            volume = 0.0
        }
        val converter = UnitConverter(volume, unitFrom)
        val convertedVolume = when (binding.radioGroupTo.checkedRadioButtonId) {
            binding.cupTo.id -> converter.toCups()
            binding.teaspoonTo.id -> converter.toTsp()
            binding.tablespoonTo.id -> converter.toTbsp()
            binding.fluidOuncesTo.id -> converter.toFlOz()
            else -> return
        }
        binding.editTextTo.setText("%.3f".format(convertedVolume))
    }
}

/**
 * An unit converter class
 *
 * @param volume Original volume whose unit is specified in `unit` param.
 * @param unit Specifies the unit being used
 */
class UnitConverter(private val volume: Double, unit: Units = Units.CUP) {
    private var cups = 0.0

    /**
     * Ratios of (cups / unit)
     */
    private val cupsTspRatio = 1.0 / 48.0
    private val cupsTbspRatio = 1.0 / 16.0
    private val cupsFlOzRatio = 1.0 / 8.0

    /* Converts the input volume to cups */
    init {
        Log.d(TAG, "Volume: $volume")
        cups = when (unit) {
            Units.TSP -> tspToCups()
            Units.TBSP -> tbspToCups()
            Units.FL_OZ -> flOzToCups()
            Units.CUP -> volume
        }
        Log.d(TAG, "Cups: $cups")
    }

    /**
     * Functions to convert from units to cups
     */
    private fun tspToCups(): Double {
        return volume * cupsTspRatio
    }

    private fun tbspToCups(): Double {
        return volume * cupsTbspRatio
    }

    private fun flOzToCups(): Double {
        return volume * cupsFlOzRatio
    }

    /**
     * Functions to convert from cups to units
     */
    fun toCups(): Double {
        return cups
    }

    fun toTsp(): Double {
        return cups / cupsTspRatio
    }

    fun toTbsp(): Double {
        return cups / cupsTbspRatio
    }

    fun toFlOz(): Double {
        return cups / cupsFlOzRatio
    }

    /**
     * Units enum
     */
    enum class Units {
        TSP,    // teaspoon
        TBSP,   // tablespoon
        FL_OZ,  // fluid ounces
        CUP     // cups
    }
}