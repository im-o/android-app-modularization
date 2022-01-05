package com.rivaldy.id.base.data.model.api.drink


import com.google.gson.annotations.SerializedName

data class DrinkData(
    @SerializedName("idDrink")
    val idDrink: String? = null,
    @SerializedName("strDrink")
    val strDrink: String? = null,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String? = null
)