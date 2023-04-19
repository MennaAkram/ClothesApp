package com.example.clothesapp.data.model.Response

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name") val name : String,
    @SerializedName("country") val country : String,
    @SerializedName("region") val region : String,
    @SerializedName("localtime") val localTime : String
)
