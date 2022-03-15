package com.example.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    private val cupsTspRatio = 1 / 48
    private val cupsTbspRatio = 1 / 16
    private val cupsFlOzRatio = 1 / 8

    /* Converts the input volume to cups */
    init {
        cups = when (unit) {
            Units.TSP -> tspToCups()
            Units.TBSP -> tbspToCups()
            Units.FL_OZ -> tbspToCups()
            Units.CUP -> volume
        }
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