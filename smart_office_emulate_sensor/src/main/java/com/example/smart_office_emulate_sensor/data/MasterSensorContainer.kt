package com.example.smart_office_emulate_sensor.data

import android.os.SystemClock
import com.example.smart_office_lib.SensorIndicatorDef
import com.example.smart_office_lib.SensorIndicatorTypeEnum

class MasterSensorContainer {
    var sensors = mutableMapOf<String, MasterSensor>()
    private var myThread: Thread? = null

    private var sensorIndicatorDef = mutableMapOf<SensorIndicatorTypeEnum, SensorIndicatorDef>()

    init {
        var tmpDataIndicatorTypeDef = SensorIndicatorDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 20.0
            defAlarmBorder          = arrayOf(19.0, 26.0)
            defTypeOfAlarm          = 0
        }
        sensorIndicatorDef[SensorIndicatorTypeEnum.Temperature] = tmpDataIndicatorTypeDef
        tmpDataIndicatorTypeDef = SensorIndicatorDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 400.0
            defAlarmBorder          = arrayOf(200.0, 600.0)
            defTypeOfAlarm          = 0
        }
        sensorIndicatorDef[SensorIndicatorTypeEnum.Brightness] = tmpDataIndicatorTypeDef
        tmpDataIndicatorTypeDef = SensorIndicatorDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 800.0
            defAlarmBorder          = arrayOf(1000.0, 1400.0)
            defTypeOfAlarm          = 1
        }
        sensorIndicatorDef[SensorIndicatorTypeEnum.Co2] = tmpDataIndicatorTypeDef
        tmpDataIndicatorTypeDef = SensorIndicatorDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 50.0
            defAlarmBorder          = arrayOf(70.0, 90.0)
            defTypeOfAlarm          = 1
        }
        sensorIndicatorDef[SensorIndicatorTypeEnum.Humidity] = tmpDataIndicatorTypeDef
        this.myThread = Thread(
            Runnable {
                    SystemClock.sleep(1000)
            }
        )
        this.myThread?.start()
    }


    fun getDataIndicatorTypeDef(_typeEnum: SensorIndicatorTypeEnum): SensorIndicatorDef {
        val dataIndicatorTypeDef = sensorIndicatorDef[_typeEnum]
        dataIndicatorTypeDef ?: return SensorIndicatorDef()
        return dataIndicatorTypeDef
    }

    fun testGenerateData() {
        val testSensorID: Array<String> = arrayOf("id123432", "id999797", "id999997")
        val testSensorName: Array<String> = arrayOf("Room 8", "Room 2", "Room 7")

        val testSensorIndicator = mutableMapOf<String,Array<SensorIndicatorTypeEnum>>()
        testSensorIndicator[testSensorID[0]] = arrayOf(
            SensorIndicatorTypeEnum.Temperature,
            SensorIndicatorTypeEnum.Brightness,
            SensorIndicatorTypeEnum.Co2,
            SensorIndicatorTypeEnum.Humidity
        )

        testSensorIndicator[testSensorID[1]] = arrayOf(
            SensorIndicatorTypeEnum.Temperature,
            SensorIndicatorTypeEnum.Humidity
        )

        testSensorIndicator[testSensorID[2]] = arrayOf(
            SensorIndicatorTypeEnum.Temperature,
            SensorIndicatorTypeEnum.Humidity,
            SensorIndicatorTypeEnum.Brightness
        )


        var sensor: MasterSensor?
        for (ind in 0 until testSensorID.size) {
            sensor = MasterSensor(testSensorID[ind], this)
            sensor.setName(testSensorName[ind])
            sensor.testGenerateData(testSensorIndicator[testSensorID[ind]])
            sensors[testSensorID[ind]] = sensor
        }
    }

    fun onChangeSensor(){

    }
}