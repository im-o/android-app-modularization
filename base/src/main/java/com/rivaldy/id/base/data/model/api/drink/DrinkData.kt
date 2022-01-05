package com.rivaldy.id.base.data.model.api.drink


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DrinkData(
    @SerializedName("idDrink")
    val idDrink: String? = null,
    @SerializedName("strDrink")
    val strDrink: String? = null,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String? = null
) : Parcelable