package com.example.smart_office_testdata

import com.example.smart_office_lib.SensorIndicatorDataRecord
import com.example.smart_office_lib.SensorIndicatorTypeEnum

class TestSensor(_sensorID: String , _sensorContainer: TestSensorContainer) {
    var indicators = mutableListOf<TestSensorIndicator>()
    var sensorID: String = _sensorID
    var sensorName: String = ""
    val sensorContainer: TestSensorContainer = _sensorContainer

    fun setName(_sensorName:String){
        this.sensorName = _sensorName
    }

    fun testGenerateData(testSensorData : MutableList<SensorIndicatorDataRecord>?) {
        if (testSensorData == null) return
        var sensorIndicator: TestSensorIndicator
        for (ind in 0 until testSensorData.size) {
            sensorIndicator = TestSensorIndicator(testSensorData[ind], this)
            indicators.add(sensorIndicator)
        }
    }
}