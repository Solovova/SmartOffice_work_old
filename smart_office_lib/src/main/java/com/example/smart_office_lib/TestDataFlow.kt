package com.example.smart_office_lib

import com.example.smart_office_lib.SensorIndicatorTypeEnum
import com.example.smart_office_lib.SensorIndicatorDataRecord

class TestDataFlow {
    private var sensorIndicatorData:MutableList<SensorIndicatorDataRecord> = mutableListOf()
    private var nextInd: Int = -1

    init {
        this.nextInd = -1
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Temperature,
                18.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Temperature,
                20.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Temperature,
                36.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Co2,
                1100.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Co2,
                1500.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Co2,
                800.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Temperature,
                20.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Co2,
                1100.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Co2,
                1500.0
            )
        )
        sensorIndicatorData.add(
            SensorIndicatorDataRecord(
                "id123432",
                SensorIndicatorTypeEnum.Co2,
                800.0
            )
        )
    }

    fun getNextTestRecord() : SensorIndicatorDataRecord {
        this.nextInd++
        if (this.nextInd >=sensorIndicatorData.size) this.nextInd = 0
        return sensorIndicatorData[this.nextInd]
    }
}