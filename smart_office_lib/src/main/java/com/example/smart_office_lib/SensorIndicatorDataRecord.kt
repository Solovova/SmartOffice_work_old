package com.example.smart_office_lib

import org.json.JSONObject

class SensorIndicatorDataRecord {
    var sensorId: String
    var indicatorTypeEnum: SensorIndicatorTypeEnum
    var indicatorValue: Double

    constructor (sensorId: String , indicatorTypeEnum: SensorIndicatorTypeEnum , indicatorValue: Double) {
        this.sensorId = sensorId
        this.indicatorTypeEnum = indicatorTypeEnum
        this.indicatorValue = indicatorValue
    }

    constructor(jsObject: JSONObject) {
        this.sensorId = jsObject.getString("sensorId")
        this.indicatorTypeEnum = SensorIndicatorTypeEnum.values()[jsObject.getInt("indicatorTypeEnum")]
        this.indicatorValue = jsObject.getDouble("indicatorValue")
    }

    fun getJSObject() : JSONObject {
        val jObject = JSONObject()
        jObject.put("sensorId",this.sensorId)
        jObject.put("indicatorTypeEnum",this.indicatorTypeEnum.ordinal)
        jObject.put("indicatorValue",this.indicatorValue)
        return jObject
    }
}