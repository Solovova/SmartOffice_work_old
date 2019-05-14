package com.example.smart_office_testdata


import com.example.smart_office_lib.SensorIndicatorDataRecord
import com.example.smart_office_lib.SensorIndicatorTypeEnum

class TestSensorIndicator {
    private var indicatorValue: Double
    private var type: SensorIndicatorTypeEnum
    private var time: Long
    private val sensor: TestSensor

    constructor(type: SensorIndicatorTypeEnum, indicatorValue: Double, time: Long, sensor: TestSensor) {
        this.indicatorValue = indicatorValue
        this.type = type
        this.time = time
        this.sensor = sensor
    }

    constructor(sensorIndicatorDataRecord: SensorIndicatorDataRecord, sensor: TestSensor) {
        this.indicatorValue = sensorIndicatorDataRecord.indicatorValue
        this.type = sensorIndicatorDataRecord.type
        this.time = sensorIndicatorDataRecord.time
        this.sensor = sensor
    }

    fun getSensorIndicatorDataRecord() : SensorIndicatorDataRecord {
        return SensorIndicatorDataRecord(sensor.sensorID, this.type, this.time, this.indicatorValue)
    }
}