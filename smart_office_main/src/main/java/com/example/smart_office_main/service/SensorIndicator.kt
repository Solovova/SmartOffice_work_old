package com.example.smart_office_main.service

import com.example.smart_office_main.soviews.SensorIndicatorButton
import com.example.smart_office_lib.SensorIndicatorTypeEnum
import com.example.smart_office_lib.SensorIndicatorDataRecord
import android.os.SystemClock
import com.example.smart_office_lib.SensorIndicatorDef


class SensorIndicator(_typeEnum: SensorIndicatorTypeEnum, _sensor: Sensor) {
    private var indicatorValue: Double
    private var indicatorOldValue: Double
    private var indicatorValueTime: Long
    var sensorIndicatorDef: SensorIndicatorDef

    var typeEnum: SensorIndicatorTypeEnum = _typeEnum
    val sensor: Sensor = _sensor


    private var alarmBorder: Array<Double>


    private var sensorIndicatorButton: SensorIndicatorButton? = null

    init {
        this.sensorIndicatorDef =  _sensor.sensorContainer.getDataIndicatorTypeDef(this.typeEnum)
        this.alarmBorder = sensorIndicatorDef.defAlarmBorder.clone()
        this.indicatorValue = sensorIndicatorDef.defValue
        this.indicatorOldValue = 0.0
        this.indicatorValueTime = 0
    }

    fun testGenerateData() {

    }

    fun setLinkToSensorIndicatorButton(_sensorIndicatorButton: SensorIndicatorButton) {
        if (this.sensorIndicatorButton !=_sensorIndicatorButton) {
            this.sensorIndicatorButton = _sensorIndicatorButton
            _sensorIndicatorButton.setSensorIndicator(this)
        }
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

    fun getIndicatorValue(): Double {
        return this.indicatorValue
    }

    private fun setIndicatorValue(_value: Double){
        this.indicatorOldValue = this.indicatorValue
        this.indicatorValueTime = SystemClock.currentThreadTimeMillis()
        this.indicatorValue = _value
        sensorIndicatorButton?.refreshValue()
        sensor.onChangeSensorIndicator()
    }

    fun eventDataIn(sensorIndicatorDataRecord: SensorIndicatorDataRecord) {
        this.setIndicatorValue(sensorIndicatorDataRecord.indicatorValue)
    }


}