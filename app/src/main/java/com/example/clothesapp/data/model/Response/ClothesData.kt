package com.example.clothesapp.data.model.Response

import com.example.clothesapp.R

 class ClothesData{
    private var summer: List<Int> = listOf(R.drawable.shirt1, R.drawable.shirt2, R.drawable.shirt3)
    private var winter: List<Int> = listOf(R.drawable.jacket1, R.drawable.jacket2, R.drawable.jacket3)

     fun getImage(temperature: Int): Int{
        return when {
             temperature > 20 -> {
                 summer.random()
             }
             else -> {
                 winter.random()
             }
         }
     }
 }
