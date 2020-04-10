package com.cloudproject.smartheartrate.model

import com.google.gson.annotations.SerializedName

data class ElderList (
    @SerializedName("result") val elderList: ArrayList<Elder>
)