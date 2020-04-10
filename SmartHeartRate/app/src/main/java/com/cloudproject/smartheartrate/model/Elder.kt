package com.cloudproject.smartheartrate.model

import com.google.gson.annotations.SerializedName

data class Elder(
    @SerializedName("deviceID") val deviceID: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("age") val age: Int,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)