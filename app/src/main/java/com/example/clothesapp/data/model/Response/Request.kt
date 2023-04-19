package com.example.clothesapp.data.model.Response

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("type") val type : String?,
    @SerializedName("query") val query : String?
)
