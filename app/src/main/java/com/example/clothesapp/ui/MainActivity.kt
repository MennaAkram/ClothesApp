package com.example.clothesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.clothesapp.data.SharedPreferences
import com.example.clothesapp.data.model.Response.DataResponse
import com.example.clothesapp.databinding.ActivityMainBinding
import com.example.clothesapp.util.hide
import com.example.clothesapp.util.show

class MainActivity : AppCompatActivity() , MainView{
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val sharedPreferences by lazy { SharedPreferences(applicationContext) }
    private val presenter: MainPresenter by lazy { MainPresenter(this,sharedPreferences) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.getWeatherRequest("Cairo")
        addCallBack()
    }

    private fun addCallBack(){
        binding.tryAgainText.setOnClickListener {
            Log.i("TAG", "addCallBack: ")
            presenter.getWeatherRequest("Cairo")
            hideNoInternetConnection()
            showError()
        }
    }

    override fun showNoInternetConnection(){
        runOnUiThread {
            binding.apply {
                informationGroup.hide()
                clothesCardView.hide()
                noInternetLottie.show()
                tryAgainText.show()
                }
            }
        }

    override fun hideNoInternetConnection() {
        runOnUiThread {
            binding.apply {
                noInternetLottie.hide()
                tryAgainText.hide()
                informationGroup.show()
                clothesCardView.show()
            }
        }
    }

    override fun showData(response: DataResponse) {
        runOnUiThread {
            Log.e("message", response.toString())
            val country = response.location.name
            binding.countryNameTextView.text = country
            val date = response.location.localTime
            binding.dayDetailsTextView.text = date
            val temperature = response.current.temperature
            binding.tempTextView.text = temperature.toString()
            val weatherdescription = response.current.weather_descriptions.first().toString()
            binding.weatherStatuTextView.text = weatherdescription
            val drawableId = presenter.getClothesImage(temperature, date)
            binding.clothesImageView.setImageResource(drawableId)
        }
    }

    override fun showError() {
        showErrorMessage()
    }

    private fun showErrorMessage() {
        Toast.makeText(this,
            "Please check your connection and try again", Toast.LENGTH_LONG)
            .show()
    }
}