package com.example.clothesapp.data

import android.util.Log
import com.example.clothesapp.data.model.Response.DataResponse
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class WeatherService {
    private val client: OkHttpClient by lazy {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()
    }

    fun getWeather(country: String,
                   onFailure: (message: String?) -> Unit,
                   onSuccess: (response: DataResponse) -> Unit
    ) {
        val request = Request.Builder()
            .url("$URL$country").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e.message)
                Log.e("message", "eeeeeeeeeeee")
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonString ->
                    val result = Gson().fromJson(jsonString, DataResponse::class.java)
                    onSuccess(result)
                    Log.d("message", "mmmmmmmmmmmmmmmm")
                }
            }
        })
    }

    companion object {
        const val URL = "http://api.weatherstack.com/current?access_key=930ab245c12b5679f4d987e6a5451b39&query="
    }
}