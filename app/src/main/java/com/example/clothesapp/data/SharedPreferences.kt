package com.example.clothesapp.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedPreferences(private val applicationContext: Context) {

    private val sharedPreferences = applicationContext.getSharedPreferences(SAVED_IMAGE,MODE_PRIVATE)

    fun saveImage(imageId: Int){
        val edit = sharedPreferences.edit()
        edit.putInt(IMAGE_ID_KEY, imageId)
        edit.apply()
    }
    fun getSaveImage(): Int{
        return sharedPreferences.getInt(IMAGE_ID_KEY, 0)
    }

    fun saveDate(date: String){
        val edit = sharedPreferences.edit()
        edit.putString(DATE_KEY, date)
        edit.apply()
    }
    fun getSaveDate(): String?{
        return sharedPreferences.getString(DATE_KEY, "")
    }

    fun isImageSaved(): Boolean{
        return sharedPreferences.contains(IMAGE_ID_KEY)
    }

    fun isDateSaved(apiDate: String): Boolean{
        return getSaveDate() == apiDate.take(10)
    }

    companion object {
        private const val SAVED_IMAGE = "SAVED_IMAGE"
        private const val IMAGE_ID_KEY = "IMAGE_ID_KEY"
        private const val DATE_KEY = "DATE_KEY"
    }

}