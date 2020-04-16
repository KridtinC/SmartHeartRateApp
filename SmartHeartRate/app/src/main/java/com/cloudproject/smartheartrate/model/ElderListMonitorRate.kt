package com.cloudproject.smartheartrate.model

import com.google.gson.annotations.SerializedName

data class MonitorRate(
    @SerializedName("deviceID") val deviceID: Int,
    @SerializedName("heartRate") val heartRate:Int
)

data class MonitorResponse(
    @SerializedName("result") val result:ArrayList<MonitorRate>
)


