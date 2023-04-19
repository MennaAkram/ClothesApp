package com.example.clothesapp.ui

import com.example.clothesapp.data.model.Response.DataResponse

interface MainView {
    fun showData(response: DataResponse)
    fun showError()
    fun showNoInternetConnection()
    fun hideNoInternetConnection()
}