package com.example.busschedule

import android.app.Activity
import android.app.Application
import com.example.busschedule.database.schedule.AppDatabase

class BusScheduleApplication : Application() {
    val database : AppDatabase by lazy { AppDatabase.getDatabase(this) }
}