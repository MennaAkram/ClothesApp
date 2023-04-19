package com.example.clothesapp.ui

import android.util.Log
import com.example.clothesapp.data.SharedPreferences
import com.example.clothesapp.data.WeatherService
import com.example.clothesapp.data.model.Response.ClothesData
import java.time.LocalDate
import java.time.LocalDateTime

class MainPresenter(private var mainView: MainView,private val sharedPreferences: SharedPreferences) {
    private val service by lazy { WeatherService() }

    fun getWeatherRequest(country: String){
        service.getWeather(country = country,
            onSuccess = {
                val date = it.location.localTime.take(10)
                mainView.showData(it)
                sharedPreferences.saveDate(date)
                Log.e( "result", it.toString())
            },
            onFailure = {
              mainView.showNoInternetConnection()
            })
    }

     fun getClothesImage(temperature: Int, date: String): Int{
        val clothesData = ClothesData()
        return if (sharedPreferences.isDateSaved(LocalDateTime.now().plusDays(1).toString())) {
            if (sharedPreferences.isImageSaved()) {
                sharedPreferences.getSaveImage()
            } else {
                val drawableId = clothesData.getImage(temperature)
                sharedPreferences.saveImage(drawableId)
                drawableId
            }
        } else {
            val drawableId = clothesData.getImage(temperature)
            sharedPreferences.saveImage(drawableId)
            drawableId
        }
    }
}