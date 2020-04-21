package com.cloudproject.smartheartrate.model

import com.google.gson.annotations.SerializedName

data class AuthenResponse (
    @SerializedName("result") val result: String,
    @SerializedName("error") val error: String
)