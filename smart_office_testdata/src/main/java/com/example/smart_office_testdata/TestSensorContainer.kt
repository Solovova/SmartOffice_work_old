package com.example.smart_office_testdata

import com.example.smart_office_lib.SensorIndicatorDataRecord
import com.example.smart_office_lib.SensorIndicatorTypeEnum


class TestSensorContainer {
    var sensors = mutableMapOf<String,TestSensor>()
    init {
        val testSensorID: Array<String> = arrayOf("id001", "id002", "id003")
        val testSensorName: Array<String> = arrayOf("Room 1", "Room 2", "Room 3")

        val testData = mutableMapOf<String, MutableList<SensorIndicatorDataRecord>>()

        //"id001"
        var testSensorData = mutableListOf<SensorIndicatorDataRecord>()
        testSensorData.add(SensorIndicatorDataRecord("id001",SensorIndicatorTypeEnum.Temperature,0,20.0))
        testSensorData.add(SensorIndicatorDataRecord("id001",SensorIndicatorTypeEnum.Co2,0,800.0))
        testSensorData.add(SensorIndicatorDataRecord("id001",SensorIndicatorTypeEnum.Humidity,0,30.0))
        testSensorData.add(SensorIndicatorDataRecord("id001",SensorIndicatorTypeEnum.Brightness,0,400.0))

        testData["id001"] = testSensorData

        //"id002"
        testSensorData = mutableListOf<SensorIndicatorDataRecord>()
        testSensorData.add(SensorIndicatorDataRecord("id002",SensorIndicatorTypeEnum.Temperature,0,22.0))
        testSensorData.add(SensorIndicatorDataRecord("id002",SensorIndicatorTypeEnum.Co2,0,810.0))
        testSensorData.add(SensorIndicatorDataRecord("id002",SensorIndicatorTypeEnum.Brightness,0,390.0))

        testData["id002"] = testSensorData

        //"id003"
        testSensorData = mutableListOf<SensorIndicatorDataRecord>()
        testSensorData.add(SensorIndicatorDataRecord("id003",SensorIndicatorTypeEnum.Co2,0,900.0))
        testSensorData.add(SensorIndicatorDataRecord("id003",SensorIndicatorTypeEnum.Humidity,0,40.0))

        testData["id003"] = testSensorData

        var sensor: TestSensor?
        for (ind in 0 until testSensorID.size) {
            sensor = TestSensor(testSensorID[ind] , this)
            sensor.setName(testSensorName[ind])
            sensor.testGenerateData(testData[testSensorID[ind]])
            sensors[testSensorID[ind]] = sensor
        }
    }
}