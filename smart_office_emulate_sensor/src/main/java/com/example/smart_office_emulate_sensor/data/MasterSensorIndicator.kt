package com.example.smart_office_emulate_sensor.data

import android.os.SystemClock
import com.example.smart_office_lib.SensorIndicatorDataRecord
import com.example.smart_office_lib.SensorIndicatorDef
import com.example.smart_office_lib.SensorIndicatorTypeEnum

class MasterSensorIndicator(_typeEnum: SensorIndicatorTypeEnum, _sensor: MasterSensor) {
    private var indicatorValue: Double
    private var indicatorOldValue: Double
    private var indicatorValueTime: Long
    var sensorIndicatorDef: SensorIndicatorDef

    var typeEnum: SensorIndicatorTypeEnum = _typeEnum
    private val sensor: MasterSensor = _sensor

    private var alarmBorder: Array<Double>

    init {
        this.sensorIndicatorDef =  _sensor.sensorContainer.getDataIndicatorTypeDef(this.typeEnum)
        this.alarmBorder = sensorIndicatorDef.defAlarmBorder.clone()
        this.indicatorValue = sensorIndicatorDef.defValue
        this.indicatorOldValue = 0.0
        this.indicatorValueTime = 0
    }

    fun testGenerateData() {

    }

    fun getAlarmCode():Int {
        val dataIndicatorTypeDef =  this.sensor.sensorContainer.getDataIndicatorTypeDef(this.typeEnum)
        when (dataIndicatorTypeDef.defTypeOfAlarm) {
            0 -> {
                if (this.indicatorValue <= this.alarmBorder[0]) return 1
                if (this.indicatorValue >= this.alarmBorder[1]) return 2
                return 0
            }
            1 -> {
                if (this.indicatorValue >= this.alarmBorder[1]) return 2
                if (this.indicatorValue >= this.alarmBorder[0]) return 1
                return 0
            }
            else -> return 0
        }
    }

    private fun setIndicatorValue(_value: Double){
        this.indicatorOldValue = this.indicatorValue
        this.indicatorValueTime = SystemClock.currentThreadTimeMillis()
        this.indicatorValue = _value
        sensor.onChangeSensorIndicator()
    }

    fun eventDataIn(sensorIndicatorDataRecord: SensorIndicatorDataRecord) {
        this.setIndicatorValue(sensorIndicatorDataRecord.indicatorValue)
    }
}