package com.example.clothesapp.data.model.Response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("request") val request: Request,
    @SerializedName("location") val location : Location
    , @SerializedName("current") val current: Current
)
