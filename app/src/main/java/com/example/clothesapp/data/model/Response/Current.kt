package com.example.clothesapp.data.model.Response

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("temperature") val temperature : Int,
    @SerializedName("weather_descriptions") val weather_descriptions : List<String>
)
