package com.example.smart_office_emulate_sensor

import android.app.Activity
import android.app.Application
import com.example.smart_office_emulate_sensor.data.MasterSensorContainer


class SOApplicationEmulator : Application() {
    var sensorContainer = MasterSensorContainer()

    override fun onCreate() {
        super.onCreate()
        //this.sensorContainer.testGenerateData()
    }
}