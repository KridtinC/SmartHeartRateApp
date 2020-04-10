package com.cloudproject.smartheartrate.model

import com.google.gson.annotations.SerializedName

data class ElderListResponse (
    @SerializedName("result") val result: ArrayList<Elder>
)