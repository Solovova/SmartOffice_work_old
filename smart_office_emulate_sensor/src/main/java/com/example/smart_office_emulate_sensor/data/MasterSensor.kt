package com.example.smart_office_emulate_sensor.data

import com.example.smart_office_lib.SensorIndicatorDataRecord
import com.example.smart_office_lib.SensorIndicatorTypeEnum

class MasterSensor(_sensorID: String, _sensorContainer: MasterSensorContainer) {
    var indicators = mutableListOf<MasterSensorIndicator>()
    var sensorID: String = _sensorID
    var sensorName: String = ""
    val sensorContainer: MasterSensorContainer = _sensorContainer

    fun setName(_sensorName:String){
        this.sensorName = _sensorName
    }

    fun testGenerateData(testSensorIndicatorTypeEnum : Array<SensorIndicatorTypeEnum>?) {
        if (testSensorIndicatorTypeEnum == null) return
        var sensorIndicator: MasterSensorIndicator
        for (ind in 0 until testSensorIndicatorTypeEnum.size) {
            sensorIndicator = MasterSensorIndicator(testSensorIndicatorTypeEnum[ind], this)
            sensorIndicator.testGenerateData()
            indicators.add(sensorIndicator)
        }
    }

    fun getAlarmState(typeEnum: SensorIndicatorTypeEnum? = null): Int {
        var maxAlarm  = 0
        for (indicator in indicators){
            if (indicator.typeEnum == typeEnum || typeEnum == null ) {
                val tmpAlarm = indicator.getAlarmCode()
                if (tmpAlarm > maxAlarm) maxAlarm = tmpAlarm
            }
        }
        return maxAlarm
    }

    fun eventDataIn(sensorIndicatorDataRecord: SensorIndicatorDataRecord) {
        for (indicator in indicators) {
            if (indicator.typeEnum == sensorIndicatorDataRecord.indicatorTypeEnum) {
                indicator.eventDataIn(sensorIndicatorDataRecord)
                break
            }
        }
    }

    fun onChangeSensorIndicator(){
        sensorContainer.onChangeSensor()
    }
}