package com.example.smart_office_main.service

import android.widget.LinearLayout
import com.example.smart_office_main.dataclass.EnumIndicatorsType
import com.example.smart_office_main.fragments.FragmentSensor
import com.example.smart_office_main.soviews.SensorButton
import com.example.smart_office_main.soviews.SensorIndicatorButton
import com.example.smart_office_main.test.TestDataRecordIndicator

class Sensor(_sensorID: String, _sensorContainer: SensorContainer) {
    private var indicators = mutableListOf<SensorIndicator>()
    var sensorID: String = _sensorID
    var sensorName: String = ""
    val sensorContainer: SensorContainer = _sensorContainer
    private var sensorButton: SensorButton? = null
    private var fragmentSensor: FragmentSensor? = null
    private var sensorIndicatorContainer: LinearLayout? = null


    fun setName(_sensorName:String){
        this.sensorName = _sensorName
        //sensorButton?.refreshAll()
        //fragmentSensor?.refreshHead()
    }

    fun setLinkToSensorButton(sensorButton: SensorButton){
        this.sensorButton = sensorButton
        sensorButton.setSensor(this)
    }

    fun setLinkToView(_fragmentSensor: FragmentSensor, _sensorIndicatorContainer: LinearLayout){
        if (this.fragmentSensor != _fragmentSensor || this.sensorIndicatorContainer != _sensorIndicatorContainer) {
            this.sensorContainer.setLinkToViewNull()
            this.fragmentSensor = _fragmentSensor
            this.sensorIndicatorContainer = _sensorIndicatorContainer
            this.createSensorIndicatorButton()
        }
    }

    fun setLinkToViewNull(){
        this.fragmentSensor = null
        this.sensorIndicatorContainer = null
    }

    private fun createSensorIndicatorButton(){
        val sensorIndicatorContainer = this.sensorIndicatorContainer
        if (sensorIndicatorContainer != null) {
            if (sensorIndicatorContainer.childCount > 0) sensorIndicatorContainer.removeAllViews()
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            for (indicator in indicators) {
                val newButtonIndicator =
                    SensorIndicatorButton(sensorIndicatorContainer.context)
                params.setMargins(5, 5, 5, 5)
                newButtonIndicator.layoutParams = params
                sensorIndicatorContainer.addView(newButtonIndicator)
                indicator.setLinkToSensorIndicatorButton(newButtonIndicator)
            }
            sensorIndicatorContainer.invalidate()
        }
    }

    fun testGenerateData(testSensorIndicatorType : Array<EnumIndicatorsType>?) {
        if (testSensorIndicatorType == null) return
        var sensorIndicator: SensorIndicator
        for (ind in 0 until testSensorIndicatorType.size) {
            sensorIndicator = SensorIndicator(testSensorIndicatorType[ind], this)
            sensorIndicator.testGenerateData()
            indicators.add(sensorIndicator)
        }
    }

    fun getAlarmState(type: EnumIndicatorsType? = null): Int {
        var maxAlarm  = 0
        for (indicator in indicators){
            if (indicator.type == type || type == null ) {
                val tmpAlarm = indicator.getAlarmCode()
                if (tmpAlarm > maxAlarm) maxAlarm = tmpAlarm
            }
        }
        return maxAlarm
    }

    fun eventDataIn(testDataRecordIndicator: TestDataRecordIndicator) {
        for (indicator in indicators) {
            if (indicator.type == testDataRecordIndicator.indicatorType) {
                indicator.eventDataIn(testDataRecordIndicator)
                break
            }
        }
    }

    fun onChangeSensorIndicator(){
        sensorButton?.refreshValue()
        fragmentSensor?.refreshHead()
        sensorContainer.onChangeSensor()
    }
}